package com.jackson.translation;

import com.melloware.jintellitype.HotkeyListener;
import com.melloware.jintellitype.JIntellitype;

/**
 * Create by: Jackson
 */
public class HotKey {

    public static final int GLOBAL_HOT_KEY = 1;
    public static final int GLOBAL_HOT_KEY2 = 2;

    public void init(HotkeyListener listener){
      //  JIntellitype.getInstance().registerHotKey(GLOBAL_HOT_KEY, JIntellitype.MOD_SHIFT, (int) 'D');
        JIntellitype.getInstance().registerHotKey(GLOBAL_HOT_KEY, JIntellitype.MOD_CONTROL,'P' );
        JIntellitype.getInstance().registerHotKey(GLOBAL_HOT_KEY2,JIntellitype.MOD_CONTROL,'Q');
        JIntellitype.getInstance().addHotKeyListener(listener);

    }
}
