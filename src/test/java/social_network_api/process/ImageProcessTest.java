package social_network_api.process;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.social_network_api.domain.UserPhoto;
import org.social_network_api.process.ImageProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-application-context.xml"})
public class ImageProcessTest {

    @Autowired
    private ImageProcess imageProcess;

    @Test
    public void test()
    {
        String url = "http://cs617730.vk.me/v617730938/1bc8e/h-XNdQoU4wc.jpg";
        Long userId = 42L;
        UserPhoto userPhoto = imageProcess.getUserPhoto(userId, url);
        assertNotNull(userPhoto);
        assertNotNull(userPhoto.getPhoto());
    }


}
