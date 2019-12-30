package ru.epam.javacore.lesson_7_collections_continue_map.homework.carrier.service;

import ru.epam.javacore.lesson_7_collections_continue_map.homework.carrier.domain.Carrier;
import ru.epam.javacore.lesson_7_collections_continue_map.homework.carrier.domain.CarrierType;
import ru.epam.javacore.lesson_7_collections_continue_map.homework.carrier.repo.CarrierRepo;
import ru.epam.javacore.lesson_7_collections_continue_map.homework.transportation.domain.Transportation;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CarrierServiceImpl implements CarrierService {

    private CarrierRepo carrierRepo;

    public CarrierServiceImpl(
            CarrierRepo carrierRepo) {
        this.carrierRepo = carrierRepo;
    }

    @Override
    public void add(Carrier carrier) {
        carrierRepo.add(carrier);
    }

    @Override
    public Carrier getById(Long id) {
        if (id != null) {
            return carrierRepo.getById(id);
        }
        return null;
    }

    @Override
    public List<Carrier> getByName(String name) {
        Carrier[] found = carrierRepo.getByName(name);
        return (found == null || found.length == 0) ? Collections.emptyList() : Arrays.asList(found);

    }

    @Override
    public List<Carrier> getAll() {
        return carrierRepo.getAll();
    }

    @Override
    public boolean update(Long id, String[] params) {
        if (params == null || id == null) return false;
        Carrier carrier = getById(id);
        if (carrier == null) return false;
        if (params[0] != null) {
            carrier.setName(params[0]);
        }
        if (params[1] != null) {
            carrier.setAddress(params[1]);
        }
        return true;
    }

    @Override
    public boolean update(Long id, CarrierType carrierType) {
        if (id == null || carrierType == null) return false;
        Carrier carrier = getById(id);
        if (carrier == null) return false;
        carrier.setCarrierType(carrierType);
        return false;
    }

    @Override
    public boolean update(Long id, Transportation[] transportation) {
        if (id == null || transportation == null) return false;
        Carrier carrier = getById(id);
        if (carrier == null) return false;
        carrier.setTransportations(transportation);
        return true;
    }

    @Override
    public boolean deleteById(Long id) {
        return carrierRepo.deleteById(id);
    }

    @Override
    public void printAll() {
        List<Carrier> carriers = carrierRepo.getAll();
        for (Carrier carrier : carriers) {
            System.out.println(carrier);
        }
    }
}
