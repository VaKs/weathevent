package EventbriteAPI.Models;

import org.json.JSONObject;

import java.util.Currency;

/**
 * Created by Agnieszka on 25.03.2018.
 */

public class Ticket implements JSONPopulator {
    public Currency Cost;
    public Boolean Donation;
    public Boolean Free;

    @Override
    public void populate(JSONObject data) {
        Donation = data.optBoolean("donation");
        Free = data.optBoolean("free");
    }

    public Currency getCost() {
        return Cost;
    }

    public void setCost(Currency cost) {
        Cost = cost;
    }

    public Boolean getDonation() {
        return Donation;
    }

    public void setDonation(Boolean donation) {
        Donation = donation;
    }

    public Boolean getFree() {
        return Free;
    }

    public void setFree(Boolean free) {
        Free = free;
    }
}
