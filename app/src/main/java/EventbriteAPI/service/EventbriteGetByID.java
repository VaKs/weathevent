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

import EventbriteAPI.Models.Event;
import EventbriteAPI.Models.Venue;
import weathevent.weathevent.WeatheventActivity;

public class EventbriteGetByID extends AsyncTask<String, Void, Event> {

    private static final String Token = "FRBVU4556DR3S6HFNKBH";
    private WeakReference<WeatheventActivity> weakReference;
    Event eventObject;

    public EventbriteGetByID(WeatheventActivity activity) {
        weakReference = new WeakReference<WeatheventActivity>(activity);
    }

    @Override
    protected Event doInBackground(String... params) {
        StringBuilder sBuilder = new StringBuilder();
        eventObject = new Event();
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https");
        builder.authority("www.eventbriteapi.com");
        builder.appendPath("v3");
        builder.appendPath("events");
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
            eventObject.populate(object);

        } catch (JSONException e) {
            System.out.println(e.toString());
        }
        return eventObject;
    }

    @Override
    protected void onPostExecute(Event eventObject) {
        myMethod();
    }

    public Event myMethod() {
        return eventObject;
    }
}