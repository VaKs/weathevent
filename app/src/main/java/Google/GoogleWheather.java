package Google;

import com.google.android.gms.awareness.state.Weather;


import POJO.MyWeather;

/**
 * Created by Usuario on 23/04/2018.
 */

public class GoogleWheather {

    Weather weather;
    MyWeather myWeather;
    float temperature;
    int conditions;
    float dewPoint;
    float feelsLikeTemeperature;
    int humidity;
    String conditions_icon;
    //weather = weatherResponse.getWeather();


    private float setTemperature() {
        temperature = weather.getTemperature(2);
        return temperature;
    }

    private float setDewPoint() {
        dewPoint = weather.getDewPoint(2);

        return dewPoint;
    }

    private float setFeelsLikeTemeperature() {
        feelsLikeTemeperature = weather.getFeelsLikeTemperature(2);
        return feelsLikeTemeperature;
    }
    private String setConditions(){
        conditions = weather.getConditions()[0];
        setConditionIcon(conditions);
        return conditions_icon;
    }
    private int setConditionsNumber(){
        conditions = weather.getConditions()[0];
        return conditions;
    }
    private int setHumidity(){
        humidity = weather.getHumidity();
        return humidity;
    }

    private void setConditionIcon(int conditions){
        switch (conditions){
            case 1 :
                conditions_icon="CLEAR";
                break;
            case 2 :
                conditions_icon="CLOUDY";
                break;
            case 3 :
                conditions_icon="FOGGY";
                break;
            case 4 :
                conditions_icon="HAZY";
                break;
            case 5 :
                conditions_icon="ICY";
                break;
            case 6 :
                conditions_icon="RAINY";
                break;
            case 7 :
                conditions_icon="SNOWY";
                break;
            case 8 :
                conditions_icon="STORMY";
                break;
            case 9 :
                conditions_icon="WINDY";
                break;
            case 0 :
                conditions_icon="UNKNOWN";
                break;
        }

    }
    public MyWeather setMyWeather(Weather weather){
        myWeather.setConditions(setConditions());
        myWeather.setDewPoint(setDewPoint());
        myWeather.setFeelsLikeTemeperature(setFeelsLikeTemeperature());
        myWeather.setHumidity(setHumidity());
        myWeather.setTemperature(setTemperature());
        myWeather.setConditionNumber(setConditionsNumber());
        return myWeather;
    }

}
