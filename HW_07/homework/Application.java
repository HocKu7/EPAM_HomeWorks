package ru.epam.javacore.lesson_6_collections.homework;


import ru.epam.javacore.lesson_6_collections.homework.cargo.domain.Cargo;
import ru.epam.javacore.lesson_6_collections.homework.cargo.domain.CargoType;
import ru.epam.javacore.lesson_6_collections.homework.cargo.service.impl.CargoCollectionServiceImpl;

public class Application {

    public static Cargo[] genCargo()
    {
        Cargo cargo1= new Cargo();
        cargo1.setName("cargo1");
        cargo1.setCargoType(CargoType.FOOD);
        cargo1.setWeight(100);

        Cargo cargo2= new Cargo();
        cargo2.setName("cargo2");
        cargo2.setCargoType(CargoType.CLOTHERS);
        cargo2.setWeight(200);

        Cargo cargo3= new Cargo();
        cargo3.setName("cargo3");
        cargo3.setCargoType(CargoType.FOOD);
        cargo3.setWeight(300);

        return new Cargo[]{cargo1,cargo2,cargo3};
    }
    public static void main(String[] args) {
        CargoCollectionServiceImpl cargoService = new CargoCollectionServiceImpl();
        Cargo[] arrayOfCargo= genCargo();

        cargoService.add(arrayOfCargo[0]);
        cargoService.add(arrayOfCargo[1]);
        cargoService.add(arrayOfCargo[2]);

        cargoService.deleteById(arrayOfCargo[1].getId());

        Cargo cargo1=cargoService.getById(1L);


        cargoService.printAllCargos();

    }
}
