package social_network_api.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.social_network_api.dao.CityDao;
import org.social_network_api.domain.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static junit.framework.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-application-context.xml"})
public class CityDaoTest {

  @Autowired
  private CityDao cityDao;

  @Test
  public void test()
  {
    City city = cityDao.getById(1L);
    assertNotNull(city);
  }


}
