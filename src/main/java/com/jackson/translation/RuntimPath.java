package com.jackson.translation;

import java.io.File;
import static com.jackson.translation.Config.isDebug;
/**
 * Create by: Jackson
 */
public class RuntimPath {

    public String getRuntimePath() {
        if(!isDebug){
            String path = RuntimPath.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            return new File(path).getParent();
        }else {
            return System.getProperty("user.dir");
        }

    }
}
