package com.example.notisaver.useful;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

import com.example.notisaver.BuildConfig;

public class CNLSHelper {
    public static String getAppNameFromPackage(Context context, String packageName, boolean returnNull) {
        final PackageManager pm = context.getApplicationContext().getPackageManager();
        ApplicationInfo ai;
        try {
            ai = pm.getApplicationInfo(packageName, 0);
        } catch(final PackageManager.NameNotFoundException e) {
            ai = null;
        }
        if(returnNull) {
            return ai == null ? null : pm.getApplicationLabel(ai).toString();
        }
        return (String) (ai != null ? pm.getApplicationLabel(ai) : packageName);
    }

    public static Drawable getAppIconFromPackage(Context context, String packageName) {
        PackageManager pm = context.getApplicationContext().getPackageManager();
        Drawable drawable = null;
        try {
            ApplicationInfo ai = pm.getApplicationInfo(packageName, 0);
            drawable = pm.getApplicationIcon(ai);
        } catch (Exception e) {
            if(BuildConfig.DEBUG) e.printStackTrace();
        }
        return drawable;
    }
}
