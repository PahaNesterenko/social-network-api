package org.social_network_api.domain;


import com.fasterxml.jackson.annotation.JsonProperty;

public class Cities {

  @JsonProperty("response")
  private City[] cities;

  public City[] getCities() {
    return cities;
  }

  public void setCities(City[] cities) {
    this.cities = cities;
  }
}
