package com.danny.android.androidplugin.apkplugin;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;

import com.danny.android.androidplugin.utils.FileUtile;
import com.danny.android.pluginsimpl.dynamicload.DLPlugin;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * Created by danny on 16/11/6.
 */

public class ApkLoader {
    public static APKInfo loadActivityFromApk(Context context, String tagApkFileName) throws Exception {
        // 释放jar包
        String strLoaderFilePath = FileUtile.CopyAssertJarToOwnerFile(context,tagApkFileName,"apks", tagApkFileName);
        final File optimizedDexOutputPath = context.getDir("dexapks", Context.MODE_PRIVATE);

        //定义DexClassLoader
        //第一个参数：是dex压缩文件的路径
        //第二个参数：是dex解压缩后存放的目录
        //第三个参数：是C/C++依赖的本地库文件目录,可以为null
        //第四个参数：是上一级的类加载器
        PluginClassLoader cl = new PluginClassLoader(strLoaderFilePath,
                optimizedDexOutputPath.getAbsolutePath(), null, context.getClassLoader());


        AssetManager assetManager = AssetManager.class.newInstance();
        Method addAssetPath = assetManager.getClass().getMethod("addAssetPath", String.class);
        addAssetPath.invoke(assetManager, strLoaderFilePath);

        Resources superRes = context.getResources();
        Resources resources = new Resources(assetManager, superRes.getDisplayMetrics(),
                superRes.getConfiguration());
//        mTheme = resources.newTheme();
//        mTheme.setTo(context.getTheme());



        APKInfo apkInfo = new APKInfo();
        apkInfo.classLoader = cl;
        apkInfo.assetManager = assetManager;
        apkInfo.resources = resources;

        return apkInfo;
    }

    public static DLPlugin startActivity(Context context, APKInfo apkInfo, String tagActivityName) throws Exception{
        if( apkInfo == null ){
            return null;
        }

        // 载入类
        Class<?> libProviderClazz = apkInfo.classLoader.loadClass(tagActivityName);
        Constructor constructor = libProviderClazz.getConstructor(new Class[] {});
        Object loadobj = constructor.newInstance(new Object[] {});
        Method setProxy = libProviderClazz.getMethod("setProxy",
                new Class[] { Activity.class }); // 最重要的方法;需要在调用onCreate方法之前调用;设置代理Activity
        setProxy.setAccessible(true); // 设置方法访问权限
        setProxy.invoke(loadobj, new Object[] { context }); // 调用方法

        Method onCreate = libProviderClazz.getDeclaredMethod("onCreate",
                new Class[] { Bundle.class }); // 通过反射,得到onCreate方法
        onCreate.setAccessible(true); // 设置方法访问权限
        onCreate.invoke(loadobj, new Object[] { new Bundle() }); // 调用onCreate方法

        return (DLPlugin)loadobj;
    }
}
