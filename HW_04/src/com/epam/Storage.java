package com.epam;

import com.epam.cargo.Cargo;
import com.epam.carrier.Carrier;
import com.epam.transportation.Transportation;

public class Storage {
    private Cargo[] cargos;
    private Carrier[] carriers;
    private Transportation[] transportations;
    private int sizeOfCargos;
    private int sizeOfCarriers;
    private int sizeOfTransportations;
    static final int SIZE_ARRAY = 10;

    Storage() {
        cargos = new Cargo[SIZE_ARRAY];
        carriers = new Carrier[SIZE_ARRAY];
        transportations = new Transportation[SIZE_ARRAY];
    }

    public boolean addCargo(Cargo cargo) {
        boolean haveSpaceForAdd = sizeOfCargos < SIZE_ARRAY;
        if (haveSpaceForAdd) {
            if (pushIntoArray(cargo)) {
                sizeOfCargos++;
                return true;
            }
        }
        return false;
    }

    public boolean addCarrier(Carrier carrier) {
        boolean haveSpaceForAdd = sizeOfCarriers < SIZE_ARRAY;
        if (haveSpaceForAdd) {
            if (pushIntoArray(carrier)) {
                sizeOfCarriers++;
                return true;
            }
        }
        return false;
    }

    public boolean addTransportation(Transportation transportation) {
        boolean haveSpaceForAdd = sizeOfTransportations < SIZE_ARRAY;
        if (haveSpaceForAdd) {
            if (pushIntoArray(transportation)) {
                sizeOfTransportations++;
                return true;
            }
        }
        return false;
    }

    private boolean pushIntoArray(Object item) {
        if (item == null) return false;

        if (Cargo.class.equals(item.getClass())) {
            cargos[sizeOfCargos] = (Cargo) item;
            return true;
        } else if (Carrier.class.equals(item.getClass())) {
            carriers[sizeOfCarriers] = (Carrier) item;
            return true;
        } else if (Transportation.class.equals(item.getClass())) {
            transportations[sizeOfTransportations] = (Transportation) item;
            return true;
        }

        return false;
    }

    public void printAllCargos() {
        for (int i = 0; i < sizeOfCargos; i++) {
            System.out.println(cargos[i]);
        }
    }

    public void printAllCarriers() {
        for (int i = 0; i < sizeOfCarriers; i++) {
            System.out.println(carriers[i]);
        }
    }

    public void printAllTransportation() {
        for (int i = 0; i < sizeOfTransportations; i++) {
            System.out.println(transportations[i]);
        }
    }

}
