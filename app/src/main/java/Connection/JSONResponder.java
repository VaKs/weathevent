package Connection;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Rafal on 2018-03-26.
 */

public class JSONResponder {

    /*
    *   This method let you get the JSON response object from any passed URL as argument
    *   When connection is broken it returns empty JSON object so first you have
    *   to check if that object has any content
    */
    public static JSONObject getJSONFromURL(URL url) {

        JSONObject JSONresponse = new JSONObject();

        try {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            int respondCode = connection.getResponseCode();
            //Sending GET request to URL
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            //generating JSON object based on response
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = bufferedReader.readLine()) != null) {
                response.append(inputLine);
            }
            bufferedReader.close();

            JSONresponse = new JSONObject(response.toString());

        } catch (Exception e) {
            // Here should be our information method about Exception
            System.out.println(e);
        }

        return JSONresponse;
    }

}
