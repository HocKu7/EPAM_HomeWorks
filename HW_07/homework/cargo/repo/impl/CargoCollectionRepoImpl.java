package ru.epam.javacore.lesson_6_collections.homework.cargo.repo.impl;

import ru.epam.javacore.lesson_6_collections.homework.cargo.domain.Cargo;
import ru.epam.javacore.lesson_6_collections.homework.cargo.repo.CargoRepo;
import ru.epam.javacore.lesson_6_collections.homework.storage.IdGenerator;

import static ru.epam.javacore.lesson_6_collections.homework.storage.Storage.cargos;
import static ru.epam.javacore.lesson_6_collections.homework.storage.Storage.listOfCargos;


public class CargoCollectionRepoImpl implements CargoRepo {
    private static final Cargo[] EMPTY_CARGO_ARRAY = new Cargo[0];

    @Override
    public void add(Cargo cargo) {
        if (cargo == null)
            return;
        cargo.setId(IdGenerator.generateId());
        listOfCargos.add(cargo);
    }

    @Override
    public Cargo getById(long id) {

        for (Cargo item : listOfCargos) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    @Override
    public Cargo[] getByName(String name) {
        int countOfitems = 0;
        int index = 0;
        for (Cargo item : listOfCargos) {
            if (item.getName().equals(name)) {
                countOfitems++;
            }
        }
        if (countOfitems > 0) {
            Cargo[] resultArray = new Cargo[countOfitems];

            for (Cargo item : listOfCargos) {
                if (item.getName().equals(name)) {
                    resultArray[index++] = item;
                }
            }
            return resultArray;
        }
        else {
            return EMPTY_CARGO_ARRAY;
        }
    }

    @Override
    public boolean deleteById(long id) {
        Cargo linkOfDelete=null;
        for(Cargo item:listOfCargos)
        {
            if(item.getId()!=null && item.getId()==id)
            {
                linkOfDelete=item;
                break;
            }
        }
        if(linkOfDelete!=null) {
            listOfCargos.remove(linkOfDelete);
            return true;
        }
        return false;
    }
    public int getSizeOfList()
    {
        return listOfCargos.size();
    }
    public Cargo getByIndex(int index)
    {
        int i=0;
        for(Cargo item:listOfCargos)
        {
            if(i==index)
                return item;
            i++;
        }
        return null;
    }
}
