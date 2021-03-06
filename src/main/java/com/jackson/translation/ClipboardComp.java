package com.jackson.translation;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;

import static com.jackson.translation.Config.isDebug;

/**
 * Create by: Jackson
 */
public class ClipboardComp {
    // 获取系统剪贴板
    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    /**
     * 把文本设置到剪贴板（复制）
     */
    public void setClipboardString(String text) {
        // 封装文本内容
        Transferable trans = new StringSelection(text);
        // 把文本内容设置到系统剪贴板
        clipboard.setContents(trans, null);

    }


    /**
     * 从剪贴板中获取文本（粘贴）
     */
    public String getClipboardString() {

        if(isDebug){
            L.d("请输入");
            return HappyInputUtil.getInput();
        }else {
            // 获取剪贴板中的内容
            Transferable trans = clipboard.getContents(null);
            if (trans != null) {
                // 判断剪贴板中的内容是否支持文本
                if (trans.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                    try {
                        // 获取剪贴板中的文本内容
                        String text = (String) trans.getTransferData(DataFlavor.stringFlavor);
                        return text;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }


        return null;
    }

}
