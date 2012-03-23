package fr.home.socket.client.model;

import org.apache.log4j.Logger;

public class Friend {

    static Logger logger = Logger.getLogger(Friend.class);

    private String login;

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

}
