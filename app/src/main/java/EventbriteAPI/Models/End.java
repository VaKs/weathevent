package EventbriteAPI.Models;

import org.json.JSONObject;

import java.util.Date;

/**
 * Created by Agnieszka on 25.03.2018.
 */

public class End implements JSONPopulator{

    public String Timezone;
    public Date Local;
    public Date Utc;


    @Override
    public void populate(JSONObject data) {
        Timezone = data.optString("timezone");
        //local
        //utc
    }

    public String getTimezone() {
        return Timezone;
    }

    public void setTimezone(String timezone) {
        Timezone = timezone;
    }

    public Date getLocal() {
        return Local;
    }

    public void setLocal(Date local) {
        Local = local;
    }

    public Date getUtc() {
        return Utc;
    }

    public void setUtc(Date utc) {
        Utc = utc;
    }
}
