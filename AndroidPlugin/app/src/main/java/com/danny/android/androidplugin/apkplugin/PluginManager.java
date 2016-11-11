package com.danny.android.androidplugin.apkplugin;

import java.util.Hashtable;

/**
 * Created by danny on 16/11/4.
 */

public class PluginManager {
    // APKplugin map
    protected Hashtable<String,APKInfo> mApkMap = new Hashtable();

    // 获取apk插件信息
    public APKInfo getAPKPlugin(String APKName){
        return mApkMap.get(APKName);
    }

    // 扫描APK文件,加载APK信息
    public void ScanAPKPlugins(){

    }

}
