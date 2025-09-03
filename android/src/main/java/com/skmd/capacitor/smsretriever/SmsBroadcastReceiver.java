package com.skmd.capacitor.smsretriever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.getcapacitor.PluginCall;
import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.auth.api.phone.SmsRetrieverStatusCodes;

public class SmsBroadcastReceiver extends BroadcastReceiver {

    private static final String TAG = "Capacitor/SmsRetriever";

    private PluginCall call;

    public void setCall(PluginCall call) {
        this.call = call;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (SmsRetriever.SMS_RETRIEVED_ACTION.equals(intent.getAction())) {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                int status = ((com.google.android.gms.common.api.Status) extras.get(SmsRetriever.EXTRA_STATUS)).getStatusCode();
                if (status == SmsRetrieverStatusCodes.SUCCESS) {
                    String message = extras.getString(SmsRetriever.EXTRA_SMS_MESSAGE);
                    Log.i(TAG, "SMS Retrieved: " + message);
                    if (call != null) {
                        call.resolve(new com.getcapacitor.JSObject().put("body", message));
                        call = null; // Prevent multiple calls
                    }
                } else {
                    Log.e(TAG, "SMS Retriever failed with status: " + status);
                    if (call != null) {
                        call.reject("SMS retrieval failed with status: " + status);
                        call = null;
                    }
                }
            }
        }
    }
}
