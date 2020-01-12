package carrier.repo.impl;


import carrier.domain.Carrier;
import common.business.repo.CommonRepoHelper;
import storage.IdGenerator;
import storage.Storage;
import carrier.repo.CarrierRepo;
import common.solutions.utils.ArrayUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class CarrierArrayRepoImpl implements CarrierRepo {

  private static final Carrier[] EMPTY_CARRIER_ARRAY = new Carrier[0];

  @Override
  public void save(Carrier carrier) {
    if (Storage.carrierIndex == Storage.carrierArray.length) {
      Carrier[] newCarriers = new Carrier[Storage.carrierArray.length * 2];
      ArrayUtils.copyArray(Storage.carrierArray, newCarriers);
      Storage.carrierArray = newCarriers;
    }

    carrier.setId(IdGenerator.generateId());
    Storage.carrierArray[Storage.carrierIndex] = carrier;
    Storage.carrierIndex++;
  }

  @Override
  public boolean update(Carrier carrier) {
    return true;
  }

  @Override
  public Carrier getByIdFetchingTransportations(long id) {
    return findById(id);
  }

  @Override
  public Carrier[] findByName(String name) {
    Carrier[] searchResultWithNullableElems = getByNameIncludingNullElements(name);
    if (searchResultWithNullableElems == null || searchResultWithNullableElems.length == 0) {
      return EMPTY_CARRIER_ARRAY;
    } else {
      return excludeNullableElementsFromArray(searchResultWithNullableElems);
    }
  }

  private Carrier[] getByNameIncludingNullElements(String name) {
    Carrier[] result = new Carrier[Storage.carrierArray.length];

    int curIndex = 0;
    for (Carrier carrier : Storage.carrierArray) {
      if (carrier != null && Objects.equals(carrier.getName(), name)) {
        result[curIndex++] = carrier;
      }
    }

    return result;
  }


  private Carrier[] excludeNullableElementsFromArray(Carrier[] carriers) {
    int sizeOfArrWithNotNullElems = 0;

    for (Carrier carrier : carriers) {
      if (carrier != null) {
        sizeOfArrWithNotNullElems++;
      }
    }

    if (sizeOfArrWithNotNullElems == 0) {
      return EMPTY_CARRIER_ARRAY;
    } else {
      Carrier[] result = new Carrier[sizeOfArrWithNotNullElems];
      int index = 0;
      for (Carrier carrier : carriers) {
        if (carrier != null) {
          result[index++] = carrier;
        }
      }

      return result;
    }
  }

  @Override
  public List<Carrier> getAll() {
    Carrier[] carriers = excludeNullableElementsFromArray(Storage.carrierArray);
    return carriers.length == 0 ? Collections.emptyList() : Arrays.asList(Storage.carrierArray);
  }

  @Override
  public int countAll() {
    return this.getAll().size();
  }

  @Override
  public Carrier findById(Long id) {
    for (Carrier carrier : Storage.carrierArray) {
      if (carrier != null && carrier.getId().equals(id)) {
        return carrier;
      }
    }

    return null;
  }

  @Override
  public boolean deleteById(Long id) {
    Integer indexToDelete = CommonRepoHelper.findEntityIndexInArrayStorageById(Storage.carrierArray, id);

    if (indexToDelete == null) {
      return false;
    } else {
      ArrayUtils.removeElement(Storage.carrierArray, indexToDelete);
      return true;
    }
  }
}