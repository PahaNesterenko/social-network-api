package org.social_network_api.domain;


import com.fasterxml.jackson.annotation.JsonProperty;

public class Countries {

  @JsonProperty("response")
  private Country[] countries;

  public Country[] getCountries() {
    return countries;
  }

  public void setCountries(Country[] countries) {
    this.countries = countries;
  }
}
