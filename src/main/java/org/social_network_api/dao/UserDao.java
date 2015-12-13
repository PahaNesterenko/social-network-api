package org.social_network_api.dao;

import org.social_network_api.domain.User;

import java.util.List;

public interface UserDao {

   void createUser(User user);
   User getById(Long id);
   List<User> getAllUsers();
   void updateUser(User user);
   void deleteUser(Long id);

}
