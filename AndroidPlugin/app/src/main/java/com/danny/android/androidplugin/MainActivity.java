package com.danny.android.androidplugin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.danny.android.androidplugin.loadjar.JarLoader;
import com.danny.android.impl.JarInterfaceImpl;

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
                JarInterfaceImpl jarcall = (JarInterfaceImpl) JarLoader.loadJar(getApplicationContext(),
                        "pluginjar.jar",
                        "com.danny.android.jarplugins.PluginTest");
                String tips = "load jar fail!";
                if( jarcall != null ){
                    tips = "load jar suc!="+jarcall.getClassName();
                }
                tv.setText(tips);
            }
        });

        Button btn_loadapk = (Button) findViewById(R.id.btn_loadapk);
        btn_loadapk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
