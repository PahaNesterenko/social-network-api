package org.social_network_api.vkImpl;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.social_network_api.domain.Group;
import org.social_network_api.domain.User;
import org.social_network_api.domain.Users;
import org.social_network_api.interfaces.SocialNetworkApi;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.social_network_api.Constants.*;


/**
 * Library for VK API. Class contains number of methods which allows to get data from social web VK
 * @author Pasha
 *
 */
@Component
public class VkImpl implements SocialNetworkApi {

  private static Logger log = Logger.getLogger(VkImpl.class.getName());
  public static HashMap<Long, String> cities = new HashMap<Long, String>();
  public static HashMap<Long, String> countries = new HashMap<Long, String>();


  /**
   * Method return random user of Vk who meet some requirements( should not be deactivated, name should not be
   * "DELETED", user should set its city and should have some friends)
   * @return proper user User
  g	 */
	/*public User getRandUser() throws IOException, ParseException {

		Random rand = new Random();
		User user;
		do {
			user = getUser(rand.nextInt(150000000));
		} while (user.isDeactivated() || user.getName().equals("DELETED") || user.getCity().equals("n/d")
				|| user.getFriendList().size() == 0);// get user with at least
														// minimum info
		return user;
	}*/


  /**
   * Method returns User who is linked with id number in VK. Method send HTTP get request and get JSON
   * script with user information then parse it and fill instance of User
   * @return Filled User instance
   */
  public User getUser(int id) {
    UriComponentsBuilder urlBuilder = UriComponentsBuilder.fromHttpUrl(VK_HOST + USER_GET_METHOD);
    urlBuilder.queryParam(USER_IDS, id);
    urlBuilder.queryParam(FIELDS_PARAMETER_KEY, FIELDS_PARAMETER_VALUE);

    String url = urlBuilder.build().toUriString();

    System.out.println(url);

    Users users = new RestTemplate().getForObject( url, Users.class);
    System.out.println(users.getUsers()[0]);

    User user = users.getUsers()[0];

    List<Integer> friendList = getFriendList(id);
    user.setFriendList(friendList);
    user.setFriendsNum(friendList.size());

    List<Integer> friendsParameters = getFriendParameters(id);
    if (friendsParameters.size() != 0) {
      user.setMaleFriends(friendsParameters.get(1));
      user.setFemaleFriends(friendsParameters.get(2));
    }

    List<Integer> followers = getFollowers(id);
    if (followers.size() != 0) {
      user.setFollowers(followers.get(0));
      user.setMaleFollowers(followers.get(1));
      user.setFemaleFollowers(followers.get(2));
    }

    List<Integer> subscriptions = getSubscriptions(id);
    if (subscriptions.size() != 0) {
      user.setGroupSubscriptions(subscriptions.get(0));
      user.setSubscriptions(subscriptions.get(1));
      user.setMaleSubscriptions(subscriptions.get(2));
      user.setFemaleSubscriptions(subscriptions.get(3));
    }

    return user;

    /*



    Object city = jsonObject.get("city");
    if (city instanceof String) {
      user.setCity((String) city);
    } else {
      user.setCity(getCitiesById((Long) city).replace("'", " "));
    }

    Object country = jsonObject.get("country");
    if (country instanceof String) {
      user.setCountry((String) country);
    } else {
      user.setCountry(getCountriesById((Long) country).replace("'", " "));
    }

    Object homeTown = jsonObject.get("home_town");
    if (homeTown instanceof String) {
      user.setHomeTown((String) homeTown);
    } else {
      user.setHomeTown(getCitiesById((Long) homeTown).replace("'", " "));
    }




   */



  }

  /**
   * Method returns group, which has at least 500 members. Method get group with random number
   * then check if it contains enough members and if it has name.
   * @return random group
   */
  public Group getRandGroup(){

    Random rand = new Random();
    Group group;
    do {
      group = getGroup(rand.nextInt(15000000));
    } while (group.getName().equals("") || group.getMembersCount() < 500);
    return group;
  }

