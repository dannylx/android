package com.danny.android.androidplugin.hooks;

import android.app.Activity;
import android.os.Bundle;

import com.danny.android.androidplugin.R;

/**
 * Created by danny on 16/11/4.
 */

public class DemoActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hookdemo);
    }

}
