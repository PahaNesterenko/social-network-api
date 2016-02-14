package org.social_network_api.process;


import org.social_network_api.domain.User;
import org.social_network_api.vkImpl.VkImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class VkImplProcess {

  private Integer vkMaxUserId = 300000000;

  @Autowired
  private VkImpl vkImpl;


  public User getRandomUser() {
    Random randomGenerator = new Random();
    Long randomInt;
    User user;
    do {
      randomInt = (long) randomGenerator.nextInt(vkMaxUserId);
      user = vkImpl.getUser(randomInt);
    } while (!user.isValidUser());
    System.out.println(user);
    VkImplUtils.savePhoto(user);
    return user;
  }


}
