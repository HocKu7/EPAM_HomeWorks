package com.epam.cargo.heirs;

import com.epam.cargo.Cargo;

public class Computer  extends Cargo {
    private String versionOS;
    private String nameOfCPU;
    private int numberOfCPU;

    public String getVersionOS() {
        return versionOS;
    }

    public void setVersionOS(String versionOS) {
        this.versionOS = versionOS;
    }

    public String getNameOfCPU() {
        return nameOfCPU;
    }

    public void setNameOfCPU(String nameOfCPU) {
        this.nameOfCPU = nameOfCPU;
    }

    public int getNumberOfCPU() {
        return numberOfCPU;
    }

    public void setNumberOfCPU(int numberOfCPU) {
        this.numberOfCPU = numberOfCPU;
    }
}
