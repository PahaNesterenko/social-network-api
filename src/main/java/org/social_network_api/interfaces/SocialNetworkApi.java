package org.social_network_api.interfaces;

import org.social_network_api.domain.Group;
import org.social_network_api.domain.User;

import java.util.ArrayList;
import java.util.List;


public interface SocialNetworkApi
{
    User getUser(int id);

    //public User getRandUser();

    //public Group getRandGroup();

     Group getGroup(int gid);

     List<Integer> getFriendList(int id);

     List<Integer> getFriendParameters(int id);

     List<Integer> getSubscriptions(int id);

     List<Integer> getFollowers(int id);
}
