package Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import EventbriteAPI.Models.EventsList;
import EventbriteAPI.Models.Search;
import weathevent.weathevent.R;
import weathevent.weathevent.WeatheventActivity;

public class ExploreFragmentList extends Fragment implements FragmentsInterface {

    public static final String TAG = "ExploreFragmentList";
    //a list to store all the events
    //the recyclerview
    RecyclerView recyclerView;
    EventsList eventList;
    Context context;
    String categories;
    String location;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getActivity().getApplicationContext();
        WeatheventActivity weatheventActivity = new WeatheventActivity();
        categories = weatheventActivity.getCategories();
        location = weatheventActivity.getLocation();
        return inflater.inflate(R.layout.fragment_explore_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        //getting the recyclerview from xml
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        //initializing the eventlist
        eventList = new EventsList();
        WeatheventActivity weatheventActivity = new WeatheventActivity();
        Search searchEvents = new Search();
        searchEvents.setLocationAddress(location);
        searchEvents.setSortBy("best");
        searchEvents.setCategories(categories);
        eventList = weatheventActivity.eventbriteSearch(searchEvents);

        //creating recyclerview adapter
        EventAdapter adapter = new EventAdapter(context, eventList);

        //setting adapter to recyclerview
        recyclerView.setAdapter(adapter);
    }
}