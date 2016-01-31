package org.social_network_api.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CustomJsonDateDeserializer extends JsonDeserializer<Date>
{
  @Override
  public Date deserialize(JsonParser jsonparser,
                          DeserializationContext deserializationcontext) throws IOException, JsonProcessingException {

    SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyyy");
    String date = jsonparser.getText();
    try {
      return format.parse(date);
    } catch (ParseException e) {
      System.out.println("Bad date " + date);
    }
    return null;

  }

}
