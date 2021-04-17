package com.beer.catalogue.beercatalogue.domain.rest.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class ManufacturerRequest {

	@ApiModelProperty(value = "Manufacturer name")
	private String name;
	@ApiModelProperty(value = "Manufacturer nationality")
	private String nationality;
}
