package net.thucydides.showcase.junit.model;

public class Client {
    private String pin;
    private String client;
    private String user_action;
    private int cap_set;
    private String agent;

    public Client(String pin, String client, String user_action, int cap_set, String agent) {
        this.pin = pin;
        this.client = client;
        this.user_action = user_action;
        this.cap_set = cap_set;
        this.agent = agent;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getUser_action() {
        return user_action;
    }

    public void setUser_action(String user_action) {
        this.user_action = user_action;
    }

    public int getCap_set() {
        return cap_set;
    }

    public void setCap_set(int cap_set) {
        this.cap_set = cap_set;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }
}
