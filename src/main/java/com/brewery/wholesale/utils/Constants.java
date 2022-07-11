package com.brewery.wholesale.utils;

public class Constants {
	public static final float DISCOUNT_ABOVE_TEN_BEERS = (float) 0.1;
	public static final float DISCOUNT_ABOVE_TWENTY_BEERS = (float) 0.2;

	public static final String ERR_EMPTY_ORDER = "The order cannot be empty";
	public static final String ERR_DUPLICATE_ORDER = "There can't be any duplicate in the order";
	public static final String ERR_ORDERED_BEERS_NUMBER_GREATER_THAN_WHOLESALERS_STOCK = "The number of beers ordered cannot be greater than the wholesaler's stock";
	public static final String ERR_SOLD_REQUIRED_BY_WHOLESALER = "The beer must be sold by the wholesaler";

	public static final String ERR_WHOLESALER_MUST_EXIST = "The wholesaler must exist";
	public static final String ERR_WHOLESALER_STOCK_MUST_EXIST = "The wholesaler stock must exist";
	public static final String ERR_BREWERY_MUST_EXIST = "The Brewery must exist";
	public static final String ERR_BEER_MUST_EXIST = "The beer must exist";

	public static final String DATA_SUCCESSFULLY_RETREIVED = "Data successfully retreived";
	public static final String DATA_SUCCESSFULLY_INSERTED = "Data successfully inserted";
	public static final String DATA_SUCCESSFULLY_DELETED = "Data successfully deleted";

	public static final String EMPTY_DATA = "Empty data";

}
