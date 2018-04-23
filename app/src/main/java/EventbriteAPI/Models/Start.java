package EventbriteAPI.Models;

import org.json.JSONObject;

import java.util.Date;

/**
 * Created by Agnieszka on 25.03.2018.
 */

public class Start implements JSONPopulator{
    public String Timezone;
    public String Local;
    public String Utc;

    @Override
    public void populate(JSONObject data) {
        Timezone = data.optString("timezone");
        Local = data.optString("local");
        //utc
    }

    public String getTimezone() {
        return Timezone;
    }

    public void setTimezone(String timezone) {
        Timezone = timezone;
    }

    public String getLocal() {
        return Local;
    }

    public void setLocal(String local) {
        Local = local;
    }

    public String getUtc() {
        return Utc;
    }

    public void setUtc(String utc) {
        Utc = utc;
    }
}
