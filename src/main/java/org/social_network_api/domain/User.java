package org.social_network_api.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

/**
 * Class container for User of VK social web. Contains basic info about user
 *
 * @author Pasha
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class User {

  @JsonProperty("uid")
  private int id;
  @JsonProperty("first_name")
  private String name;
  @JsonProperty("last_name")
  private String lastName;
  @JsonProperty("deactivated")
  private boolean deactivated;
  @JsonProperty("verified")
  private boolean verified;
  @JsonProperty("sex")
  private Sex sex;
  @JsonProperty("bdate")
  private String bdate;
  @JsonProperty("city")
  private int city;
  @JsonProperty("country")
  private int country;
  @JsonProperty("home_town")
  private String homeTown;
  @JsonProperty("photo_max")
  private String photoAddress;
  @JsonProperty("online")
  private boolean online;
  @JsonProperty("status")
  private String status;
  @JsonProperty("followers_count")
  private int followersCount;
  @JsonProperty("relation")
  private Relation relation;
  private int friendsNum;
  private ArrayList<Integer> friendList;
  private int maleFriends;
  private int femaleFriends;
  private int followers;
  private int maleFollowers;
  private int femaleFollowers;
  private int subscriptions;
  private int groupSubscriptions;
  private int maleSubscriptions;
  private int femaleSubscriptions;

  public String toString() {
    return "user id - " + id + "\tname - " + name + "\tlast name - " + lastName + "\tsex = " + sex + "\tbdate - " + bdate + "\n"
            + "city - " + city + "\tcountry - " + country + "\thome town - " + homeTown + "\n"
            + "status - " + status + "\n"
            + "relation - " + relation + "\n"
            + "friends - " + friendsNum + ", male/female - " + maleFriends + "/" + femaleFriends + "\n"
            + "followers - " + followers + ", male/female - " + maleFollowers + "/" + femaleFollowers + "\n"
            + "subscriptions - " + subscriptions + ", male/female - " + maleSubscriptions + "/" + femaleSubscriptions;
  }


  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public boolean isDeactivated() {
    return deactivated;
  }

  public void setDeactivated(boolean deactivated) {
    this.deactivated = deactivated;
  }

  public boolean isVerified() {
    return verified;
  }

  public void setVerified(boolean verified) {
    this.verified = verified;
  }

  public Sex getSex() {
    return sex;
  }

  public void setSex(int sex) {
    this.sex = Sex.getById(sex);
  }

  public String getBdate() {
    return bdate;
  }

  public void setBdate(String bdate) {
    this.bdate = bdate;
  }

  public int getCity() {
    return city;
  }

  public void setCity(int city) {
    this.city = city;
  }

  public int getCountry() {
    return country;
  }

  public void setCountry(int country) {
    this.country = country;
  }

  public String getHomeTown() {
    return homeTown;
  }

  public void setHomeTown(String homeTown) {
    this.homeTown = homeTown;
  }

  public String getPhotoAddress() {
    return photoAddress;
  }

  public void setPhotoAddress(String photoAddress) {
    this.photoAddress = photoAddress;
  }

  public boolean isOnline() {
    return online;
  }

  public void setOnline(boolean online) {
    this.online = online;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public int getFollowersCount() {
    return followersCount;
  }

  public void setFollowersCount(int followersCount) {
    this.followersCount = followersCount;
  }

  public Relation getRelation() {
    return relation;
  }

  public void setRelation(int relation) {
    this.relation = Relation.getById(relation);
  }

  public int getFriendsNum() {
    return friendsNum;
  }

  public void setFriendsNum(int friendsNum) {
    this.friendsNum = friendsNum;
  }

  public ArrayList<Integer> getFriendList() {
    return friendList;
  }

  public void setFriendList(ArrayList<Integer> friendList) {
    this.friendList = friendList;
  }

  public int getMaleFriends() {
    return maleFriends;
  }

  public void setMaleFriends(int maleFriends) {
    this.maleFriends = maleFriends;
  }

  public int getFemaleFriends() {
    return femaleFriends;
  }

  public void setFemaleFriends(int femaleFriends) {
    this.femaleFriends = femaleFriends;
  }

  public int getFollowers() {
    return followers;
  }

  public void setFollowers(int followers) {
    this.followers = followers;
  }

  public int getMaleFollowers() {
    return maleFollowers;
  }

  public void setMaleFollowers(int maleFollowers) {
    this.maleFollowers = maleFollowers;
  }

  public int getFemaleFollowers() {
    return femaleFollowers;
  }

  public void setFemaleFollowers(int femaleFollowers) {
    this.femaleFollowers = femaleFollowers;
  }

  public int getSubscriptions() {
    return subscriptions;
  }

  public void setSubscriptions(int subscriptions) {
    this.subscriptions = subscriptions;
  }

  public int getGroupSubscriptions() {
    return groupSubscriptions;
  }

  public void setGroupSubscriptions(int groupSubscriptions) {
    this.groupSubscriptions = groupSubscriptions;
  }

  public int getMaleSubscriptions() {
    return maleSubscriptions;
  }

  public void setMaleSubscriptions(int maleSubscriptions) {
    this.maleSubscriptions = maleSubscriptions;
  }

  public int getFemaleSubscriptions() {
    return femaleSubscriptions;
  }

  public void setFemaleSubscriptions(int femaleSubscriptions) {
    this.femaleSubscriptions = femaleSubscriptions;
  }
}
