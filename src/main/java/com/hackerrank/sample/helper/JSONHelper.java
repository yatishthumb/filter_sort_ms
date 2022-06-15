package com.hackerrank.sample.helper;

import com.hackerrank.sample.exception.ErrorCode;
import com.hackerrank.sample.exception.NoDataFound;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class JSONHelper {

    public static List<JSONObject> getListFromJsonArray(JSONArray data) {

        if(Objects.isNull(data)) {
            throw new NoDataFound(ErrorCode.PRODUCT_DATA_NOT_FOUND);
        }

        List<JSONObject> jsonObjectList = new ArrayList<>();

        for(int i = 0; i < data.length(); i++) {
            jsonObjectList.add((JSONObject) data.get(i));
        }

        return jsonObjectList;
    }
}
