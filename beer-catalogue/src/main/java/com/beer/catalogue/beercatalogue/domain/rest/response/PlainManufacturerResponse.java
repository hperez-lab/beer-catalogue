package com.beer.catalogue.beercatalogue.domain.rest.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class PlainManufacturerResponse {

	@ApiModelProperty(value = "Manufacturer identifier")
	private Long id;
	@ApiModelProperty(value = "Manufacturer name")
	private String name;
	@ApiModelProperty(value = "Manufacturer nationality")
	private String nationality;
}
