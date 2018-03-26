package EventbriteAPI.Models;

import org.json.JSONObject;

/**
 * Created by Agnieszka on 25.03.2018.
 */

public class Address implements JSONPopulator {
    public String Address1;
    //public object Address2;
    public String City;
    public String Region;
    public String PostalCode;
    public String Country;
    public String Latitude;
    public String Longitude;
    public String Localized_Address_Display;
    public String Localized_Area_Display;
    public String Localized_Multi_Line_Address_Display;

    @Override
    public void populate(JSONObject data) {
        Address1 = data.optString("address_1");
        City = data.optString("city");
        Region = data.optString("region");
        PostalCode = data.optString("postal_code");
        Country = data.optString("country");
        Latitude = data.optString("latitude");
        Longitude = data.optString("longitude");
        Localized_Address_Display = data.optString("localized_address_display");
        Localized_Area_Display = data.optString("localized_area_display");
        Localized_Multi_Line_Address_Display = data.optString("localized_multi_line_address_display");
    }

    public String getAddress1() {
        return Address1;
    }

    public void setAddress1(String address1) {
        Address1 = address1;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getRegion() {
        return Region;
    }

    public void setRegion(String region) {
        Region = region;
    }

    public String getPostalCode() {
        return PostalCode;
    }

    public void setPostalCode(String postalCode) {
        PostalCode = postalCode;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
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

    public String getLocalized_Address_Display() {
        return Localized_Address_Display;
    }

    public void setLocalized_Address_Display(String localized_Address_Display) {
        Localized_Address_Display = localized_Address_Display;
    }

    public String getLocalized_Area_Display() {
        return Localized_Area_Display;
    }

    public void setLocalized_Area_Display(String localized_Area_Display) {
        Localized_Area_Display = localized_Area_Display;
    }

    public String getLocalized_Multi_Line_Address_Display() {
        return Localized_Multi_Line_Address_Display;
    }

    public void setLocalized_Multi_Line_Address_Display(String localized_Multi_Line_Address_Display) {
        Localized_Multi_Line_Address_Display = localized_Multi_Line_Address_Display;
    }

}
