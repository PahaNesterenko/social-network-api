package org.social_network_api.domain;

import java.util.Arrays;

public enum Sex {
  NOT_SPECIFIED(0, "not specified"),
  FEMALE(1, "female"),
  MALE(2, "male");

  private int id;
  private String description;

  Sex(int id, String description){
    this.id = id;
    this.description = description;
  }

  public static Sex getById(int id)
  {
    for(Sex sex : Arrays.asList(Sex.values()))
    {
      if(sex.getId() == id)
      {
        return sex;
      }
    }
    return Sex.NOT_SPECIFIED;
  }

  public int getId(){
    return this.id;
  }
  public String getDescription()
  {
    return this.description;
  }


}
