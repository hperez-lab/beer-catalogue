package com.beer.catalogue.beercatalogue.domain.rest.response;

import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class ManufacturerResponse {

	@ApiModelProperty(value = "Manufacturer identifier")
	private Long id;
	@ApiModelProperty(value = "Manufacturer name")
	private String name;
	@ApiModelProperty(value = "Manufacturer nationality")
	private String nationality;
	@ApiModelProperty(value = "List of beers associated to this manufacturer")
	private List<PlainBeerResponse> beers = new ArrayList<>();
}
