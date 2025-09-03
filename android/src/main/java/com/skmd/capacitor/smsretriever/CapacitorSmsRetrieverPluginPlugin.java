package com.skmd.capacitor.smsretriever;

import android.content.IntentFilter;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.auth.api.phone.SmsRetrieverClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

/**
 * Capacitor plugin bridging Android SMS Retriever native code to JS.
 */
@CapacitorPlugin(name = "CapacitorSmsRetrieverPlugin")
public class CapacitorSmsRetrieverPluginPlugin extends Plugin {

    private static final String TAG = "Capacitor/SmsRetriever";

    private CapacitorSmsRetrieverPlugin implementation = new CapacitorSmsRetrieverPlugin();

    private SmsBroadcastReceiver smsReceiver;

    /**
     * Echo method for testing communication.
     *
     * @param call Plugin call containing { value: string }
     */
    @PluginMethod
    public void echo(PluginCall call) {
        String value = call.getString("value");
        JSObject ret = new JSObject();
        ret.put("value", implementation.echo(value));
        call.resolve(ret);
    }

    /**
     * Computes and returns the 11-char app hash.
     *
     * @param call Plugin call, no parameters required
     */
    @PluginMethod
    public void getAppHash(PluginCall call) {
        try {
            String hash = implementation.getAppHash(getContext());
            JSObject ret = new JSObject();
            ret.put("hash", hash);
            call.resolve(ret);
        } catch (Exception e) {
            call.reject("Error computing app hash: " + e.getMessage());
        }
    }

    /**
     * Starts listening for incoming SMS messages using SMS Retriever API.
     *
     * @param call PluginCall to resolve or reject based on start success/failure
     */
    @PluginMethod
    public void startListening(PluginCall call) {
        SmsRetrieverClient client = SmsRetriever.getClient(getContext());
        Task<Void> task = client.startSmsRetriever();

        task.addOnSuccessListener(
                new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.i(TAG, "SMS Retriever started");

                        smsReceiver = new SmsBroadcastReceiver();
                        ((SmsBroadcastReceiver) smsReceiver).setCall(call);

                        IntentFilter filter = new IntentFilter(SmsRetriever.SMS_RETRIEVED_ACTION);

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                            getContext().registerReceiver(smsReceiver, filter, android.content.Context.RECEIVER_EXPORTED);
                        } else {
                            getContext().registerReceiver(smsReceiver, filter);
                        }
                    }
                }
        );

        task.addOnFailureListener(
                new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG, "Failed to start SMS Retriever", e);
                        call.reject("SMS Retriever failed to start", e);
                    }
                }
        );
    }

    /**
     * Stops listening for SMS messages and unregisters broadcast receiver.
     *
     * @param call PluginCall to resolve after unregistering receiver.
     */
    @PluginMethod
    public void stopListening(PluginCall call) {
        if (smsReceiver != null) {
            getContext().unregisterReceiver(smsReceiver);
            smsReceiver = null;
        }
        call.resolve();
    }
}
