package social_network_api.process;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.social_network_api.Constants;
import org.social_network_api.domain.User;
import org.social_network_api.process.VkImplProcess;
import org.social_network_api.process.VkImplUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;

import static junit.framework.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-application-context.xml"})
public class VkImplUtilsTest {

  @Autowired
  private VkImplProcess vkImplProcess;

  @Autowired
  private VkImplUtils vkImplUtils;

  @Test
  public void savePhotoTest()
  {
    User user = vkImplProcess.getRandomUser();
    vkImplUtils.savePhoto(user);
    File file = new File(Constants.FILESTORE + user.getId());
    assertTrue(file.isFile());
  }

}
