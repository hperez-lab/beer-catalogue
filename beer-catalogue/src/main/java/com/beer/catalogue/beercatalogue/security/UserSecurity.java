package com.beer.catalogue.beercatalogue.security;

import org.springframework.stereotype.Component;

import com.beer.catalogue.beercatalogue.domain.security.CustomUserPrincipal;

@Component("userSecurity")
public class UserSecurity {
     public boolean userHasManufacturer(CustomUserPrincipal principal, Long manufacturerId) {
    	 boolean toReturn = false;

    	 if(principal.getManufacturerId().longValue() == manufacturerId.longValue()) {
    		 toReturn = true;
    	 }
    	 
    	 return toReturn;
    }
}
