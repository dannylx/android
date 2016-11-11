package com.danny.android.androidplugin.apkplugin;

import dalvik.system.DexClassLoader;

/**
 * Created by danny on 16/11/4.
 */

public class PluginClassLoader extends DexClassLoader {
    public PluginClassLoader(String dexPath, String optimizedDirectory, String librarySearchPath, ClassLoader parent) {
        super(dexPath, optimizedDirectory, librarySearchPath, parent);
    }
}
