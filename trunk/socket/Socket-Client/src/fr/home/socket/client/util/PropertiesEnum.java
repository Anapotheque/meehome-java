package fr.home.socket.client.util;

public enum PropertiesEnum {

    IP("server.ip"), PORT("server.port"), LOGIN("client.login"), STOP_CLIENT("client.stop"), MODE_CONSOLE("client.modeConsole");

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
