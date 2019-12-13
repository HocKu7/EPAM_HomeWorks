package com.epam;

import com.epam.cargo.Cargo;
import com.epam.cargo.CargoType;
import com.epam.carrier.Carrier;
import com.epam.carrier.CarrierType;
import com.epam.transportation.Transportation;

import java.util.Date;

public class DemoTransportCompany {
    public static void main(String[] args) {

        Cargo apple = new Cargo();
        apple.setId(1L);
        apple.setName("Apple");
        apple.setCargoType(CargoType.FOOD);
        apple.setWeight(4);

        Cargo orrange = new Cargo();
        orrange.setId(2L);
        orrange.setName("Orrange");
        orrange.setCargoType(CargoType.FOOD);
        orrange.setWeight(3);

        Cargo banana = new Cargo();
        banana.setId(3L);
        banana.setName("Banana");
        banana.setCargoType(CargoType.FOOD);
        banana.setWeight(5);

        Carrier company_1 = new Carrier();
        company_1.setId(1L);
        company_1.setName("Company 1");
        company_1.setAddress("Sadovaya");
        company_1.setCarrierType(CarrierType.CAR);


        Carrier company_2 = new Carrier();
        company_2.setId(2L);
        company_2.setName("Company 2");
        company_2.setAddress("Nevskyi");
        company_2.setCarrierType(CarrierType.PLANE);

        Carrier company_3 = new Carrier();
        company_3.setId(3L);
        company_3.setName("Company 3");
        company_3.setAddress("Petrogradskay");
        company_3.setCarrierType(CarrierType.SHIP);

        Date date = new Date();

        Transportation transportation1 = new Transportation();
        transportation1.setBillTo("Ivan Ivanich");
        transportation1.setId(1L);
        transportation1.setCargo(apple);
        transportation1.setCarrier(company_1);
        transportation1.setDescription("some description 1");
        transportation1.setDate(
                new Date(date.getYear(), date.getMonth(), date.getDate())
        );

        Transportation transportation2 = new Transportation();
        transportation2.setBillTo("Petr Petrovich");
        transportation2.setId(2L);
        transportation2.setCargo(orrange);
        transportation2.setCarrier(company_2);
        transportation2.setDescription("some description 2");
        transportation2.setDate(
                new Date(date.getYear(), date.getMonth(), date.getDate(), date.getHours(), date.getMinutes())
        );

        Transportation transportation3 = new Transportation();
        transportation3.setBillTo("Oleg Olegovich");
        transportation3.setId(3L);
        transportation3.setCargo(banana);
        transportation3.setCarrier(company_3);
        transportation3.setDescription("some description 3");
        transportation3.setDate(
                new Date(date.getYear(), date.getMonth(), date.getDate(), date.getHours(), date.getMinutes(),
                        date.getSeconds())
        );

        Transportation transportation4 = new Transportation();
        transportation4.setBillTo("Ivan Ivanich");
        transportation4.setId(4L);
        transportation4.setCargo(apple);
        transportation4.setCarrier(company_2);
        transportation4.setDescription("some description 4");
        transportation4.setDate(
                new Date(12312312312312L)
        );


        apple.setTransportations(
                new Transportation[]{transportation1, transportation4}
        );
        orrange.setTransportations(
                new Transportation[]{transportation2}
        );
        banana.setTransportations(
                new Transportation[]{transportation3}
        );

        company_1.setTransportations(
                new Transportation[]{transportation1}
        );
        company_2.setTransportations(
                new Transportation[]{transportation2, transportation4}
        );
        company_3.setTransportations(
                new Transportation[]{transportation3}
        );

        Storage storage = new Storage();
        storage.addCargo(apple);
        storage.addCargo(banana);
        storage.addCargo(orrange);

        storage.addCarrier(company_1);
        storage.addCarrier(company_2);
        storage.addCarrier(company_3);

        storage.addTransportation(transportation1);
        storage.addTransportation(transportation2);
        storage.addTransportation(transportation3);
        storage.addTransportation(transportation4);

        storage.printAllCargos();
        storage.printAllCarriers();
        storage.printAllTransportation();


    }
}
