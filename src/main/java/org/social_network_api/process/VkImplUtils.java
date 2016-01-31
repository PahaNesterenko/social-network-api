package org.social_network_api.process;


import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.social_network_api.Constants;
import org.social_network_api.domain.User;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.net.URL;

@Component
public class VkImplUtils {

  private static String photoStub = "http://vk.com/images/camera_400.png";


  public static void savePhoto(User user) {
    try {

      String fileName = user.getId().toString();
      File file = new File(Constants.FILESTORE + fileName);
      FileUtils.copyURLToFile(new URL(user.getPhotoAddress()), file);

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static boolean userHasPhoto(User user) {
    return StringUtils.isNotBlank(user.getPhotoAddress()) && !photoStub.equals(user.getPhotoAddress());
  }
}
