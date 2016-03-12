package org.social_network_api.process;

import org.apache.commons.io.IOUtils;
import org.social_network_api.domain.User;
import org.social_network_api.domain.UserPhoto;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;

@Component
public class ImageProcess {

    public UserPhoto getUserPhoto(Long userId, String urlString) {
        UserPhoto userPhoto = new UserPhoto();
        userPhoto.setId(userId);
        byte[] photo = null;
        try {
            URL url = new URL(urlString);
            photo = IOUtils.toByteArray(url.openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        userPhoto.setPhoto(photo);
        return userPhoto;
    }

    public UserPhoto getUserPhoto(User user)
    {
        return getUserPhoto(user.getId(), user.getPhotoAddress());
    }
}
