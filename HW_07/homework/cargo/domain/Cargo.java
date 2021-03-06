package ru.epam.javacore.lesson_6_collections.homework.cargo.domain;

import ru.epam.javacore.lesson_4_oop_continue.homework.transportation.Transportation;
import ru.epam.javacore.lesson_6_collections.homework.common.business.domain.BaseEntity;

import java.util.Arrays;

public class Cargo extends BaseEntity {

  protected String name;
  protected int weight;
  protected Transportation[] transportations;
  protected CargoType cargoType;

  public Cargo() {
    cargoType = this.getCargoType();
  }

  public CargoType getCargoType(){return cargoType;};

  public void setCargoType(CargoType type){this.cargoType=type;};

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getWeight() {
    return weight;
  }

  public void setWeight(int weight) {
    this.weight = weight;
  }

  public Transportation[] getTransportations() {
    return transportations;
  }

  public void setTransportations(Transportation[] transportations) {
    this.transportations = transportations;
  }

  @Override
  public String toString() {
    return "Cargo{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", weight=" + weight +
        ", cargoType=" + cargoType +
        ", transportations=" + Arrays.toString(transportations) +
        '}';
  }
}
