package EventbriteAPI.service;

import android.net.Uri;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import Connection.JSONResponder;

/**
 * Created by Agnieszka on 25.03.2018.
 */

public class EventbriteService extends AsyncTask<String, Void, String> {

    private static final String Token = "FRBVU4556DR3S6HFNKBH";

    private EventbriteServiceCallback callback;

    public EventbriteService(EventbriteServiceCallback callback){
        this.callback = callback;
    }

    @Override
    protected String doInBackground(String... strings) {
        String endpoint = String.format("https://www.eventbriteapi.com/v3/events/?token=FRBVU4556DR3S6HFNKBH", Token);
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https")
                .authority("www.eventbriteapi.com")
                .appendPath("v3")
                //.appendPath(params[])
                .appendQueryParameter("token", Token);

        /*
        Rafal: instead of that, try get the information from JSONobject
        which you receive from our new function (example below)

                try {
                    URL url = new URL(builder.build().toString());
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setDoInput(true);
                        // Get response
                    connection.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        */
        try {
            final JSONObject jsonFromURL = JSONResponder.getJSONFromURL(new URL(builder.build().toString()));
            // here you can extract your data from JSONobject
            String id = jsonFromURL.getString("id");
        } catch (IOException e) {
            System.out.println(e.toString());
        } catch (JSONException e) {
            System.out.println(e.toString());
        }
        return null;
    }
}

