package com.epam;

public enum Transport {
    CAR(30, 20),
    TRAIN(1000, 700),
    AIRPLANE(100, 300),
    SHIP(500, 400);

    private float maxCarryWeidht;
    private float maxCarryVolume;

    Transport(float maxCarryWeidht, float maxCarryVolume) {
        this.maxCarryWeidht = maxCarryWeidht;
        this.maxCarryVolume = maxCarryVolume;
    }
}
