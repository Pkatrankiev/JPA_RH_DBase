package OldClases;

import java.io.IOException;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;

public class weatherTest {
	
    public static void main(String[] args) {
       
    	 String json = "";
        try {
        	
            json = Jsoup.connect("http://dataservice.accuweather.com/forecasts/v1/hourly/12hour/46798?apikey=fcGmRd0w1Seo6c0s1srkThdoHghZedMg&language=bg&details=true&metric=true").ignoreContentType(true).execute().body();
            System.out.print(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            JSONArray baseArray = new JSONArray(json);
            for (int i = 0; i < baseArray.length(); i++) {
                JSONObject jsonObject = baseArray.getJSONObject(i);
                System.out.println(jsonObject);
                String DateTime = jsonObject.getString("DateTime");
                System.out.println();
                
//                int EpochDateTime = jsonObject.getInt("EpochDateTime");
//                 SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
////                java.util.Date start = ff.parse("01.01.1970");
//                java.util.Date newDate = new java.util.Date (EpochDateTime*1000) ;
//               
////                String date = sdf.format(newDate);
//                String date = new java.text.SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new java.util.Date (EpochDateTime));
//                LocalDate localDate2 = Instant.ofEpochMilli(EpochDateTime).atZone(ZoneId.systemDefault()).toLocalDate();
//             
//                LocalDateTime localdate = LocalDateTime.ofInstant(Instant.ofEpochMilli(EpochDateTime), ZoneId.systemDefault());
//                
                int WeatherIcon = jsonObject.getInt("WeatherIcon");
                String IconPhrase = jsonObject.getString("IconPhrase");
                int CloudCover = jsonObject.getInt("CloudCover");
                Boolean IsDaylight = jsonObject.getBoolean("HasPrecipitation");
                //This is for Temperature
                //Similar thing can be done for RealFeelTemperature, WetBulbTemperature etc 
                JSONObject temperature = jsonObject.getJSONObject("Temperature");
//                JSONObject temperature1 = temperature.getJSONObject("Temperature");
                double temperatureValue = temperature.getDouble("Value");
                String temperatureUnit = temperature.getString("Unit");
               
                JSONObject Wind = jsonObject.getJSONObject("Wind");
                JSONObject WindSpeed = Wind.getJSONObject("Speed");
                double WindSpeedValue = WindSpeed.getDouble("Value");
                String WindSpeedUnit = WindSpeed.getString("Unit");
                
                JSONObject Direction = Wind.getJSONObject("Direction");
                String DirectionLocalized = Direction.getString("Localized");
//                System.out.println(WeatherIcon);
//                System.out.println(IsDaylight);
         //       Similarly other values can be parsed
//                System.out.println(EpochDateTime);
//                System.out.println(date);
//                System.out.println(localDate2);
//                System.out.println(localdate);
                
                System.out.println();
                
                int index = DateTime.indexOf("T");
                System.out.println("Дата: "+DateTime.substring(0, index));
                 System.out.println("Час: "+DateTime.substring(index+1, index+6));
                System.out.println(IconPhrase+"  "+CloudCover+"%");
                System.out.println("Температура "+temperatureValue+" "+temperatureUnit);
                System.out.println("Вятър "+WindSpeedValue+" "+WindSpeedUnit+" "+DirectionLocalized);
                
                System.out.println();              

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}