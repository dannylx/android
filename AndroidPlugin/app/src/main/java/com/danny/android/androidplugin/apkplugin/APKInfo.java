package com.danny.android.androidplugin.apkplugin;

import android.app.Application;
import android.content.res.AssetManager;
import android.content.res.Resources;

/**
 * Created by danny on 16/11/4.
 */

public class APKInfo {
    public transient PluginClassLoader classLoader;
    public transient Application application;
    public transient AssetManager assetManager;
    public transient Resources resources;
}
