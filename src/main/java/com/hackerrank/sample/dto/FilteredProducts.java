package com.hackerrank.sample.dto;

public class FilteredProducts {

	public String getBarCode() {
		return BarCode;
	}

	public void setBarCode(String barCode) {
		BarCode = barCode;
	}

	private String BarCode;
	
	public FilteredProducts(String a) {
		BarCode=a;
	}


}