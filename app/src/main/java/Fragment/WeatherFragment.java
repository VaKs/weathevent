package Fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.awareness.Awareness;
import com.google.android.gms.awareness.SnapshotClient;
import com.google.android.gms.awareness.snapshot.WeatherResponse;
import com.google.android.gms.awareness.snapshot.WeatherResult;
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
    private Context context;
    SnapshotClient client;
    Weather weather;
    float temperature;
    int conditions;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getActivity().getApplicationContext();
        return inflater.inflate(R.layout.fragment_weather, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        client = Awareness.getSnapshotClient(context);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {


            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
        }
        client.getWeather().addOnSuccessListener(new OnSuccessListener<WeatherResponse>(){


            @Override
            public void onSuccess(WeatherResponse weatherResponse) {
                weather = weatherResponse.getWeather();
                conditions = weather.getConditions()[0];
                temperature = weather.getTemperature(2);
                Log.i(TAG, "conditions: " + conditions + " temperature: " + temperature);
            }
        });


    }
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

}
