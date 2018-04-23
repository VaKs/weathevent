package Fragment;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.awareness.Awareness;
import com.google.android.gms.awareness.SnapshotClient;
import com.google.android.gms.awareness.snapshot.WeatherResponse;
import com.google.android.gms.awareness.state.Weather;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnSuccessListener;


import weathevent.weathevent.R;

import static com.google.android.gms.awareness.Awareness.getSnapshotClient;

/**
 * Created by Rafal on 2018-03-25.
 */

public class WeatherFragment extends Fragment implements FragmentsInterface, GoogleApiClient.OnConnectionFailedListener {

    public static final String TAG = "WeatherFragment";
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    private Context context;
    SnapshotClient client;
    Weather weather;
    float temperature;
    int conditions;
    private TextView conditions_icon;
    private TextView temperature_degree;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getActivity().getApplicationContext();
        return inflater.inflate(R.layout.fragment_weather, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        conditions_icon = view.findViewById(R.id.conditions_icon);
        temperature_degree = view.findViewById(R.id.temperature_degree);
        client = Awareness.getSnapshotClient(context);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_LOCATION);

        }
        client.getWeather().addOnSuccessListener(new OnSuccessListener<WeatherResponse>(){


            @Override
            public void onSuccess(WeatherResponse weatherResponse) {
                weather = weatherResponse.getWeather();
                conditions = weather.getConditions()[0];
                temperature = weather.getTemperature(2);
                temperature_degree.setText(temperature + "º");
                setConditionIcon(conditions);



            }
        });


    }
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
    public void setConditionIcon(int conditions){
        switch (conditions){
            case 1 :
                conditions_icon.setText("CLEAR");
                break;
            case 2 :
                conditions_icon.setText("CLOUDY");
                break;
            case 3 :
                conditions_icon.setText("FOGGY");
                break;
            case 4 :
                conditions_icon.setText("HAZY");
                break;
            case 5 :
                conditions_icon.setText("ICY");
                break;
            case 6 :
                conditions_icon.setText("RAINY");
                break;
            case 7 :
                conditions_icon.setText("SNOWY");
                break;
            case 8 :
                conditions_icon.setText("STORMY");
                break;
            case 9 :
                conditions_icon.setText("WINDY");
                break;
            case 0 :
                conditions_icon.setText("UNKNOWN");
                break;
        }

    }

}
