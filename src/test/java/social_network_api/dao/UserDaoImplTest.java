package social_network_api.dao;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.social_network_api.dao.UserDao;
import org.social_network_api.domain.City;
import org.social_network_api.domain.Country;
import org.social_network_api.domain.User;
import org.social_network_api.vkImpl.VkImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static junit.framework.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-application-context.xml"})
public class UserDaoImplTest {

  @Autowired
  private VkImpl vkImpl;

  @Autowired
  private UserDao userDao;

  @Test
  public void test()
  {
    City city = new City();
    city.setId(1L);
    city.setName("city");

    Country country = new Country();
    country.setId(1L);
    country.setName("countryName");

    User user = new User();
    user.setId(2L);
    user.setName("Name");
    user.setLastName("lastName");
    user.setCountry(country);
    user.setCity(city);

    userDao.createUser(user);

    user = userDao.getById(2L);
    assertNotNull(user.getName());
    assertNotNull(user.getLastName());
  }


}
