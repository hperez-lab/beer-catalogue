package com.beer.catalogue.beercatalogue.domain.rest.request;

import com.beer.catalogue.beercatalogue.enumeration.BeerType;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BeerFilterRequest {

    @ApiModelProperty(value = "Beer name")
	private String name;
    @ApiModelProperty(value = "Beer graduation")
	private Double graduation;
    @ApiModelProperty(value = "Beer type")
	private BeerType type;
    @ApiModelProperty(value = "Beer description")
	private String description;
    @ApiModelProperty(value = "Beer manufacturer identifier")
	private Long manufacturerId;
}
