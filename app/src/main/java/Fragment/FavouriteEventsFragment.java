package Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import EventbriteAPI.Models.Event;
import EventbriteAPI.Models.EventsList;
import EventbriteAPI.Models.Search;
import EventbriteAPI.Models.Venue;
import weathevent.weathevent.R;
import weathevent.weathevent.WeatheventActivity;

/**
 * Created by Rafal on 2018-03-25.
 */

public class FavouriteEventsFragment extends Fragment implements FragmentsInterface {

    public static final String TAG = "FavouriteEventsFragment";
    EventsList eventsList;
    Venue venue;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favourite, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        WeatheventActivity weatheventActivity = new WeatheventActivity();
        Search searchEvent = new Search();
        searchEvent.setLocationLatitude("51.7532339");
        searchEvent.setLocationLongitude("0.4596447999999782");
        searchEvent.setLocationWithin("5km");
        eventsList = weatheventActivity.eventbriteSearch(searchEvent);
        List<String> venues = new ArrayList<>();
        ArrayList<Event> events = eventsList.getEvents();
        for(int i=0; i < eventsList.size(); i++){
            venues.add(events.get(i).getVenueId());
        }
        for (int j=0; j<venues.size();j++){
            venue= weatheventActivity.eventbritegetVenue(venues.get(j));
            String latitude = venue.getLatitude();
            String longitude = venue.getLongitude();
        }
    }
}
