package EventbriteAPI.service;

import android.net.Uri;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;

import EventbriteAPI.Models.Event;
import EventbriteAPI.Models.EventsList;
import EventbriteAPI.Models.Search;
import weathevent.weathevent.WeatheventActivity;

/**
 * Created by Agnieszka on 25.03.2018.
 */

public class EventbriteService extends AsyncTask<Search, Void, EventsList> {

    private static final String Token = "FRBVU4556DR3S6HFNKBH";
    private EventsList eventsList;
    private WeakReference<WeatheventActivity> weakReference;

    public EventbriteService(WeatheventActivity activity){
        weakReference = new WeakReference<WeatheventActivity>(activity);
    }

    @Override
    protected EventsList doInBackground(Search... params) {
        eventsList = new EventsList();
        StringBuilder sBuilder = new StringBuilder();
        JSONArray events = null;
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https");
        builder.authority("www.eventbriteapi.com");
        builder.appendPath("v3");
        builder.appendPath("events");
        if(params[0].isHasParameters()==true) {
            builder.appendPath("search");
            if (!(params[0].sortBy.equals("null")))
                builder.appendQueryParameter("sort_by", params[0].sortBy);
            if (!(params[0].locationAddress.equals("null")))
                builder.appendQueryParameter("location.address", params[0].locationAddress);
            if (!(params[0].locationWithin.equals("null")))
                builder.appendQueryParameter("location.within", params[0].locationWithin);
            if (!(params[0].categories.equals("null")))
                builder.appendQueryParameter("categories", params[0].categories);
            if (!(params[0].rangeStart.equals("null")))
                builder.appendQueryParameter("start_date.range_start", params[0].rangeStart);
            if (!(params[0].rangeStartKeyWord.equals("null")))
                builder.appendQueryParameter("start_date.keyword", params[0].rangeStartKeyWord);
            if (!(params[0].locationLatitude.equals("null")))
                builder.appendQueryParameter("location.latitude", params[0].locationLatitude);
            if (!(params[0].locationLongitude.equals("null")))
                builder.appendQueryParameter("location.longitude", params[0].locationLongitude);
        }
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
            JSONObject pagination = object.getJSONObject("pagination");
            events = object.getJSONArray("events");

            for (int i=0; i<events.length(); i++){
                JSONObject eventJSONObject = events.getJSONObject(i);
                Event eventObject = new Event();
                eventObject.populate(eventJSONObject);
                eventsList.add(eventObject);
            }
        } catch (JSONException e) {
            System.out.println(e.toString());
        }
        return eventsList;
    }

    @Override
    protected void onPostExecute(EventsList eventsList) {
        myMethod();
    }

    public EventsList myMethod (){
        return eventsList;
    }
}

