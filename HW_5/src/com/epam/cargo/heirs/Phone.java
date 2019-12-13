package com.epam.cargo.heirs;

import com.epam.cargo.Cargo;

public class Phone extends Cargo {
    private String nameOfModel;
    private int verson;
    private float diogonal;

    public String getNameOfModel() {
        return nameOfModel;
    }

    public void setNameOfModel(String nameOfModel) {
        this.nameOfModel = nameOfModel;
    }

    public int getVerson() {
        return verson;
    }

    public void setVerson(int verson) {
        this.verson = verson;
    }

    public float getDiogonal() {
        return diogonal;
    }

    public void setDiogonal(float diogonal) {
        this.diogonal = diogonal;
    }
}
