package storage.initor;

import application.serviceholder.ServiceHolder;
import cargo.domain.Cargo;
import cargo.domain.ClothersCargo;
import cargo.domain.FoodCargo;
import carrier.domain.Carrier;
import carrier.domain.CarrierType;
import common.solutions.utils.JavaUtilDataUtils;
import transportation.domain.Transportation;
import common.business.domain.BaseEntity;

import java.io.*;
import java.util.*;


public class InTextFileStorageInitor implements StorageInitor {
    private static final String FILE_PATH = "resource/TextMemory/storage.txt";
    private static final String STOP_SYMBOL = "}";

    @Override
    public void initStorage() {

        if(readFileStorage(FILE_PATH))
        {
            System.out.println("Read file is Complete!");
        }
        else{
            System.out.println("Read from file is fail");
        }

    }

    public  boolean readFileStorage(String path) {

        File inputFile = new File(FILE_PATH);
        Map<String, BaseEntity> mapOfEntity = new HashMap<>();
        List<ParsedTransportation> listOfTransportation = null;
        Map<String, Cargo> mapOfCargo = null;
        Map<String, Carrier> mapOfCarrier = null;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                switch (line) {
                    case "Cargo{": {
                        mapOfCargo = readCargoFromFile(bufferedReader);
                        break;
                    }
                    case "Carriers{": {
                        mapOfCarrier = readCarriersFromFile(bufferedReader);
                        break;
                    }
                    case "Transportation{": {
                        listOfTransportation = readTransportationFromFile(bufferedReader);
                        break;
                    }
                }
            }
            setReffBetweenEntries(mapOfCargo, mapOfCarrier, listOfTransportation);
            persistCargo(mapOfCargo.values());
            persistCarriers(mapOfCarrier.values());
            List<Transportation> transportationList = getTransportationsFromParsedObject(listOfTransportation);
            persistTransportations(transportationList);
        } catch (IOException e) {
            System.out.println("General cycle");
            return false;
        }

        return true;
    }

    private List<Transportation> getTransportationsFromParsedObject(List<ParsedTransportation> transportations){
        List<Transportation> result=new ArrayList<>();
        for(ParsedTransportation transportation:transportations){
            result.add(transportation.transportation);
        }
        return result;
    }
    private void persistCargo(Collection<Cargo> cargos) {
        for (Cargo cargo : cargos) {
            ServiceHolder.getInstance().getCargoService().save(cargo);
        }
    }
    private  void persistCarriers(Collection<Carrier> carriers) {
        for (Carrier carrier : carriers) {
            ServiceHolder.getInstance().getCarrierService().save(carrier);
        }
    }
    private void persistTransportations(Collection<Transportation> transportations){
        for(Transportation transportation:transportations){
            ServiceHolder.getInstance().getTransportationService().save(transportation);
        }
    }

    private  void setReffBetweenEntries(Map<String, Cargo> cargoMap, Map<String, Carrier> carrierMap,
                                              List<ParsedTransportation> parsedTransportationsList) {
        for (ParsedTransportation parsedTransportation : parsedTransportationsList) {
            Cargo cargo = cargoMap.get(parsedTransportation.cargoRef);
            Carrier carrier = carrierMap.get(parsedTransportation.carrierRef);
            Transportation transportation = parsedTransportation.transportation;

            if (cargo != null) {
                List<Transportation> transportations =
                        cargo.getTransportations() == null ? new ArrayList<>() : cargo.getTransportations();
                transportations.add(transportation);
                cargo.setTransportations(transportations);
                transportation.setCargo(cargo);

            }

            if (carrier != null) {
                List<Transportation> transportations =
                        carrier.getTransportations() == null ? new ArrayList<>() : carrier.getTransportations();
                transportations.add(transportation);
                carrier.setTransportations(transportations);
                transportation.setCarrier(carrier);
            }


        }

    }

    private  Map<String, Cargo> readCargoFromFile(BufferedReader bufferedReader) throws IOException {
        String line;
        Map<String, Cargo> mapOfCargo = new HashMap<>();
        while ((line = bufferedReader.readLine()) != null && !line.equals(STOP_SYMBOL)) {
            line = line.trim();
            switch (line) {
                case "Clother{": {
                    Map<String, ClothersCargo> mapOfClother;
                    if ((mapOfClother = readCargoClotherFromFile(bufferedReader)) != null) {
                        mapOfCargo.putAll(mapOfClother);

                    }
                    break;
                }
                case "Food{": {
                    Map<String, FoodCargo> mapOfFood;
                    if ((mapOfFood = readCargoFoodFromFile(bufferedReader)) != null) {
                        mapOfCargo.putAll(mapOfFood);
                    }
                    break;

                }
            }
        }
        return mapOfCargo;
    }

    private  Map<String, Carrier> readCarriersFromFile(BufferedReader bufferedReader) throws IOException {
        Map<String, Carrier> mapOfCarrier = new HashMap<>();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            line = line.trim();
            if (line.equals(STOP_SYMBOL))
                break;
            Carrier newCarrier = new Carrier();
            String[] components = line.split("#");

            String id = components[0];
            //newCarrier.setId(IdGenerator.generateId());
            newCarrier.setName(components[1]);
            newCarrier.setAddress(components[2]);
            //skip set transportation. Set it latter
            //skip cargoType. Set auto
            switch (components[3]) {
                case "car": {
                    newCarrier.setCarrierType(CarrierType.CAR);
                    break;
                }
                case "train": {
                    newCarrier.setCarrierType(CarrierType.TRAIN);
                    break;
                }
                case "plane": {
                    newCarrier.setCarrierType(CarrierType.PLANE);
                    break;
                }
                case "ship": {
                    newCarrier.setCarrierType(CarrierType.SHIP);
                    break;
                }
                default: {
                    newCarrier.setCarrierType(null);
                }
            }

            mapOfCarrier.put(id, newCarrier);

        }
        return mapOfCarrier;
    }

    private  List<ParsedTransportation> readTransportationFromFile(BufferedReader bufferedReader) throws IOException {
        List<ParsedTransportation> listOfTransportation = new ArrayList<>();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            line = line.trim();
            if (line.equals(STOP_SYMBOL))
                break;
            ParsedTransportation parsedTransportation = new ParsedTransportation();
            String[] components = line.split("#");

            parsedTransportation.cargoRef = components[0];
            parsedTransportation.carrierRef = components[1];
            parsedTransportation.transportation.setDescription(components[2]);
            parsedTransportation.transportation.setBillTo(components[3]);
            parsedTransportation.transportation.setTransportationBeginDate(JavaUtilDataUtils.valueOf(components[4]));
            listOfTransportation.add(parsedTransportation);
        }
        return listOfTransportation;
    }

    private  Map<String, FoodCargo> readCargoFoodFromFile(BufferedReader bufferedReader) throws IOException {
        Map<String, FoodCargo> mapOfFood = new HashMap<>();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            line = line.trim();
            if (line.equals(STOP_SYMBOL))
                break;
            FoodCargo newFoodCargo = new FoodCargo();
            String[] components = line.split("#");

            String id = components[0];
            //newFoodCargo.setId(IdGenerator.generateId());
            newFoodCargo.setName(components[1]);
            newFoodCargo.setWeight(Integer.parseInt(components[2]));
            //skip set transportation. Set it latter
            //skip cargoType. Set auto
            newFoodCargo.setExpirationDate(JavaUtilDataUtils.valueOf(components[5]));
            newFoodCargo.setStoreTemperature(Integer.parseInt(components[6]));
            mapOfFood.put(id, newFoodCargo);

        }
        return mapOfFood;

    }

    private  Map<String, ClothersCargo> readCargoClotherFromFile(BufferedReader bufferedReader) throws IOException {
        Map<String, ClothersCargo> mapOfClother = new HashMap<>();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            line = line.trim();
            if (line.equals(STOP_SYMBOL))
                break;
            ClothersCargo newClothersCargo = new ClothersCargo();
            String[] components = line.split("#");
            String id = components[0];
            //newClothersCargo.setId(IdGenerator.generateId());
            newClothersCargo.setName(components[1]);
            newClothersCargo.setWeight(Integer.parseInt(components[2]));
            //skip set transportation. Set it latter
            //skip cargoType. Set auto
            newClothersCargo.setSize(components[5]);
            newClothersCargo.setMaterial(components[6]);
            mapOfClother.put(id, newClothersCargo);
        }
        return mapOfClother;
    }


    private  class ParsedTransportation {
        private String cargoRef;
        private String carrierRef;
        private Transportation transportation;

        ParsedTransportation() {
            transportation = new Transportation();
        }
    }
}
