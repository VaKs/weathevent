package EventbriteAPI.service;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

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
        String urlSttring = params[0]+"?token="+Token;
        // example: urlSttring="https://www.eventbriteapi.com/v3/events/45001581943/?token=FRBVU4556DR3S6HFNKBH";
        try {
            URL url = new URL(urlSttring);
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