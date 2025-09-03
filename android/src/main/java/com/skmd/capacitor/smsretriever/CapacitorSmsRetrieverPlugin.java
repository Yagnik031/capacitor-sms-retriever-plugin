package com.skmd.capacitor.smsretriever;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Base64;
import android.util.Log;

import java.security.MessageDigest;
import java.util.Arrays;

public class CapacitorSmsRetrieverPlugin {

    private static final String TAG = "Capacitor/SmsRetriever";

    /**
     * Computes the 11-character app hash required by Android SMS Retriever API.
     *
     * @param context Android context
     * @return 11-character app hash string
     * @throws Exception if any error occurs during calculation
     */
    public String getAppHash(Context context) throws Exception {
        String pkg = context.getPackageName();
        PackageManager pm = context.getPackageManager();
        PackageInfo info;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            info = pm.getPackageInfo(pkg, PackageManager.GET_SIGNING_CERTIFICATES);
            for (android.content.pm.Signature s : info.signingInfo.getApkContentsSigners()) {
                return hashFor(pkg, s.toCharsString());
            }
        } else {
            info = pm.getPackageInfo(pkg, PackageManager.GET_SIGNATURES);
            for (android.content.pm.Signature s : info.signatures) {
                return hashFor(pkg, s.toCharsString());
            }
        }
        return "";
    }

    private String hashFor(String pkg, String sig) throws Exception {
        String appInfo = pkg + " " + sig;
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(appInfo.getBytes("UTF-8"));
        byte[] digest = md.digest();
        String base64 = Base64.encodeToString(Arrays.copyOfRange(digest, 0, 9),
                Base64.NO_PADDING | Base64.NO_WRAP);
        Log.d(TAG, "Computed App Hash: " + base64);
        return base64.length() >= 11 ? base64.substring(0, 11) : base64;
    }

    /**
     * Echo method to verify communication.
     *
     * @param value input string
     * @return input string echoed
     */
    public String echo(String value) {
        Log.i(TAG, "Echo: " + value);
        return value;
    }
}
