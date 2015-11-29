package social_network_api.VkImpl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.social_network_api.dao.UserDao;
import org.social_network_api.domain.User;
import org.social_network_api.vkImpl.VkImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-application-context.xml"})
public class VkImplTest {

  @Autowired
  private VkImpl VkImpl;

  @Autowired
  private UserDao userDao;

  @Test
  public void test() {

    System.out.println("hello");
    User user = VkImpl.getUser(1);

    assertEquals(user.getName(), "Павел");
    assertEquals(user.getLastName(), "Дуров");

    userDao.createUser(user);


  }


}
