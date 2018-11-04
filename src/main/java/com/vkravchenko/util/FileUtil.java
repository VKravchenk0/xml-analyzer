package com.vkravchenko.util;

import java.io.File;

public class FileUtil {

    public static File openFile(String fileName) {
        File file = new File(fileName);
        if (file.exists()) {
            return file;
        }
        return null;
    }

}
