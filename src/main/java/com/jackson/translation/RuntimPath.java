package com.jackson.translation;

import java.io.File;

/**
 * Create by: Jackson
 */
public class RuntimPath {

    public String getRuntimePath() {
        if(Main.isPackage){
            String path = RuntimPath.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            return new File(path).getParent();
        }else {
            return System.getProperty("user.dir");
        }

    }
}
