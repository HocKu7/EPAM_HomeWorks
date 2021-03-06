package ru.epam.javacore.lesson_8_collections_map_set_comparators.homework.carrier.repo.impl;


import static ru.epam.javacore.lesson_8_collections_map_set_comparators.homework.storage.Storage.carrierCollection;

import ru.epam.javacore.lesson_8_collections_map_set_comparators.homework.carrier.domain.Carrier;
import ru.epam.javacore.lesson_8_collections_map_set_comparators.homework.carrier.repo.CarrierRepo;
import ru.epam.javacore.lesson_8_collections_map_set_comparators.homework.common.business.repo.IndexNotFound;
import ru.epam.javacore.lesson_8_collections_map_set_comparators.homework.storage.IdGenerator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class CarrierCollectionRepoImpl implements CarrierRepo {

  @Override
  public void add(Carrier carrier) {
    carrier.setId(IdGenerator.generateId());
    carrierCollection.add(carrier);
  }

  @Override
  public Carrier getById(long id) {
    for (Carrier carrier : carrierCollection) {
      if (Long.valueOf(id).equals(carrier.getId())) {
        return carrier;
      }
    }

    return null;
  }

  @Override
  public Carrier[] findByName(String name) {
    List<Carrier> result = new ArrayList<>();

    for (Carrier carrier : carrierCollection) {
      if (Objects.equals(carrier.getName(), name)) {
        result.add(carrier);
      }
    }

    return result.toArray(new Carrier[0]);
  }

  @Override
  public boolean deleteById(long id) throws  IndexNotFound{
    Iterator<Carrier> iter = carrierCollection.iterator();

    boolean removed = false;
    while (iter.hasNext()) {
      if (Long.valueOf(id).equals(iter.next().getId())) {
        iter.remove();
        removed = true;
        return removed;
      }
    }

    throw new IndexNotFound();
  }

  @Override
  public List<Carrier> getAll() {
    return carrierCollection;
  }

  @Override
  public void update(Carrier carrier) {

  }
}