  /**
   * Method fill information about group to Group instance
   * @returnFilled Group instance
   */
  public Group getGroup(int gid) {
    Group group = new Group();
    String method = "groups.getById";
    String parametr1 = "group_id=" + gid;
    String parametr2 = "fields=city,country,place,description,wiki_page,members_count,counters,start_date,finish_date,activity,status,verified,site";

    InputStreamReader in = request(method, parametr1, parametr2);
    JSONParser jsonParser = new JSONParser();
    JSONObject jsonObject = null;
    try {
      jsonObject = (JSONObject) jsonParser.parse(in);
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ParseException e) {
      e.printStackTrace();
    }
    JSONArray jarr = (JSONArray) jsonObject.get("response");
    jsonObject = (JSONObject) jarr.get(0);

    Object temp;

    group.setGid(gid);

    String name = (String) jsonObject.get("name");
    group.setName(name);

    String screenName = (String) jsonObject.get("screen_name");
    group.setScreenName(screenName);

    int isClosed = (int) (long) jsonObject.get("is_closed");
    if (isClosed == 1) {
      group.setClosed(true);
    }

    String type = (String) jsonObject.get("type");
    group.setType(type);

    Object city = jsonObject.get("city");
    if (city instanceof String) {
      group.setCity((String) city);
    } else {
      group.setCity(getCitiesById((Long) city).replace("'", " "));
    }

    Object country = jsonObject.get("country");
    if (country instanceof String) {
      group.setCountry((String) country);
    } else {
      group.setCountry(getCountriesById((Long) country).replace("'", " "));
    }

    String wikiPage = (String) jsonObject.get("wiki_page");
    group.setWikiPage(wikiPage);

    String description = (String) jsonObject.get("description");
    group.setDescription(description);

    temp = jsonObject.get("members_count");
    if (temp != null) {
      int membersCount = (int) (long) temp;
      group.setMembersCount(membersCount);
    }

    String startDate = (String) jsonObject.get("start_date");
    group.setStartDate(startDate);

    String finishDate = (String) jsonObject.get("finish_date");
    group.setFinishDate(finishDate);

    String activity = (String) jsonObject.get("activity");
    group.setActivity(activity);

    temp = jsonObject.get("status");
    if (temp != null) {
      String status = (String) temp;
      group.setStatus(status);
    }

    temp = jsonObject.get("verified");
    if (temp != null) {
      int verified = (int) (long) temp;
      group.setVerified(verified);
    }

    String site = (String) jsonObject.get("site");
    group.setSite(site);

    String photoAddress = (String) jsonObject.get("photo_big");
    group.setPhotoAddress(photoAddress.replace("\\/", "/"));

    log.log(Level.INFO,
            "group obtained: id - " + gid + " name - " + group.getName() + " description - "
                    + group.getDescription());
    return group;

  }

  /**
   * Method return friends of user in format of ArrayList<Integer> with friends ids
   * @param id number of user
   * @return ArrayList<Integer> list of friends id's
   */
  public List<Integer> getFriendList(int id) {

    String method = "friends.get";
    String parametr1 = "user_id=" + id;
    // String parametr2 = "order=random";

    InputStreamReader in = request(method, parametr1);
    JSONParser jsonParser = new JSONParser();
    JSONObject jsonObject = null;
    try {
      jsonObject = (JSONObject) jsonParser.parse(in);
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ParseException e) {
      e.printStackTrace();
    }
    JSONArray jarr = (JSONArray) jsonObject.get("response");
    List<Integer> list = new ArrayList<Integer>();
    try {
      for (Object o : jarr) {
        list.add((int) (long) o);
      }
    } catch (NullPointerException e) {
      log.log(Level.INFO, "NPE!!!!");
    }
    log.log(Level.INFO, "Friend list from user " + id + " odtained. There are " + list.size() + " friends");
    // in.close();
    return list;
  }

  /**
   * Method show users friends parameters.
   * @param id number of user
   * @return ArrayList<Integer> result ( result[0] - number of friends,
   *         result[1] - male friends, result[2] - female friends
   */
  public List<Integer> getFriendParameters(int id){

    List<Integer> result = new ArrayList<Integer>();
    String method = "friends.get";
    String parametr1 = "user_id=" + id;
    String parametr2 = "fields=sex";

    InputStreamReader in = request(method, parametr1, parametr2);
    JSONParser jsonParser = new JSONParser();
    JSONObject jsonObject = null;
    try {
      jsonObject = (JSONObject) jsonParser.parse(in);
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ParseException e) {
      e.printStackTrace();
    }
    JSONArray jarr = (JSONArray) jsonObject.get("response");
    if (jarr.size() == 0) {
      return result;
    }

    int userNum = 0;
    int males = 0;
    int females = 0;
    int sex;

    for (Object obj : jarr) {
      JSONObject jobj = (JSONObject) obj;
      userNum++;
      sex = (int) (long) jobj.get("sex");
      if (sex == 1) {
        females++;
      } else if (sex == 2) {
        males++;
      }
    }
    result.add(userNum);
    result.add(males);
    result.add(females);
    return result;
  }

