package com.example.laucher;

import android.graphics.drawable.Drawable;

public class AppInfo {
    private String appName;
    private String packageName;
    private String className;
    private Drawable appIcon;

    public AppInfo(String appName, String packageName, String className, Drawable appIcon) {
        this.appName = appName;
        this.packageName = packageName;
        this.className = className;
        this.appIcon = appIcon;
    }

    public String getAppName() {
        return appName;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getClassName() {
        return className;
    }

    public Drawable getAppIcon() {
        return appIcon;
    }
}
