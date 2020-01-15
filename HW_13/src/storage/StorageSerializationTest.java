package storage;

import application.serviceholder.ServiceHolder;
import application.serviceholder.StorageType;
import cargo.service.CargoService;
import carrier.service.CarrierService;
import common.business.exception.checked.InitStorageException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import storage.initor.InitStorageType;
import storage.initor.StorageInitor;
import transportation.service.TransportationService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static storage.initor.StorageInitorFactory.getStorageInitor;

public class StorageSerializationTest {
    CargoService cargoService;
    CarrierService carrierService;
    TransportationService transportationService;
    StorageInitor storageInitor;
    @Before
    public void init() throws InitStorageException {
        ServiceHolder.initServiceHolder(StorageType.COLLECTION);
        cargoService = ServiceHolder.getInstance().getCargoService();
        carrierService = ServiceHolder.getInstance().getCarrierService();
        transportationService = ServiceHolder.getInstance().getTransportationService();
        //StorageInitor storageInitor = getStorageInitor(InitStorageType.TEXT_FILE);
        storageInitor = getStorageInitor(InitStorageType.SAX_PARSE);
        storageInitor.initStorage();
    }

    @Test
    public void startTest() {

        //создаем тестовые данные

        Path file = null;

        //создаем список expected и заполняем его данными нашего метода
        Storage expected = Storage.INSTANCE;
        try {
            file = Files.createTempFile("HW_13", ".txt");
            ObjectOutput objectOutput=new ObjectOutputStream(new FileOutputStream(file.toFile()));
            objectOutput.writeObject(Storage.INSTANCE);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        //создаем список actual в него помещаем данные для сравнения
        //то что мы предпологиаем метод должен вернуть
        Storage actual;
        actual =readStorageFromFile(file.toFile().getAbsolutePath());

        //запускаем тест, в случае если список expected и actual не будут равны
        //тест будет провален, о результатах теста читаем в консоли
        Assert.assertEquals(expected, actual);

    }
    private static Storage readStorageFromFile(String file) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))) {
            Object o = inputStream.readObject();
            return (Storage) o;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return null;
        }
    }
}