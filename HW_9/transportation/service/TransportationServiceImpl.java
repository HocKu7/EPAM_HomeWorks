package ru.epam.javacore.lesson_8_collections_map_set_comparators.homework.transportation.service;

import ru.epam.javacore.lesson_8_collections_map_set_comparators.homework.common.business.repo.IndexNotFound;
import ru.epam.javacore.lesson_8_collections_map_set_comparators.homework.transportation.domain.Transportation;
import ru.epam.javacore.lesson_8_collections_map_set_comparators.homework.transportation.repo.TransportationRepo;

import java.util.List;

public class TransportationServiceImpl implements TransportationService {

  private TransportationRepo transportationRepo;

  public TransportationServiceImpl(
      TransportationRepo transportationRepo) {
    this.transportationRepo = transportationRepo;
  }

  @Override
  public boolean deleteById(Long id)  {
    try{
      transportationRepo.deleteById(id);
      return true;
    }catch (IndexNotFound e){
      System.out.println(e.getMessage());
      return false;
    }
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
  public void update(Transportation transportation) {
    if (transportation != null) {
      transportationRepo.update(transportation);
    }
  }
}
