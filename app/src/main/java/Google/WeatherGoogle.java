package Google;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;

import com.google.android.gms.awareness.Awareness;
import com.google.android.gms.awareness.SnapshotClient;
import com.google.android.gms.awareness.snapshot.WeatherResponse;
import com.google.android.gms.awareness.state.Weather;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnSuccessListener;

import java.lang.ref.WeakReference;
import java.util.concurrent.ExecutionException;

import Fragment.WeatherFragment;
import POJO.MyWeather;
import weathevent.weathevent.WeatheventActivity;

/**
 * Created by Usuario on 23/04/2018.
 */

public class WeatherGoogle extends AsyncTask<Void, Void , MyWeather> implements GoogleApiClient.OnConnectionFailedListener, AsyncResponseWeather {
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    private WeakReference<WeatheventActivity> activityWeak;
    private WeatheventActivity activity;
    private Context context;
    SnapshotClient client;
    Weather weather;
    MyWeather myWeather;
    float temperature;
    int conditions;
    float dewPoint;
    float feelsLikeTemeperature;
    int humidity;
    String conditions_icon;
    public AsyncResponseWeather delegate;
    WeakReference<WeatherFragment> fragmentWeak;
    WeatherFragment fragment;


    public WeatherGoogle (WeatheventActivity activity, Context context, WeatherFragment fragment){

        this.context = context;
        this.activityWeak = new WeakReference<>(activity);
        this.activity = activity;
        this.fragmentWeak = new WeakReference<WeatherFragment>(fragment);
    }

    @Override
    protected MyWeather doInBackground(Void... voids) {
        client = Awareness.getSnapshotClient(context);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_LOCATION);

        }
        client.getWeather().addOnSuccessListener(new OnSuccessListener<WeatherResponse>(){


            @Override
            public void onSuccess(WeatherResponse weatherResponse) {
                weather = weatherResponse.getWeather();
                myWeather.setConditions(setConditions(activity,context));
                myWeather.setDewPoint(setDewPoint(activity,context));
                myWeather.setFeelsLikeTemeperature(setFeelsLikeTemeperature(activity,context));
                myWeather.setHumidity(setHumidity(activity,context));
                myWeather.setTemperature(setTemperature(activity,context));
            }
        });

        return myWeather;
    }
    @Override
    protected void onPostExecute(MyWeather myWeather){
        fragmentWeak.get().processFinish(myWeather);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
    private float setTemperature(WeatheventActivity activity, Context context) {
        AsyncTask weatherTask = new WeatherGoogle(activity, context, fragment);
        try {
            weather = (Weather) weatherTask.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        temperature = weather.getTemperature(2);
        return temperature;
    }

    private float setDewPoint(WeatheventActivity activity, Context context) {
        AsyncTask weatherTask = new WeatherGoogle(activity, context, fragment);
        try {
            weather = (Weather) weatherTask.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        dewPoint = weather.getDewPoint(2);
        return dewPoint;
    }

    private float setFeelsLikeTemeperature(WeatheventActivity activity, Context context) {
        AsyncTask weatherTask = new WeatherGoogle(activity, context, fragment);
        try {
            weather = (Weather) weatherTask.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        feelsLikeTemeperature = weather.getFeelsLikeTemperature(2);
        return feelsLikeTemeperature;
    }
    private String setConditions(WeatheventActivity activity, Context context){
        AsyncTask weatherTask = new WeatherGoogle(activity, context, fragment);
        try {
            weather = (Weather) weatherTask.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        conditions = weather.getConditions()[0];
        setConditionIcon(conditions);
        return conditions_icon;
    }
    private String setConditionsNumber(WeatheventActivity activity, Context context){
        AsyncTask weatherTask = new WeatherGoogle(activity, context, fragment);
        try {
            weather = (Weather) weatherTask.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        conditions = weather.getConditions()[0];
        return conditions_icon;
    }
    private int setHumidity(WeatheventActivity activity, Context context){
        AsyncTask weatherTask = new WeatherGoogle(activity, context, fragment);
        try {
            weather = (Weather) weatherTask.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
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

    @Override
    public void processFinish(MyWeather myWeather) {
    }
}
