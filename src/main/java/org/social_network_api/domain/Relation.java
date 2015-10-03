package org.social_network_api.domain;

import java.util.Arrays;

public enum Relation {

  NOT_SPECIFIED(0, "not specified"),
  SINGLE(1, "single"),
  IN_RELATIONSHIP(2, "in relationship"),
  ENGAGED(3, "engaged"),
  MARRIED(4, "married"),
  ALL_DIFFICULT(5, "all difficult"),
  IN_ACTIVE_SEARCH(6, "in active search"),
  IN_LOVE(7, "in love");

  private int id;
  private String description;

  Relation(int id, String description){
    this.id = id;
    this.description = description;
  }

  public static Relation getById(int id){
    for ( Relation relation: Arrays.asList(Relation.values()))
    {
      if( id == relation.getId())
      {
        return relation;
      }
    }
    return NOT_SPECIFIED;
  }

  public int getId() {
    return id;
  }

  public String getDescription() {
    return description;
  }
}