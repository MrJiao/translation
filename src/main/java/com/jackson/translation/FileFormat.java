package com.jackson.translation;

import java.io.File;

/**
 * Create by: Jackson
 */
public class FileFormat {
    public static File format(File file){
        return new File(file.toURI().getPath());
    }
}
