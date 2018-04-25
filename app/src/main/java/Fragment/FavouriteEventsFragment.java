package Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import Database.StorageManager;
import Database.StorageManagerImplFirebaseRoom;
import EventbriteAPI.Models.Description;
import EventbriteAPI.Models.End;
import EventbriteAPI.Models.Event;
import EventbriteAPI.Models.EventsList;
import EventbriteAPI.Models.Logo;
import EventbriteAPI.Models.Name;
import EventbriteAPI.Models.Search;
import EventbriteAPI.Models.Start;
import EventbriteAPI.Models.Venue;
import EventbriteAPI.service.EventBriteDownloadImage;
import Fragment.Adapters.FriendsListAdapter;
import POJO.User;
import weathevent.weathevent.R;
import weathevent.weathevent.WeatheventActivity;
import Fragment.Adapters.FavouriteEventAdapter;

/**
 * Created by Rafal on 2018-03-25.
 */

public class FavouriteEventsFragment extends Fragment implements FragmentsInterface {

    public static final String TAG = "FavouriteEventsFragment";
    EventsList eventsList;
    Event favouriteEvent;
    private StorageManager storageManager;
    private User currentUser;
    private Context context;
    RecyclerView rvFavourites;
    RecyclerView recyclerView;
    FavouriteEventAdapter adapter;
    EventsList eventList;
    String categories;
    String location;
    ImageView iv_event_image;
    TextView tv_event_title;
    TextView tv_event_description;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favourite, container, false);
        context = getActivity().getApplicationContext();
        storageManager = StorageManagerImplFirebaseRoom.getInstance(context);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        currentUser=storageManager.getCurrentUser();
        eventList= new EventsList();
        //currentUser.getFavoriteEventIdsList();

        String url;
        for(int i=0;i<currentUser.getFavoriteEventIdsList().size();i++){
            url= "https://www.eventbriteapi.com/v3/events/"+currentUser.getFavoriteEventIdsList().get(i).toString()+"/";
            favouriteEvent =((WeatheventActivity) getActivity()).eventbriteGetEvent(url);
        }


        eventList.add(favouriteEvent);

        Name nombre = favouriteEvent.getName();
        Log.i("Evento del guaenOOOO3",nombre.getText());


        rvFavourites=view.findViewById(R.id.rv_favourites);
        rvFavourites.setLayoutManager(new LinearLayoutManager(context));
        adapter = new FavouriteEventAdapter(context,eventList,this);
        rvFavourites.setAdapter(adapter);


        /*
        public ArrayList<Tuple> getCloserEvents(Double currentLatitude, Double currentLongitude) {
        ArrayList<Tuple> closerEventsList = new ArrayList<>();
        Search searchEvents = new Search();
        searchEvents.setLocationLatitude(currentLatitude.toString());
        searchEvents.setLocationLongitude(currentLongitude.toString());
        searchEvents.setLocationWithin(km);
        EventsList eventsList = eventbriteSearch(searchEvents);
        ArrayList<Event> events = eventsList.getEvents();
        LatLng eventLocation;
        for (int i = 0; i < events.size(); i++) {
            String venueId = events.get(i).getVenueId();
            Venue venue;
            venue = eventbritegetVenue(venueId);
            Double evLatitude = Double.parseDouble(venue.getLatitude());
            Double evLongitude = Double.parseDouble(venue.getLongitude());
            eventLocation = new LatLng(evLatitude, evLongitude);
            String name = venue.getName();
            Tuple tuple = new Tuple(eventLocation, name);
            closerEventsList.add(tuple);
        }
        return closerEventsList;
    }

        */

        //geting parameters from object Event
       /* Name name = favouriteEvent.getName();
        Description description = favouriteEvent.getDescription();
        Start start = favouriteEvent.getStart();
        End end = favouriteEvent.getEnd();
        Boolean isFree = favouriteEvent.isFree();
        Logo eventLogo = favouriteEvent.getLogo();
        String eventLogoURL = eventLogo.getUrl();
        String eventName = name.getText();
        String eventDescription = description.getText();
        String eventStart = start.getLocal();
        String eventEnd = end.getLocal();

        /*
        //setting Date and image
        //example of values
        iv_event_image = view.findViewById(R.id.iv_event_image);
        tv_event_title = view.findViewById(R.id.tv_event_title);
        tv_event_description = view.findViewById(R.id.tv_event_description);
        tv_event_date = view.findViewById(R.id.tv_event_date);
        tv_event_rating = view.findViewById(R.id.tv_event_rating);
        tv_event_price = view.findViewById(R.id.tv_event_price);
        fab_favourite = view.findViewById(R.id.fab_favourite);
        fab_map = view.findViewById(R.id.fab_map);
        Date dateStart = new Date();
        Date dateEnd = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        try {
            dateStart = format.parse(eventStart);
            dateEnd = format.parse(eventEnd);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (isFree == true) {
            tv_event_price.setText(String.valueOf("Free"));
        } else {
            tv_event_price.setText(String.valueOf("Consult the price"));
        }
        tv_event_date.setText(dateStart.toLocaleString());

        try {
            new EventBriteDownloadImage((ImageView) iv_event_image )
                    .execute(eventLogoURL).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }*/
    }
}
