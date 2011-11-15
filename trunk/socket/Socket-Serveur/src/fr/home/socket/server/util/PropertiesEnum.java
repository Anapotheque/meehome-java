package fr.home.socket.server.util;

public enum PropertiesEnum {

    PORT("server.port");

    private final String value;

    PropertiesEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static PropertiesEnum fromValue(String value) {
        return valueOf(value);
    }
}
