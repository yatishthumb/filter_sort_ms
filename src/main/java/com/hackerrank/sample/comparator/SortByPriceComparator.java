package com.hackerrank.sample.comparator;

import org.json.JSONObject;

import java.util.Comparator;

import static com.hackerrank.sample.constant.ProductDataConstatnt.PRODUCT_PRICE;

public class SortByPriceComparator implements Comparator<JSONObject> {

    @Override
    public int compare(JSONObject o1, JSONObject o2) {

        return ((Integer) o1.get(PRODUCT_PRICE))
                .compareTo(((Integer) o2.get(PRODUCT_PRICE)));
    }
}
