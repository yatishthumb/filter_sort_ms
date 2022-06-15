package com.hackerrank.sample.service.impl;

import com.hackerrank.sample.comparator.SortByPriceComparator;
import com.hackerrank.sample.dto.SortedProducts;
import com.hackerrank.sample.exception.ErrorCode;
import com.hackerrank.sample.exception.NoDataFound;
import com.hackerrank.sample.helper.JSONHelper;
import com.hackerrank.sample.service.ProductSortingService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

@Service
public class ProductSortingServiceImpl implements ProductSortingService {

  @Override
  public SortedProducts[] sortByPrice(JSONArray data) {

    List<JSONObject> jsonObjectList = JSONHelper.getListFromJsonArray(data);

    if(CollectionUtils.isEmpty(jsonObjectList)) {
      throw new NoDataFound(ErrorCode.PRODUCT_DATA_NOT_FOUND);
    }

    Collections.sort(jsonObjectList, new SortByPriceComparator());

    return transformListIntoArray(jsonObjectList);
  }

  private SortedProducts[] transformListIntoArray(List<JSONObject> jsonObjectList) {
    SortedProducts[] sortedProducts = new SortedProducts[jsonObjectList.size()];

    int counter = 0;
    for(JSONObject jsonObject : jsonObjectList) {
      sortedProducts[counter++] = new SortedProducts((String) jsonObject.get("barcode"));
    }

    return sortedProducts;
  }

}
