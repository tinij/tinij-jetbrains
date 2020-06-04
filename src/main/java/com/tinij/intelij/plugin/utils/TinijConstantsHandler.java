package com.tinij.intelij.plugin.utils;

import java.math.BigDecimal;

public class TinijConstantsHandler {
    public static String IDE_VERSION;
    public static String IDE_NAME;
    public static String PLUGIN_VERSION;
    public static final BigDecimal FREQUENCY = new BigDecimal(2 * 60);

    public static String ACTIVITY_URL = "https://api.tinij.com/v1/collector/activity/bulk";
}
