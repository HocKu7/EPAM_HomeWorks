package ru.epam.javacore.lesson_6_collections.homework.cargo.service.impl;

import ru.epam.javacore.lesson_6_collections.homework.cargo.domain.Cargo;
import ru.epam.javacore.lesson_6_collections.homework.cargo.repo.impl.CargoCollectionRepoImpl;
import ru.epam.javacore.lesson_6_collections.homework.cargo.service.CargoService;

public class CargoCollectionServiceImpl implements CargoService {
    private static CargoCollectionRepoImpl repo = new CargoCollectionRepoImpl();

    @Override
    public void add(Cargo cargo) {
        if(cargo==null)return;
        //some logic
        System.out.println("Add cargo " + cargo.getName());
        repo.add(cargo);
    }

    @Override
    public Cargo getById(Long id) {
        if (id != null) {
            System.out.println("Get cargo by id "+ id);
            Cargo result=repo.getById(id);
            return result;
        }
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        if(id==null)return false;
        System.out.println("Delete cargo by id " + id);
        return repo.deleteById(id);
    }
    public static Cargo[] getByName(String name) {
        if(name==null)
            return null;
        return repo.getByName(name);
    }

    public void printAllCargos()
    {
        for(int i=0; i<repo.getSizeOfList();i++)
        {
            System.out.println(repo.getByIndex(i));
        }
    }

}
