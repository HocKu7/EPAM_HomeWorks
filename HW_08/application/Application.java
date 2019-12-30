package ru.epam.javacore.lesson_7_collections_continue_map.homework.application;

import ru.epam.javacore.lesson_7_collections_continue_map.homework.application.serviceholder.ServiceHolder;
import ru.epam.javacore.lesson_7_collections_continue_map.homework.application.serviceholder.StorageType;
import ru.epam.javacore.lesson_7_collections_continue_map.homework.cargo.service.CargoService;
import ru.epam.javacore.lesson_7_collections_continue_map.homework.carrier.service.CarrierService;
import ru.epam.javacore.lesson_7_collections_continue_map.homework.common.solutions.utils.CollectionUtils;
import ru.epam.javacore.lesson_7_collections_continue_map.homework.storage.initor.InMemoryStorageInitor;
import ru.epam.javacore.lesson_7_collections_continue_map.homework.storage.initor.StorageInitor;
import ru.epam.javacore.lesson_7_collections_continue_map.homework.transportation.domain.Transportation;
import ru.epam.javacore.lesson_7_collections_continue_map.homework.transportation.service.TransportationService;

public class Application {

  private static final String SEPARATOR = "--------------";
  private static CargoService cargoService;
  private static CarrierService carrierService;
  private static TransportationService transportationService;

  public static void main(String[] args) {
    ServiceHolder.initServiceHolder(StorageType.COLLECTION);
    cargoService = ServiceHolder.getInstance().getCargoService();
    carrierService = ServiceHolder.getInstance().getCarrierService();
    transportationService = ServiceHolder.getInstance().getTransportationService();

    StorageInitor storageInitor = new InMemoryStorageInitor();
    storageInitor.initStorage();

    //printStorageData();
    //doSearchOperations();
    //doSortOperations();
    cargoService.update(1L,"updated NAME");
    cargoService.update(2L, 100);

    doSortOperations();
  }

  private static void printStorageData() {
    System.out.println("ALL CARGOS");
    cargoService.printAll();
    printSeparator();

    System.out.println("ALL CARRIERS");
    carrierService.printAll();
    printSeparator();

    System.out.println("ALL TRANSPOORTATIONS");
    transportationService.printAll();
    printSeparator();
  }

  private static void doSearchOperations() {
    System.out.println("SEARCH CARGO BY ID = 1");
    System.out.println(cargoService.getById(1L));
    printSeparator();

    System.out.println("SEARCH CARRIER BY ID = 8");
    System.out.println(carrierService.getById(8L));
    printSeparator();

    System.out.println("SEARCH CARGOES BY NAME = 'Clothers_Name_1'");
    CollectionUtils.printCollection(cargoService.getByName("Clothers_Name_1"));
    printSeparator();

    System.out.println("SEARCH CARRIERS BY NAME = 'Carrier_Name'");
    CollectionUtils.printCollection(carrierService.getByName("Carrier_Name"));
  }
  private static void doSortOperations() {
    System.out.println("SORT CARGO BY WEIGHT");
    cargoService.sort(CargoService.SortType.WEIGHT);
    cargoService.printAll();
    printSeparator();

    System.out.println("SORT CARGO BY NAME");
    cargoService.sort(CargoService.SortType.NAME);
    cargoService.printAll();
    printSeparator();

    System.out.println("SORT CARGO BY NAME AND WEIGHT");
    cargoService.sort(CargoService.SortType.NAMEANDWEIGHT);
    cargoService.printAll();
    printSeparator();

  }

  private static void printSeparator() {
    System.out.println(SEPARATOR);
  }

}
