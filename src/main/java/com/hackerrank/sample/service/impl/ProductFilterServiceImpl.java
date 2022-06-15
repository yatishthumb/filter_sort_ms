package com.hackerrank.sample.service.impl;

import com.hackerrank.sample.dto.FilteredProducts;
import com.hackerrank.sample.exception.ErrorCode;
import com.hackerrank.sample.exception.NoDataFound;
import com.hackerrank.sample.helper.JSONHelper;
import com.hackerrank.sample.service.ProductFilterService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.hackerrank.sample.constant.ProductDataConstatnt.PRODUCT_BARCODE;
import static com.hackerrank.sample.constant.ProductDataConstatnt.PRODUCT_PRICE;

@Service
public class ProductFilterServiceImpl implements ProductFilterService {

    @Override
    public List<FilteredProducts> filterByPriceRange(JSONArray data, int initialPrice, int finalPrice) {

        List<JSONObject> jsonObjectList = JSONHelper.getListFromJsonArray(data);

        jsonObjectList = jsonObjectList.stream()
                .filter(jsonObject -> (int)jsonObject.get(PRODUCT_PRICE) >= initialPrice
                                && (int)jsonObject.get(PRODUCT_PRICE) <= finalPrice)
                .collect(Collectors.toList());

        if(CollectionUtils.isEmpty(jsonObjectList)) {
            throw new NoDataFound(ErrorCode.PRODUCT_DATA_NOT_FOUND);
        }

        return buildResponse(initialPrice, finalPrice, jsonObjectList);

    }

    private List<FilteredProducts> buildResponse(int initialPrice, int finalPrice, List<JSONObject> jsonObjectList) {
        List<FilteredProducts> filteredProducts = new ArrayList<>();

        for(JSONObject product : jsonObjectList) {
            if(((int)product.get(PRODUCT_PRICE) >= initialPrice) && ((int)product.get(PRODUCT_PRICE) <= finalPrice)) {
                filteredProducts.add(new FilteredProducts((String) product.get(PRODUCT_BARCODE)));
            }
        }

        return filteredProducts;
    }
}
