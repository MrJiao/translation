package com.jackson.translation;

import com.melloware.jintellitype.HotkeyListener;
import com.melloware.jintellitype.JIntellitype;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;

/**
 * Create by: Jackson
 */
public class MyKey implements HotkeyListener {
    LinkedBlockingQueue<String> queue ;

    public MyKey(LinkedBlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void onHotKey(int identifier) {
        switch (identifier) {
            case HotKey.GLOBAL_HOT_KEY:
                queue.offer("123");
                break;
            case HotKey.GLOBAL_HOT_KEY2:
                L.d("释放按键成功");
                JIntellitype.getInstance().unregisterHotKey(HotKey.GLOBAL_HOT_KEY);
                JIntellitype.getInstance().unregisterHotKey(HotKey.GLOBAL_HOT_KEY2);
                break;
        }
    }


}
