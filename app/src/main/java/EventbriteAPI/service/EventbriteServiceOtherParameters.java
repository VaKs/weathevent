package EventbriteAPI.service;

import android.net.Uri;
import android.os.AsyncTask;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;

import EventbriteAPI.Models.Venue;
import weathevent.weathevent.WeatheventActivity;

public class EventbriteServiceOtherParameters extends AsyncTask<String, Void, Venue> {

    private static final String Token = "FRBVU4556DR3S6HFNKBH";
    private WeakReference<WeatheventActivity> weakReference;
    Venue venueObject;

    public EventbriteServiceOtherParameters(WeatheventActivity activity) {
        weakReference = new WeakReference<WeatheventActivity>(activity);
    }

    @Override
    protected Venue doInBackground(String... params) {
        StringBuilder sBuilder = new StringBuilder();
        venueObject = new Venue();
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https");
        builder.authority("www.eventbriteapi.com");
        builder.appendPath("v3");
        builder.appendPath("venues");
        builder.appendPath(params[0]);
        builder.appendQueryParameter("token", Token);
        try {
            URL url = new URL(builder.build().toString());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String s = null;
            while ((s = reader.readLine()) != null) {
                sBuilder.append(s);
            }
            reader.close();
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            JSONObject object = new JSONObject(sBuilder.toString());
            venueObject.populate(object);

        } catch (JSONException e) {
            System.out.println(e.toString());
        }
        return venueObject;
    }

    @Override
    protected void onPostExecute(Venue venueObject) {
        myMethod();
    }

    public Venue myMethod() {
        return venueObject;
    }
}