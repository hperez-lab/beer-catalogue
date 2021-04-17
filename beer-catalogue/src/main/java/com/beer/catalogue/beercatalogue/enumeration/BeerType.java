package com.beer.catalogue.beercatalogue.enumeration;

public enum BeerType {

    ALE("ALE"), 
    LAGER("LAG"), 
    PORTER("POR"), 
    BLOND_ALE("BLA"),
    BROWN_ALES("BRA"),
    PALE_ALE("PAA"),
    INDIA_PALE_ALE("IPA"),
    WHEAT("WHE"),
    PISLNER("PIL"),
    SOUR_ALE("SOA");

    private String code;

    private BeerType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
