package Fragment;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.awareness.SnapshotClient;
import com.google.android.gms.awareness.state.Weather;


import Fragment.Adapters.WeatherInterface;
import Google.AsyncResponseWeather;
import POJO.MyWeather;
import Google.WeatherGoogle;
import weathevent.weathevent.R;
import weathevent.weathevent.WeatheventActivity;


/**
 * Created by Rafal on 2018-03-25.
 */

public class WeatherFragment extends Fragment implements FragmentsInterface, WeatherInterface {

    public static final String TAG = "WeatherFragment";
    private Context context;
    Weather weather;
    float temperature;
    int conditions;
    int humidity;
    String conditionstext;
    private TextView tv_city;
    private TextView tv_temperature;
    private TextView tv_humidity;
    private TextView tv_condition;
    private ImageView iv_condition;
    private ProgressBar pb_humidity;
    private ProgressBar pb_rain;

    private TextView conditions_icon;
    private TextView temperature_degree;
    MyWeather myWeather;
    WeatheventActivity activity;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        activity = (WeatheventActivity) getActivity();
        context = getActivity().getApplicationContext();
        return inflater.inflate(R.layout.fragment_weather, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        tv_city = view.findViewById(R.id.tv_weather_city);
        tv_temperature = view.findViewById(R.id.tv_weather_temperature);
        tv_humidity = view.findViewById(R.id.tv_humidity);
        pb_humidity = view.findViewById(R.id.pg_humidity);
        iv_condition = view.findViewById(R.id.iv_weather_icon);
        tv_condition = view.findViewById(R.id.tv_weather_condition);

        activity.getMyWeather(this);


    }
    @Override
    public void weatherReceived(MyWeather myWeather){
        this.myWeather = myWeather;

        conditionstext = myWeather.getConditions();
        conditions = myWeather.getConditionNumber();
        setConditionIcon(conditions);
        humidity = myWeather.getHumidity();
        temperature = myWeather.getTemperature();
        System.out.println("condition: " + conditions_icon + " temperature: " + temperature);
    }




    public void setConditionIcon(int conditions){
        switch (conditions){
            case 1 :
                iv_condition.setVisibility(View.VISIBLE);
                iv_condition.setImageDrawable(getResources().getDrawable(R.drawable.clear));
                tv_condition.setText(getString(R.string.weather_clear));
                break;
            case 2 :
                iv_condition.setVisibility(View.VISIBLE);
                iv_condition.setImageDrawable(getResources().getDrawable(R.drawable.cloudy));
                tv_condition.setText(getString(R.string.weather_cloudy));
                break;
            case 3 :
                iv_condition.setVisibility(View.VISIBLE);
                iv_condition.setImageDrawable(getResources().getDrawable(R.drawable.fog));
                tv_condition.setText(getString(R.string.weather_foggy));
                break;
            case 4 :
                iv_condition.setVisibility(View.VISIBLE);
                iv_condition.setImageDrawable(getResources().getDrawable(R.drawable.haze));
                tv_condition.setText(getString(R.string.weather_hazy));
                break;
            case 5 :
                iv_condition.setVisibility(View.VISIBLE);
                iv_condition.setImageDrawable(getResources().getDrawable(R.drawable.icy));
                tv_condition.setText(getString(R.string.weather_icy));
                break;
            case 6 :
                iv_condition.setVisibility(View.VISIBLE);
                iv_condition.setImageDrawable(getResources().getDrawable(R.drawable.rain));
                tv_condition.setText(getString(R.string.weather_rainy));
                break;
            case 7 :
                iv_condition.setVisibility(View.VISIBLE);
                iv_condition.setImageDrawable(getResources().getDrawable(R.drawable.snow));
                tv_condition.setText(getString(R.string.weather_snowy));
                break;
            case 8 :
                iv_condition.setVisibility(View.VISIBLE);
                iv_condition.setImageDrawable(getResources().getDrawable(R.drawable.storm));
                tv_condition.setText(getString(R.string.weather_stormy));
                break;
            case 9 :
                iv_condition.setVisibility(View.VISIBLE);
                iv_condition.setImageDrawable(getResources().getDrawable(R.drawable.windy));
                tv_condition.setText(getString(R.string.weather_windy));
                break;
            case 0 :
                iv_condition.setVisibility(View.INVISIBLE);
                tv_condition.setText(getString(R.string.weather_unknown));
                break;
        }

    }
}
