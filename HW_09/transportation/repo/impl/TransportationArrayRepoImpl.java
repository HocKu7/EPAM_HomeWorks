package ru.epam.javacore.lesson_8_collections_map_set_comparators.homework.transportation.repo.impl;


import static ru.epam.javacore.lesson_8_collections_map_set_comparators.homework.common.business.repo.CommonRepoHelper.findEntityIndexInArrayStorageById;
import static ru.epam.javacore.lesson_8_collections_map_set_comparators.homework.storage.Storage.transportationArray;
import static ru.epam.javacore.lesson_8_collections_map_set_comparators.homework.storage.Storage.transportationIndex;

import ru.epam.javacore.lesson_8_collections_map_set_comparators.homework.common.business.repo.IndexNotFound;
import ru.epam.javacore.lesson_8_collections_map_set_comparators.homework.common.solutions.utils.ArrayUtils;
import ru.epam.javacore.lesson_8_collections_map_set_comparators.homework.storage.IdGenerator;
import ru.epam.javacore.lesson_8_collections_map_set_comparators.homework.transportation.domain.Transportation;
import ru.epam.javacore.lesson_8_collections_map_set_comparators.homework.transportation.repo.TransportationRepo;

import java.util.Arrays;
import java.util.List;

public class TransportationArrayRepoImpl implements TransportationRepo {

    @Override
    public void add(Transportation transportation) {
        if (transportationIndex == transportationArray.length) {
            Transportation[] newTransportations =
                    new Transportation[transportationArray.length * 2];
            ArrayUtils.copyArray(transportationArray, newTransportations);
            transportationArray = newTransportations;
        }

        transportation.setId(IdGenerator.generateId());
        transportationArray[transportationIndex] = transportation;
        transportationIndex++;
    }

    @Override
    public Transportation getById(long id) {
        for (Transportation transportation : transportationArray) {
            if (transportation != null && Long.valueOf(id).equals(transportation.getId())) {
                return transportation;
            }
        }

        return null;
    }

    @Override
    public List<Transportation> getAll() {
        return Arrays.asList(transportationArray);
    }

    @Override
    public void update(Transportation transportation) {

    }

    @Override
    public boolean deleteById(long id) throws IndexNotFound {
        try {
            Integer indexToDelete = findEntityIndexInArrayStorageById(transportationArray, id);
            ArrayUtils.removeElement(transportationArray, indexToDelete);
            return true;
        } catch (IndexNotFound e) {
           throw e;
        }

    }
}
