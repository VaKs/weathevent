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
            eventList.add(favouriteEvent);
        }

        rvFavourites=view.findViewById(R.id.rv_favourites);
        rvFavourites.setLayoutManager(new LinearLayoutManager(context));
        adapter = new FavouriteEventAdapter(context,eventList,this);
        rvFavourites.setAdapter(adapter);

    }
}
