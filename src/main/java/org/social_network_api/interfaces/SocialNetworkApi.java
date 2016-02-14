package org.social_network_api.interfaces;

import org.social_network_api.domain.Group;
import org.social_network_api.domain.User;

import java.util.List;


public interface SocialNetworkApi
{
    User getUser(Long id);

    //public User getRandUser();

    //public Group getRandGroup();

     Group getGroup(Long gid);

     List<Integer> getFriendList(Long id);

     List<Integer> getFriendParameters(Long id);

     List<Integer> getSubscriptions(Long id);

     List<Integer> getFollowers(Long id);
}
