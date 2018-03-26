package EventbriteAPI.Models;

import org.json.JSONObject;

/**
 * Created by Agnieszka on 25.03.2018.
 */

public class Venue implements JSONPopulator{
    public Address Address;
    public String ResourceUri;
    public String Id;
    public String Name;
    public String Latitude;
    public String Longitude;

    @Override
    public void populate(JSONObject data) {
        //Address = data.optString("address");
        ResourceUri = data.optString("resource_uri");
        Id = data.optString("id");
        Name = data.optString("name");
        Latitude = data.optString("latitude");
        Longitude = data.optString("longitude");
    }

    public EventbriteAPI.Models.Address getAddress() {
        return Address;
    }

    public void setAddress(EventbriteAPI.Models.Address address) {
        Address = address;
    }

    public String getResourceUri() {
        return ResourceUri;
    }

    public void setResourceUri(String resourceUri) {
        ResourceUri = resourceUri;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }
}
