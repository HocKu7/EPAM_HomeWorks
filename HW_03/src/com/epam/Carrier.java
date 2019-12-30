package com.epam;

public class Carrier {
    private Transport[] haveTransports;
    private Transportation[] serveTransportation;
    private Float availableMoney;
    private String nativeCountry;

    public boolean hasEnougMoney(Float costOfTransportation) {
        //some code
        return true;
    }

    public Transport[] getHaveTransports() {
        //some code
        return haveTransports;
    }

    public Transport[] getHaveAndAllowTransport(Transportation in) {
        //compare has and allow transports
        return new Transport[10];
    }

    public void setTransportOfTransportation(Transport in) {
        serveTransportation[0].setTransportOfTransportation(in);
    }

    public void pushTask(Transportation task) {

    }

    public void popTask(Transportation task) {

    }
}
