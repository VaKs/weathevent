package POJO;

import android.content.Context;
import android.os.AsyncTask;

import com.google.android.gms.awareness.state.Weather;

import java.util.concurrent.ExecutionException;

import weathevent.weathevent.WeatheventActivity;

/**
 * Created by Usuario on 23/04/2018.
 */

public class MyWeather {
    Weather weather;
    float temperature;
    int condition;
    float dewPoint;
    float feelsLikeTemeperature;
    int humidity;
    String conditions_icon;

    public MyWeather(){
    }

    public MyWeather(float temperature, String conditions_icon, float dewPoint, float feelsLikeTemperature, int humidity){
        this.temperature = temperature;
        this.conditions_icon = conditions_icon;
        this.dewPoint = dewPoint;
        this.feelsLikeTemeperature = feelsLikeTemperature;
        this.humidity = humidity;

    }

    public float getTemperature() {
        return temperature;
    }

    public float getDewPoint() {
        return dewPoint;
    }

    public float getFeelsLikeTemeperature() {
        return feelsLikeTemeperature;
    }
    public String getConditions(){
        return conditions_icon;
    }
    public int getHumidity(){
        return humidity;
    }
    public int getConditionNumber(){
        return condition;
    }
    public void setConditionNumber(int condition){
        this.condition = condition;
    }
    public void setTemperature(float temperature){
        this.temperature = temperature;
    }
    public void setConditions(String conditions_icon){
        this.conditions_icon = conditions_icon;
    }
    public void setDewPoint(float dewPoint){
        this.dewPoint = dewPoint;
    }
    public void setFeelsLikeTemeperature(float feelsLikeTemeperature){
        this.feelsLikeTemeperature = feelsLikeTemeperature;
    }
    public void setHumidity(int humidity){
        this.humidity = humidity;
    }

}
