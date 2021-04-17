package com.beer.catalogue.beercatalogue.domain.rest.response;

import com.beer.catalogue.beercatalogue.enumeration.BeerType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class BeerResponse {

    @ApiModelProperty(value = "Beer identifier")
	private Long id;
    @ApiModelProperty(value = "Beer name")
	private String name;
    @ApiModelProperty(value = "Beer graduation")
	private Double graduation;
    @ApiModelProperty(value = "Beer type")
	private BeerType type;
    @ApiModelProperty(value = "Beer description")
	private String descritpion;
    @ApiModelProperty(value = "Beer manufacturer")
	private PlainManufacturerResponse manufacturer;

}
