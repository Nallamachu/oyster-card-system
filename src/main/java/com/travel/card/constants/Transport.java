package com.travel.card.constants;

public enum Transport {
    BUS("Bus"),
    TUBE("Tube");

    private String transport;
    private Transport(String transport)
    {
        this.transport = transport;
    }
    public String getTransport() {
        return transport;
    }
}
