package EventbriteAPI.Models;

import org.json.JSONObject;

/**
 * Created by Agnieszka on 25.03.2018.
 */

public class Description implements JSONPopulator {

    public String Text;
    public String Html;

    @Override
    public void populate(JSONObject data) {
        Text = data.optString("text");
        Html = data.optString("html");
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }

    public String getHtml() {
        return Html;
    }

    public void setHtml(String html) {
        Html = html;
    }
}
