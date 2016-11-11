package com.danny.android.androidplugin.apkplugin;

import android.app.Activity;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;

import com.danny.android.pluginsimpl.dynamicload.DLPlugin;

/**
 * Created by danny on 16/11/9.
 */

public class ProxyActivity extends Activity {

    private APKInfo apkinfo;
    private DLPlugin plugin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null) {
            String apkname = bundle.getString(ValueUtil.APK_NAME);
            String classname = bundle.getString(ValueUtil.CLASS_NAME);
            try {
                apkinfo = ApkLoader.loadActivityFromApk(this, apkname);
                plugin = ApkLoader.startActivity(this,apkinfo,classname);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public AssetManager getAssets() {
        if( apkinfo !=  null && apkinfo.assetManager != null )
            return apkinfo.assetManager;
        else
            return super.getAssets();
    }

    @Override
    public Resources getResources() {
        if( apkinfo !=  null && apkinfo.resources != null )
            return apkinfo.resources;
        else
            return super.getResources();
    }

}
