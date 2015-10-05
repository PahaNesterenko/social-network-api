package social_network_api.VkImpl;

import org.junit.Test;
import org.social_network_api.domain.User;
import org.social_network_api.vkImpl.VkImpl;
import static org.junit.Assert.assertEquals;

public class VkImplTest {

  @Test
  public void test() {

    System.out.println("hello");
    VkImpl v = new VkImpl();
    User user = v.getUser(1);

    assertEquals(user.getName(), "Павел");
    assertEquals(user.getLastName(), "Дуров");

  }


}
