package com.danny.android.pluginapktest;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.danny.android.pluginsimpl.dynamicload.DLBasePluginActivity;
import com.danny.android.pluginsimpl.dynamicload.internal.DLIntent;

/**
 * Created by danny on 16/11/11.
 */

public class SecondPage extends DLBasePluginActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec);

        Button btn_click = (Button) that.findViewById(R.id.click_test);
        btn_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(that, "secondpage test",Toast.LENGTH_SHORT).show();
                DLIntent intent = new DLIntent();
                intent.setClassName(that,"com.danny.android.androidplugin.hooks.DemoActivity");
                that.startActivity(intent);
            }
        });
    }
}
