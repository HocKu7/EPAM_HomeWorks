package ru.epam.javacore.lesson_7_collections_continue_map.homework.transportation.service;

import ru.epam.javacore.lesson_7_collections_continue_map.homework.cargo.domain.Cargo;
import ru.epam.javacore.lesson_7_collections_continue_map.homework.carrier.domain.Carrier;
import ru.epam.javacore.lesson_7_collections_continue_map.homework.transportation.domain.Transportation;
import ru.epam.javacore.lesson_7_collections_continue_map.homework.transportation.repo.TransportationRepo;

import java.util.List;

public class TransportationServiceImpl implements TransportationService {

    private TransportationRepo transportationRepo;

    public TransportationServiceImpl(
            TransportationRepo transportationRepo) {
        this.transportationRepo = transportationRepo;
    }

    @Override
    public boolean deleteById(Long id) {
        return transportationRepo.deleteById(id);
    }

    @Override
    public void printAll() {
        List<Transportation> allTransportations = transportationRepo.getAll();
        for (Transportation transportation : allTransportations) {
            System.out.println(transportation);
        }
    }

    @Override
    public void add(Transportation transportation) {
        transportationRepo.add(transportation);
    }

    @Override
    public Transportation getById(Long id) {
        return transportationRepo.getById(id);
    }

    @Override
    public List<Transportation> getAll() {
        return transportationRepo.getAll();
    }

    @Override
    public boolean update(Long id, String[] params) {
        if (id == null || params == null) return false;
        Transportation transportation = getById(id);
        if (transportation == null) return false;
        if (params[0] != null) {
            transportation.setDescription(params[0]);
        }
        if (params[1] != null) {
            transportation.setBillTo(params[1]);
        }
        return true;
    }

    @Override
    public boolean update(Long id, Cargo cargo) {
        if (id == null || cargo == null) return false;
        Transportation transportation = getById(id);
        if (transportation == null) return false;
        transportation.setCargo(cargo);
        return true;
    }

    @Override
    public boolean update(Long id, Carrier carrier) {
        if (id == null || carrier == null) return false;
        Transportation transportation = getById(id);
        if (transportation == null) return false;
        transportation.setCarrier(carrier);
        return true;
    }
}
