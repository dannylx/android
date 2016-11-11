package com.danny.android.androidplugin;

import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.danny.android.androidplugin.utils.FileUtile;
import com.danny.android.pluginsimpl.dynamicload.internal.DLIntent;
import com.danny.android.pluginsimpl.dynamicload.internal.DLPluginManager;
import com.danny.android.pluginsimpl.utils.DLUtils;

public class MainActivity extends AppCompatActivity {

    private TextView tv = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    protected void init(){
        tv = (TextView) findViewById(R.id.message);
        Button btn_loadjar = (Button) findViewById(R.id.btn_loadjar);
        btn_loadjar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                JarInterfaceImpl jarcall = (JarInterfaceImpl) JarLoader.loadJar(getApplicationContext(),
//                        "pluginjar.jar",
//                        "com.danny.android.jarplugins.PluginTest");
//                String tips = "load jar fail!";
//                if( jarcall != null ){
//                    tips = "load jar suc!="+jarcall.getClassName();
//                }
//                tv.setText(tips);
            }
        });

        Button btn_hookactivity = (Button) findViewById(R.id.btn_hookactivity);
        btn_hookactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                try {
//                    HooksAPI.attachActivityContext(MainActivity.this);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//                Intent intent = new Intent();
//                intent.setClass(getBaseContext(), DemoActivity.class);
//                startActivity(intent);
            }
        });

        Button btn_loadapk = (Button) findViewById(R.id.btn_loadapk);
        btn_loadapk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Intent intent = new Intent();
//                intent.setClass(getBaseContext(), ProxyActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putString(ValueUtil.APK_NAME,"app-debug.apk");
//                bundle.putString(ValueUtil.CLASS_NAME,"com.danny.android.pluginapktest.MainActivity");
//                intent.putExtras(bundle);
//                startActivity(intent);

                String strLoaderFilePath = FileUtile.CopyAssertJarToOwnerFile(MainActivity.this,
                        "app-debug.apk","apks", "app-debug.apk");
                PackageInfo packageInfo = DLUtils.getPackageInfo(MainActivity.this, strLoaderFilePath);
                DLPluginManager.getInstance(MainActivity.this).loadApk(strLoaderFilePath);
                DLPluginManager pluginManager = DLPluginManager.getInstance(MainActivity.this);
                pluginManager.startPluginActivity(MainActivity.this, new DLIntent(packageInfo.packageName));
            }
        });
    }

}
