package com.hackerrank.sample.service;

import com.hackerrank.sample.dto.FilteredProducts;
import org.json.JSONArray;

import java.util.List;

public interface ProductFilterService {

    List<FilteredProducts> filterByPriceRange(JSONArray jsonProductData, int initialPrice, int finalPrice);

}
