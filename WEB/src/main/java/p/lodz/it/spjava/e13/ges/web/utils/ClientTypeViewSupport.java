package p.lodz.it.spjava.e13.ges.web.utils;

import java.util.EnumMap;
import java.util.Map;

public class ClientTypeViewSupport {
    public static enum ClientType {STANDARD, ADVANCED, BUSINESS, ARCHIVED}

    public static Map<ClientType,String> getI18nClientTypes() {
        EnumMap<ClientType,String> result = new EnumMap<>(ClientType.class);
        for (ClientType item: ClientType.values()) {
            result.put(item, I18nUtils.getMessage(item.name()));
        }
        return result;
    }
}
