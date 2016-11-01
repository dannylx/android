package com.danny.android.androidplugin.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * Created by danny on 16/10/31.
 */

public class FileUtile {
    public static String CopyAssertJarToOwnerFile(Context context, String assertfilename, String forlder, String desfilename) {
        try {

            final File optimizedDexOutputPath = context.getDir(forlder, Context.MODE_PRIVATE);

            File file = new File(optimizedDexOutputPath.getAbsolutePath() + File.separator + desfilename);
            if(file.exists())
                return file.getAbsolutePath();

            InputStream inputStream = context.getAssets().open(assertfilename);
            file.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            byte buffer[] = new byte[1024];
            int len = 0;
            while ((len = inputStream.read(buffer)) > 0) {
                fileOutputStream.write(buffer, 0, len);
            }
            fileOutputStream.close();
            fileOutputStream.close();
            return file.getAbsolutePath();
        } catch (Exception e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static String CopyAssertJarToExternalFile(Context context, String assertfilename, String forlder, String desfilename) {
        try {

            File file = new File(Environment.getExternalStorageDirectory()
                    .toString() + File.separator + forlder + desfilename);
//            if (file.exists()) {
//                return;
//            }

            InputStream inputStream = context.getAssets().open(assertfilename);
            file.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            byte buffer[] = new byte[1024];
            int len = 0;
            while ((len = inputStream.read(buffer)) > 0) {
                fileOutputStream.write(buffer, 0, len);
            }
            fileOutputStream.close();
            fileOutputStream.close();
            return file.getAbsolutePath();
        } catch (Exception e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
