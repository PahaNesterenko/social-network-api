package org.social_network_api.interfaces;

import org.social_network_api.domain.Group;
import org.social_network_api.domain.User;

import java.util.ArrayList;


public interface SocialNetworkApi
{
    public User getUser(int id);

    //public User getRandUser();

    //public Group getRandGroup();

    public Group getGroup(int gid);

    public ArrayList<Integer> getFriendList(int id);

    public ArrayList<Integer> getFriendParameters(int id);

    public ArrayList<Integer> getSubscriptions(int id);

    public ArrayList<Integer> getFollowers(int id);
}
