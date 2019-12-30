package ru.epam.javacore.lesson_7_collections_continue_map.homework.cargo.service;

import ru.epam.javacore.lesson_7_collections_continue_map.homework.cargo.domain.Cargo;
import ru.epam.javacore.lesson_7_collections_continue_map.homework.cargo.repo.CargoRepo;
import ru.epam.javacore.lesson_7_collections_continue_map.homework.transportation.domain.Transportation;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CargoServiceImpl implements CargoService {

    private CargoRepo cargoRepo;
    private static SortType sortType;

    public CargoServiceImpl(CargoRepo cargoRepo) {
        this.cargoRepo = cargoRepo;
    }

    @Override
    public void add(Cargo cargo) {
        cargoRepo.add(cargo);
    }

    @Override
    public Cargo getById(Long id) {
        if (id != null) {
            return cargoRepo.getById(id);
        }
        return null;
    }

    @Override
    public List<Cargo> getAll() {
        return cargoRepo.getAll();
    }

    @Override
    public List<Cargo> getByName(String name) {
        Cargo[] found = cargoRepo.getByName(name);
        return (found == null || found.length == 0) ? Collections.emptyList() : Arrays.asList(found);
    }

    @Override
    public boolean update(Long id, String name) {
        if (id == null) return false;
        Cargo item = getById(id);
        if (item == null) return false;
        item.setName(name);
        return true;
    }

    @Override
    public boolean update(Long id, Integer weight) {
        if (id == null) return false;
        Cargo item = getById(id);
        if (item == null) return false;
        item.setWeight(weight);
        return true;
    }

    @Override
    public boolean update(Long id, Transportation[] transportation) {
        if (id == null) return false;
        Cargo item = getById(id);
        if (item == null) return false;
        item.setTransportations(transportation);
        return true;
    }

    @Override
    public boolean deleteById(Long id) {
        return cargoRepo.deleteById(id);
    }

    @Override
    public void printAll() {
        List<Cargo> allCargos = cargoRepo.getAll();

        for (Cargo cargo : allCargos) {
            System.out.println(cargo);
        }
    }

    @Override
    public void sort(SortType sortType) {
        List<Cargo> allCargos = cargoRepo.getAll();
        switch (sortType) {
            case WEIGHT: {
                allCargos.sort(new Comparator<Cargo>() {
                    @Override
                    public int compare(Cargo o1, Cargo o2) {
                        if (o1.getWeight() == o2.getWeight()) return 0;
                        return o1.getWeight() > o2.getWeight() ? 1 : -1;
                    }
                });
                break;
            }
            case NAME: {
                allCargos.sort(new Comparator<Cargo>() {
                    @Override
                    public int compare(Cargo o1, Cargo o2) {
                        return o1.getName().compareTo(o2.getName());
                    }
                });
                break;
            }
            case NAMEANDWEIGHT: {
                //if name equals then sort by weight
                allCargos.sort(new Comparator<Cargo>() {
                    @Override
                    public int compare(Cargo o1, Cargo o2) {
                        if (o1.getName().compareTo(o2.getName()) == 0) {
                            if (o1.getWeight() == o2.getWeight()) return 0;
                            return o1.getWeight() > o2.getWeight() ? 1 : -1;
                        } else {
                            return o1.getName().compareTo(o2.getName());
                        }
                    }
                });
                break;
            }
        }


    }
}
