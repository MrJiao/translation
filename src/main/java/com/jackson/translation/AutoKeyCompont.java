package com.jackson.translation;

import java.awt.*;

/**
 * Create by: Jackson
 */
public class AutoKeyCompont {


    public void key(int ...keycode) throws AWTException {
        Robot robot = new Robot();
        for (int code : keycode) {
            L.d("press:"+keycode);
            robot.keyPress(code);
        }
        for (int i = 0; i < keycode.length; i++) {
            L.d("release:"+keycode);
            robot.keyRelease(keycode[keycode.length-i-1]);
        }
    }

}
