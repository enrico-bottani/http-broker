package com.enricobottani.httpbroker.dto;

import java.util.*;

public class SetCookie {
    private final HashMap<String, String> kvAttributes;
    private final Set<String> attributes;
    private final String name;
    private final String value;

    public SetCookie(HashMap<String, String> kvAttributes, Set<String> attributes, String name, String value) {
        this.kvAttributes = kvAttributes;
        this.attributes = attributes;
        this.name = name;
        this.value = value;
    }

    public static SetCookie parse(String setcookie) {
        HashMap<String, String> values = new HashMap<>();
        Set<String> properties = new HashSet<>();
        String name = "";
        String value = "";
        String[] spit = setcookie.split(";");
        int i = 0;

        for (String token : spit) {
            if (i == 0) {
                name = token.split("=")[0];
                value = token.split("=")[1];
            }
            if (token.split("=").length > 1) {
                values.put(token.split("=")[0], token.split("=")[1]);
            } else
                properties.add(token);
            i++;
        }
        return new SetCookie(values, properties, name, value);
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
    public Set<String> getAttributes() {
        return new HashSet<>(attributes);
    }

    public HashMap<String, String> getKvAttributes() {
        return new HashMap<>(kvAttributes);
    }
}
