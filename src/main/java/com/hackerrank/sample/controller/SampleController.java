package com.hackerrank.sample.controller;

import com.hackerrank.sample.dto.FilteredProducts;
import com.hackerrank.sample.dto.SortedProducts;
import com.hackerrank.sample.service.ProductFilterService;
import com.hackerrank.sample.service.ProductSortingService;
import com.hackerrank.sample.validator.ProductValidator;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.springframework.http.HttpStatus.OK;

@RestController
public class SampleController {

	@Autowired
	private ProductFilterService filterService;

    @Autowired
    private ProductSortingService sortingService;

    @Autowired
	private ProductValidator validator;

    //TODO: DELETE ME: As its scrap code. Copied the jsonmock api's response for development purpose
//	public SampleController() throws IOException {
//	}
//
//	Stream<String> stream = Files.lines(Paths.get("C:\\Users\\703241463\\Desktop\\inventory.json"));
//	String productJson = stream.collect(Collectors.joining());
//
//	JSONObject root = new JSONObject(productJson);
//	JSONArray data = root.getJSONArray("data");
	// End of Scrap code

	final String uri = "https://jsonmock.hackerrank.com/api/inventory";
	RestTemplate restTemplate = new RestTemplate();
	String result = restTemplate.getForObject(uri, String.class);
	JSONObject root = new JSONObject(result);

	JSONArray data = root.getJSONArray("data");

	@CrossOrigin
	@GetMapping("/filter/price/{initial_price}/{final_price}")
	public ResponseEntity<List<FilteredProducts> > filteredProduct(
			@PathVariable("initial_price") int initialPrice,
			@PathVariable("final_price") int finalPrice) {

			validator.validatePriceRange(initialPrice, finalPrice);

			return new ResponseEntity<>(filterService.filterByPriceRange(data, initialPrice, finalPrice), OK);
	}
		
	@CrossOrigin
	@GetMapping("/sort/price")
	public ResponseEntity<SortedProducts[]> sortedProduct() {

		return new ResponseEntity<>(sortingService.sortByPrice(data), OK);
	}

}
