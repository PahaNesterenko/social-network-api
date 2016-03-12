package org.social_network_api.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.social_network_api.process.VkImplUtils;
import org.social_network_api.utils.CustomJsonDateDeserializer;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * Class container for User of VK social web. Contains basic info about user
 */
@Entity
@Table(name="USER")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class User {

  @JsonProperty("uid")
  @Id
  @Column(name = "ID")
  private Long id;

  @JsonProperty("first_name")
  @Column(name = "FIRST_NAME")
  private String name;

  @JsonProperty("last_name")
  @Column(name = "LAST_NAME")
  private String lastName;

  @JsonProperty("deactivated")
  @Column(name = "DEACTIVATED")
  private String deactivated;

  @JsonProperty("verified")
  @Column(name = "VERIFIED")
  private boolean verified;

  @JsonProperty("sex")
  @Column(name = "SEX")
  private Sex sex;

  @JsonProperty("bdate")
  @JsonDeserialize(using = CustomJsonDateDeserializer.class)
  @Column(name = "BDATE")
  private Date bdate;

  @JsonProperty("city")
  @Transient
  private Long cityId;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name="CITY")
  @JsonIgnore
  private City city;

  @JsonProperty("country")
  @Transient
  private Long countryId;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "COUNTRY")
  @JsonIgnore
  private Country country;

  @JsonProperty("home_town")
  @Column(name = "HOME_TOWN")
  private String homeTown;

  @JsonProperty("photo_max_orig")
  @Column(name = "PHOTO_ADDRESS")
  private String photoAddress;

  @JsonProperty("hidden")
  @Transient
  private String hidden;

  @JsonProperty("online")
  @Transient
  private boolean online;

  @JsonProperty("status")
  @Column(name = "STATUS")
  private String status;

  @JsonProperty("followers_count")
  @Column(name = "FOLLOWERS_COUNT")
  private int followersCount;

  @JsonProperty("relation")
  @Column(name = "RELATION")
  private Relation relation;

  @JsonProperty("relation_partner")
  @Transient
  private User relation_partner;


  @Column(name = "FRIENDS_NUM")
  private int friendsNum;

  @Transient
  private List<Integer> friendList;

  @Column(name = "MALE_FRIENDS")
  private int maleFriends;

  @Column(name = "FEMALE_FRIENDS")
  private int femaleFriends;

  @Column(name = "FOLLOWERS")
  private int followers;

  @Column(name = "MALE_FOLLOWERS")
  private int maleFollowers;

  @Column(name = "FEMALE_FOLLOWERS")
  private int femaleFollowers;

  @Column(name = "SUBSCRIPTIONS")
  private int subscriptions;

  @Column(name = "GROUP_SUBSCRIPTIONS")
  private int groupSubscriptions;

  @Column(name = "MALE_SUBSCRIPTIONS")
  private int maleSubscriptions;

  @Column(name = "FEMALE_SUBSCRIPTIONS")
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

  public Boolean isValidUser()
  {
    return isNotBlank(name) && isNotBlank(lastName) && isBlank(deactivated) && VkImplUtils.userHasPhoto(this);
  }


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
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

  public String getDeactivated() {
    return deactivated;
  }

  public void setDeactivated(String deactivated) {
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

  public Date getBdate() {
    return bdate;
  }

  public void setBdate(Date bdate) {
    this.bdate = bdate;
  }

  public City getCity() {
    return city;
  }

  public void setCity(City city) {
    this.city = city;
  }

  public Country getCountry() {
    return country;
  }

  public void setCountry(Country country) {
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

  public List<Integer> getFriendList() {
    return friendList;
  }

  public void setFriendList(List<Integer> friendList) {
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

  public Long getCityId() {
    return cityId;
  }

  public void setCityId(Long cityId) {
    this.cityId = cityId;
  }

  public Long getCountryId() {
    return countryId;
  }

  public void setCountryId(Long countryId) {
    this.countryId = countryId;
  }
}
