package com.danny.android.androidplugin.loadjar;

import android.content.Context;

import com.danny.android.androidplugin.utils.FileUtile;

import java.io.File;

import dalvik.system.DexClassLoader;

/**
 * Created by danny on 16/11/1.
 */

public class JarLoader {
    public static Object loadJar(Context context, String tagJarFileName, String tagClassName){
        Object loadobj = null;

        // 释放jar包
        String strLoaderFilePath = FileUtile.CopyAssertJarToOwnerFile(context,tagJarFileName,"jars", tagJarFileName);
        final File optimizedDexOutputPath = context.getDir("dexjars", Context.MODE_PRIVATE);

        try {
            //定义DexClassLoader
            //第一个参数：是dex压缩文件的路径
            //第二个参数：是dex解压缩后存放的目录
            //第三个参数：是C/C++依赖的本地库文件目录,可以为null
            //第四个参数：是上一级的类加载器
            DexClassLoader cl = new DexClassLoader(strLoaderFilePath,
                    optimizedDexOutputPath.getAbsolutePath(), null, context.getClassLoader());
            // 载入JarLoader类， 并且通过反射构建JarLoader对象， 然后调用sayHi方法
            Class libProviderClazz = cl.loadClass(tagClassName);
            loadobj = libProviderClazz.newInstance();
        } catch (Exception exception) {
            // Handle exception gracefully here.
            exception.printStackTrace();
        }

        return loadobj;
    }
}
