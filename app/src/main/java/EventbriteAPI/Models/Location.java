package EventbriteAPI.Models;

import org.json.JSONObject;

/**
 * Created by Agnieszka on 25.03.2018.
 */

public class Location implements JSONPopulator{
    public String Latitude;
    public String Within;
    public String Longitude;
    public String Address;

    @Override
    public void populate(JSONObject data) {
        Latitude = data.optString("latitude");
        Within = data.optString("within");
        Longitude = data.optString("longitude");
        Address = data.optString("address");
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public String getWithin() {
        return Within;
    }

    public void setWithin(String within) {
        Within = within;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }
}
