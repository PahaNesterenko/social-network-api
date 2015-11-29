package org.social_network_api.dao;

import org.social_network_api.domain.User;

import java.util.List;

public interface UserDao {

  public void createUser(User user);
  public User getById(Long id);
  public List<User> getAllUsers();
  public void updateUser(User user);
  public void deleteUser(Long id);

}