  /**
   * Method remove smiles from string, used to clean status string from 'smiles'.
   * @return Cleaned string
   */
  private String removeSmiles(String s) {
    char[] init = s.toCharArray();
    String rez = "";
    for (int i = 0; i < init.length; ++i) {
      if (i < init.length - 1) {
        if ((init[i] == (char) 92) && (init[i + 1] == 'x')) {
          i += 3;
          continue;
        }
      }
      if (init[i] == (char) 39) {
        continue;
      }
      rez += init[i];
    }
    return rez;
  }

  /**
   * Method remove non alphabetic characters from string, but leave the space symbol, used for clean city and
   * country strings
   * @return Cleaned string
   */
  private String removeNonAlphaChars(String s) {
    char[] init = s.toCharArray();
    String rez = "";
    for (int i = 0; i < init.length; ++i) {
      if (!Character.isAlphabetic(init[i]) && init[i] != ' ') {
        continue;
      }
      rez += init[i];
    }
    return rez;
  }

  /**
   * This method returns the city that linked with city number. Method
   * send get request to vk method and get the String with country name.
   * Method check the internal Map container has this number, if not get country through
   * http request and add country to internal container.
   * @param id
   * @return city or n/d if number linked with nothing
   */
  private String getCitiesById(Long id) {

    if (id == null || id == 0) {
      return "n/d";
    }

    if (cities.containsKey(id)) {
      return cities.get(id);
    }

    String method = "database.getCitiesById";
    String parametr1 = "city_ids=" + id;

    InputStreamReader in = request(method, parametr1);
    JSONParser jsonParser = new JSONParser();
    JSONObject jsonObject = null;
    try {
      jsonObject = (JSONObject) jsonParser.parse(in);
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ParseException e) {
      e.printStackTrace();
    }
    JSONArray jarr = (JSONArray) jsonObject.get("response");
    if (jarr.size() == 0) {
      return "n/d";
    }
    jsonObject = (JSONObject) jarr.get(0);
    String city = (String) jsonObject.get("name");
    cities.put(id, city);
    return city;
  }

  /**
   * This method returns the country that linked with country number. Method
   * send get request to vk method and get the String with country name.
   * Method check the internal Map contains number, if not get country through
   * http request and add country to internal container.
   * @param id number of country
   * @return city or n/d if number link to nothing
   */
  private String getCountriesById(Long id) {

    if (id == null || id == 0) {
      return "n/d";
    }
    if (countries.containsKey(id)) {
      return countries.get(id);
    }

    String method = "database.getCountriesById";
    String parametr1 = "country_ids=" + id;

    InputStreamReader in = request(method, parametr1);
    JSONParser jsonParser = new JSONParser();
    JSONObject jsonObject = null;
    try {
      jsonObject = (JSONObject) jsonParser.parse(in);
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ParseException e) {
      e.printStackTrace();
    }
    JSONArray jarr = (JSONArray) jsonObject.get("response");
    jsonObject = (JSONObject) jarr.get(0);
    String country = (String) jsonObject.get("name");
    countries.put(id, country);
    return country;
  }

  /**
   * Method get subscriptions of user by id
   * @return List<Integer> result ( result[0] = number of grups,
   *         result[1] = number of users are subscribed, result[2] = number of
   *         male, result[3] = number of female
   */
  public List<Integer> getSubscriptions(int id) {
    List<Integer> result = new ArrayList<Integer>();
    if (id == 0) {
      return result;
    }

    String method = "users.getSubscriptions";
    String parametr1 = "user_id=" + id;
    String parametr2 = "extended=1";
    String parametr3 = "fields=sex";

    InputStreamReader in = request(method, parametr1, parametr2, parametr3);
    JSONParser jsonParser = new JSONParser();
    JSONObject jsonObject = null;
    try {
      jsonObject = (JSONObject) jsonParser.parse(in);
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ParseException e) {
      e.printStackTrace();
    }
    JSONArray jarr = (JSONArray) jsonObject.get("response");
    if (jarr.size() == 0) {
      return result;
    }

    int groupNum = 0;
    int userNum = 0;
    int males = 0;
    int females = 0;
    String type;
    int sex;

    for (Object obj : jarr) {
      JSONObject jobj = (JSONObject) obj;
      type = (String) jobj.get("type");
      if (type.equals("page")) {
        groupNum++;
      } else if (type.equals("profile")) {
        userNum++;
        sex = (int) (long) jobj.get("sex");
        if (sex == 1) {
          females++;
        } else if (sex == 2) {
          males++;
        }
      }
    }
    result.add(groupNum);
    result.add(userNum);
    result.add(males);
    result.add(females);
    return result;
  }

