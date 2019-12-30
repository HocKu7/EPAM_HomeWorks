package com.epam;

public class Transportation {
    private Product[] product;
    private Transport[] allowTransport;
    private Float costOfTransportation;
    private Transport transportOfTransportation;

    public Float calculateCost() {
        //some code
        return (float) 1;
    }

    public Transport[] getAllowTransport() {
        return allowTransport;
    }

    public void setTransportOfTransportation(Transport transportOfTransportation) {
        this.transportOfTransportation = transportOfTransportation;
    }
}
