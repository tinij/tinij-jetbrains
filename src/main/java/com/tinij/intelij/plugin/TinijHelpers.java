package com.tinij.intelij.plugin;

import com.intellij.openapi.vfs.VirtualFile;
import com.tinij.intelij.plugin.utils.TinijConstantsHandler;

import java.math.BigDecimal;

class TinijHelpers {

    static BigDecimal getCurrentTime() {
        return new BigDecimal(String.valueOf(System.currentTimeMillis() / 1000.0)).setScale(4, BigDecimal.ROUND_HALF_UP);
    }

    static boolean enoughTimePassed(BigDecimal currentTime) {
        return Tinij.lastTime.add(TinijConstantsHandler.FREQUENCY).compareTo(currentTime) < 0;
    }

    static boolean shouldLogFile(VirtualFile file) {
        if (file == null || file.getUrl().startsWith("mock://")) {
            return false;
        }
        String filePath = file.getPath();
        if (filePath.equals("atlassian-ide-plugin.xml") || filePath.contains("/.idea/workspace.xml")) {
            return false;
        }
        return true;
    }
}
