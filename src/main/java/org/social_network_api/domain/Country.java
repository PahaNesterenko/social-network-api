package org.social_network_api.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="COUNTRY")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Country {

  @JsonProperty("cid")
  @Id
  @Column(name = "ID")
  private Long id;

  @JsonProperty("name")
  @Column(name = "NAME")
  private String name;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
