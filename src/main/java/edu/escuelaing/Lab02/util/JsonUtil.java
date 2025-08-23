package edu.escuelaing.Lab02.util;


import java.util.*;
import java.lang.reflect.*;

public class JsonUtil {

    // Convierte cualquier objeto a JSON
    public static String toJson(Object obj) {
        if (obj == null) return "null";

        if (obj instanceof String) {
            return "\"" + escape((String) obj) + "\"";
        } else if (obj instanceof Number || obj instanceof Boolean) {
            return obj.toString();
        } else if (obj instanceof Collection) {
            return collectionToJson((Collection<?>) obj);
        } else if (obj.getClass().isArray()) {
            return arrayToJson(obj);
        } else if (obj instanceof Map) {
            return mapToJson((Map<?, ?>) obj);
        } else {
            return objectToJson(obj);
        }
    }



    private static String collectionToJson(Collection<?> col) {
        StringBuilder sb = new StringBuilder("[");
        boolean first = true;
        for (Object o : col) {
            if (!first) sb.append(",");
            sb.append(toJson(o));
            first = false;
        }
        sb.append("]");
        return sb.toString();
    }

    private static String arrayToJson(Object array) {
        StringBuilder sb = new StringBuilder("[");
        int length = Array.getLength(array);
        for (int i = 0; i < length; i++) {
            if (i > 0) sb.append(",");
            sb.append(toJson(Array.get(array, i)));
        }
        sb.append("]");
        return sb.toString();
    }

    private static String mapToJson(Map<?, ?> map) {
        StringBuilder sb = new StringBuilder("{");
        boolean first = true;
        for (Map.Entry<?, ?> e : map.entrySet()) {
            if (!first) sb.append(",");
            sb.append(toJson(e.getKey().toString())).append(":").append(toJson(e.getValue()));
            first = false;
        }
        sb.append("}");
        return sb.toString();
    }

    private static String objectToJson(Object obj) {
        StringBuilder sb = new StringBuilder("{");
        boolean first = true;
        for (Field field : obj.getClass().getDeclaredFields()) {
            try {
                field.setAccessible(true);
                Object value = field.get(obj);
                if (!first) sb.append(",");
                sb.append(toJson(field.getName())).append(":").append(toJson(value));
                first = false;
            } catch (IllegalAccessException e) {
            }
        }
        sb.append("}");
        return sb.toString();
    }

    private static String escape(String s) {
        return s.replace("\"", "\\\"").replace("\n", "\\n").replace("\r", "\\r");
    }
}
