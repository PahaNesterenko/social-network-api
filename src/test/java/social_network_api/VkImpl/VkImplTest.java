package social_network_api.VkImpl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.social_network_api.domain.User;
import org.social_network_api.vkImpl.VkImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static junit.framework.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-application-context.xml"})
public class VkImplTest {

  @Autowired
  private VkImpl vkImpl;

  @Test
  public void testDurov() {

    User user = vkImpl.getUser(1L);

    assertEquals(user.getName(), "Павел");
    assertEquals(user.getLastName(), "Дуров");
  }

}
