package com.nextlevel.monitoring.backend.core.security;

import java.util.HashMap;
import java.util.Map;

public class SecurityHelper {

    private static final InheritableThreadLocal<Map<String,String>> generalData = new InheritableThreadLocal();

    public static Map<String,String> getGeneralData() {
        Map<String,String> map = generalData.get();
        if(map == null) {
            map = new HashMap<>();
            setGeneralData(map);
        }
        return map;
    }

    public static void setGeneralData(Map<String,String> map) {
        generalData.set(map);
    }

    private SecurityHelper() {

    }

    public static String getClient() {
        return getGeneralData().get("clientId");
    }

    public static String getUserName() {
        return getGeneralData().get("userName");
    }

    public static void setClient(String clientId) {
        getGeneralData().put("clientId", clientId);
    }

    public static void setUserName(String userName) {
        getGeneralData().put("userName", userName);
    }
}