  /**
   * Method get followers of user by id
   * @return ArrayList<Integer> result ( result[0] = number of users are
   *         followed, result[1] = number of male, result[2] = number of
   *         female
   * @throws ParseException
   * @throws IOException
   */
  public List<Integer> getFollowers(int id) {
    List<Integer> result = new ArrayList<Integer>();
    if (id == 0) {
      return result;
    }

    String method = "users.getFollowers";
    String parametr1 = "user_id=" + id;
    String parametr2 = "count=1000";
    String parametr3 = "fields=sex";

    InputStreamReader in = request(method, parametr1, parametr2, parametr3);
    JSONParser jsonParser = new JSONParser();
    JSONObject jsonObject = null;
    try {
      jsonObject = (JSONObject) jsonParser.parse(in);
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ParseException e) {
      e.printStackTrace();
    }
    jsonObject = (JSONObject) jsonObject.get("response");
    JSONArray jarr = (JSONArray) jsonObject.get("items");
    if (jarr.size() == 0) {
      return result;
    }

    int userNum = 0;
    int males = 0;
    int females = 0;
    int sex;

    for (Object obj : jarr) {
      JSONObject jobj = (JSONObject) obj;
      userNum++;
      sex = (int) (long) jobj.get("sex");
      if (sex == 1) {
        females++;
      } else if (sex == 2) {
        males++;
      }
    }
    result.add(userNum);
    result.add(males);
    result.add(females);
    return result;
  }

  /**
   * Perform request to vk url and print response to console (for testing)
   */
  private void printReqest(String... args) {
    String urlString = VK_HOST + args[0] + "?" + args[1];
    for (int i = 2; i < args.length; ++i) {
      urlString = urlString + "&" + args[i];
    }
    // System.out.println(urlString);
    URL url = null;
    try {
      url = new URL(urlString);
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }
    URLConnection conn = null;
    if (url != null) {
      try {
        conn = url.openConnection();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    InputStreamReader in = null;
    do {
      try {
        in = new InputStreamReader(conn.getInputStream(), "UTF-8");
      } catch (IOException e) {

      }
    } while (in == null);
    int i = 0;
    char[] arr = new char[1024];
    while (true) {
      try {
        i = in.read(arr);
      } catch (IOException e) {
        e.printStackTrace();
      }
      if (i == -1) {
        break;
      }
      System.out.println(String.valueOf(arr));

    }
    try {
      in.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  /**
   * Perform request to vk url
   */
  private InputStreamReader request(String... args) {
    String urlString = VK_HOST + args[0] + "?" + args[1];
    for (int i = 2; i < args.length; ++i) {
      urlString = urlString + "&" + args[i];
    }
    URL url = null;
    try {
      url = new URL(urlString);
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }
    InputStreamReader in = null;
    do {
      URLConnection conn = null;
      try {
        conn = url.openConnection();
      } catch (IOException e) {
        e.printStackTrace();
      }
      try {
        in = new InputStreamReader(conn.getInputStream(), "UTF-8");
      } catch (IOException e) {
        log.log(Level.WARNING, "IOException meanwhile URL open connection");
      }
    } while (in == null);

    return in;
  }

  /**
   * Method download photo from server and write it to file.
   */
  public void savePhoto(String urlString, String path) {
    urlString = urlString.replace("\\/", "/");
    if (!path.endsWith(".jpg")) {
      path = path + ".jpg";
    }
    URL url = null;
    try {
      url = new URL(urlString);
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }
    URLConnection conn = null;
    try {
      conn = url.openConnection();
    } catch (IOException e) {
      e.printStackTrace();
    }
    assert conn != null;
    InputStreamReader in = null;
    try {
      in = new InputStreamReader(conn.getInputStream(), "ISO-8859-1");
    } catch (IOException e) {
      e.printStackTrace();
    }

    File file = new File(path);
    FileOutputStream fos = null;
    try {
      fos = new FileOutputStream(file);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    int i = 0;
    while (true) {
      try {
        i = in.read();
      } catch (IOException e) {
        e.printStackTrace();
      }
      if (i == -1) {
        break;
      }
      try {
        if (fos != null) {
          fos.write(i);
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    log.log(Level.INFO, "file " + urlString + " downloaded to " + path);
    try {
      assert fos != null;
      fos.flush();

    in.close();
    fos.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Method get field of photo address from User to obtain url, and get user name, last name and id for obtain file name.
   * Then delegates downloading and writing file to method savePhoto( String url, String path). File name consist of "userName userLastName userId.
   */
  public void savePhoto(User user, String path){
    String urlString = user.getPhotoAddress().replace("\\/", "/");
    String fullPath = path + "/" + user.getName() + "_" + user.getLastName() + "_" + user.getId();
    savePhoto(urlString, fullPath);
  }

}
