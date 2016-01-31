package org.social_network_api;

public final class Constants {

  /**URL host for vk API https://api.vk.com/method*/
  public static final String VK_HOST = "https://api.vk.com/method/";
  public static final String USER_GET_METHOD = "users.get";
  public static final String USER_IDS = "user_ids";

  public static final String FIELDS_PARAMETER_KEY = "fields";
  public static final String FIELDS_PARAMETER_VALUE = "sex,city,bdate,country,home_town,photo_max_orig,status,relation";
  public static final String FILESTORE = "/home/pasha/temp/";

  public static final String CITY_GET_METHOD = "database.getCitiesById";
  public static final String CITY_IDS = "city_ids";

  public static final String COUNTRY_GET_METHOD = "database.getCountriesById";
  public static final String COUNTRY_IDS = "country_ids";
}
