package social_network_api.process;


import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.social_network_api.dao.UserDao;
import org.social_network_api.dao.UserPhotoDao;
import org.social_network_api.domain.User;
import org.social_network_api.process.ImageProcess;
import org.social_network_api.process.VkImplProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static junit.framework.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-application-context.xml"})
public class VkImplProcessTest {

    @Autowired
    private VkImplProcess vkImplProcess;

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserPhotoDao userPhotoDao;

    @Autowired
    private ImageProcess imageProcess;

    @Test
    public void test()
    {
        User user = vkImplProcess.getRandomUser();
        assertNotNull(user.getName());
        assertNotNull(user.getLastName());
    }


    @Ignore
    @Test
    public void fetchUsersTest()
    {
        User user;
        for(int i = 0; i < 1000 ; ++i)
        {
            try {
                user = vkImplProcess.getRandomUser();
                userDao.createUser(user);
                userPhotoDao.createUserPhoto(imageProcess.getUserPhoto(user));
            }catch(Exception e){

            }
        }
    }

}
