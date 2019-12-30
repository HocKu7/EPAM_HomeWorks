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
    static final int ARRAY_CAPACITY = 2;


    Storage() {
        cargos = new Cargo[ARRAY_CAPACITY];
        carriers = new Carrier[ARRAY_CAPACITY];
        transportations = new Transportation[ARRAY_CAPACITY];
    }

    public boolean addCargo(Cargo cargo) {
        boolean haveSpaceForAdd = sizeOfCargos % (ARRAY_CAPACITY - 1) != 0;
        if (!haveSpaceForAdd) {
            Cargo[] newCargo = new Cargo[sizeOfCargos + ARRAY_CAPACITY];
            copyArray(cargos, newCargo);
            cargos = newCargo;
        }

        if (pushIntoArray(cargo)) {
            sizeOfCargos++;
            return true;
        }

        return false;
    }

    public boolean addCarrier(Carrier carrier) {
        boolean haveSpaceForAdd = sizeOfCarriers % (ARRAY_CAPACITY - 1) != 0;
        if (!haveSpaceForAdd) {
            Carrier[] newCarrier = new Carrier[sizeOfCarriers + ARRAY_CAPACITY];
            copyArray(carriers, newCarrier);
            carriers = newCarrier;
        }
        if (pushIntoArray(carrier)) {
            sizeOfCarriers++;
            return true;
        }

        return false;
    }

    public boolean addTransportation(Transportation transportation) {
        boolean haveSpaceForAdd = sizeOfTransportations % (ARRAY_CAPACITY - 1) != 0;
        if (!haveSpaceForAdd) {
            Transportation[] newTransportation = new Transportation[sizeOfCarriers + ARRAY_CAPACITY];
            copyArray(transportations, newTransportation);
            transportations = newTransportation;
        }
        if (pushIntoArray(transportation)) {
            sizeOfTransportations++;
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

    public Cargo getCargoById(long id) {
        for (Cargo item : cargos) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    public Carrier getCarrierById(long id) {
        for (Carrier item : carriers) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    public Transportation getTransportationById(long id) {
        for (Transportation item : transportations) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    public Cargo getCargoByName(String name) {
        for (Cargo item : cargos) {
            if (item.getName().equals(name)) {
                return item;
            }
        }
        return null;
    }

    public Carrier getCarrierByName(String name) {
        for (Carrier item : carriers) {
            if (item.getId().equals(name)) {
                return item;
            }
        }
        return null;
    }

    public Transportation getTransportationByName(String name) {
        for (Transportation item : transportations) {
            if (item.getId().equals(name)) {
                return item;
            }
        }
        return null;
    }

    public Cargo[] getAllCargo() {
        Cargo[] newCargos = new Cargo[sizeOfCargos];
        copyArray(cargos, newCargos);
        return newCargos;
    }

    public Carrier[] getAllCarrier() {
        Carrier[] newCarrier = new Carrier[sizeOfCarriers];
        copyArray(cargos, newCarrier);
        return newCarrier;
    }

    public Transportation[] getAllTransportation() {
        Transportation[] newTransportation = new Transportation[sizeOfTransportations];
        copyArray(cargos, newTransportation);
        return newTransportation;
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


    private static void copyArray(Object[] src, Object[] dest) {
        for (int i = 0; i < src.length; i++) {
            dest[i] = src[i];
        }
    }


}
