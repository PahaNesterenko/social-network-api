package org.social_network_api.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Users {

  @JsonProperty("response")
  private User[] users;

  public User[] getUsers() {
    return users;
  }

  public void setUsers(User[] users) {
    this.users = users;
  }
}
