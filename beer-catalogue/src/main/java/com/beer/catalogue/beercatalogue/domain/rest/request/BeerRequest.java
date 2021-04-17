package com.beer.catalogue.beercatalogue.domain.rest.request;

import com.beer.catalogue.beercatalogue.enumeration.BeerType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class BeerRequest {

    @ApiModelProperty(value = "Beer name")
	private String name;
    @ApiModelProperty(value = "Beer graduation")
	private Double graduation;
    @ApiModelProperty(value = "Beer type")
	private BeerType type;
    @ApiModelProperty(value = "Beer description")
	private String descritpion;
}
