package com.hackerrank.sample.service;

import com.hackerrank.sample.dto.SortedProducts;
import org.json.JSONArray;

public interface ProductSortingService {

  SortedProducts[] sortByPrice(JSONArray jsonProductData);

}