package com.danny.android.jarplugins;

import com.danny.android.impl.JarInterfaceImpl;

public class PluginTest implements JarInterfaceImpl {
    @Override
    public String getClassName() {
        return PluginTest.class.getName();
    }
}
