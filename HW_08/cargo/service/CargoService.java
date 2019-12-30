package ru.epam.javacore.lesson_7_collections_continue_map.homework.cargo.service;

import ru.epam.javacore.lesson_7_collections_continue_map.homework.cargo.domain.Cargo;
import ru.epam.javacore.lesson_7_collections_continue_map.homework.common.business.service.CommonService;
import ru.epam.javacore.lesson_7_collections_continue_map.homework.transportation.domain.Transportation;

import java.util.List;

public interface CargoService extends CommonService {
    enum SortType {
        WEIGHT, NAME, NAMEANDWEIGHT
    }

    void add(Cargo cargo);

    Cargo getById(Long id);

    List<Cargo> getAll();

    List<Cargo> getByName(String name);

    boolean update(Long id, String name);

    boolean update(Long id, Integer weight);

    boolean update(Long id, Transportation[] transportation);

    void sort(SortType sortType);
}
