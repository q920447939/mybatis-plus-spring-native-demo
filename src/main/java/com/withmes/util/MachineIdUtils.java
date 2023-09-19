package com.withmes.util;


public class MachineIdUtils {
    private MachineIdUtils() {}

    public static final String SYS_PROPERTY_OS_NAME = "os.name";


    public static final String WINDOWS_ID = "Windows";
    public static final String DEFAULT_SERVER_UUID = "MachineId1";

    public static String getUuid() {
        if (System.getProperty(SYS_PROPERTY_OS_NAME).startsWith(WINDOWS_ID)) {
            return WINDOWS_ID;
        } else {
            return getServerUuid();
        }
    }

    public static String getServerUuid() {
        // 待实现
        return DEFAULT_SERVER_UUID;
    }
}
