package Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import Database.StorageManager;
import Database.StorageManagerImplFirebase;
import EventbriteAPI.Models.Name;
import EventbriteAPI.Models.Description;
import EventbriteAPI.Models.End;
import EventbriteAPI.Models.Event;
import EventbriteAPI.Models.Logo;
import EventbriteAPI.Models.Search;
import EventbriteAPI.Models.Start;
import EventbriteAPI.service.EventBriteDownloadImage;
import POJO.User;
import weathevent.weathevent.R;
import weathevent.weathevent.WeatheventActivity;

import static java.lang.Integer.parseInt;

/**
 * Created by Rafal on 2018-03-25.
 */

public class EventPreviewFragment extends Fragment implements FragmentsInterface,View.OnClickListener {

    public static final String TAG = "EventPreviewFragment";
    Event foundEvent;
    ImageView iv_event_image;
    TextView tv_event_title;
    TextView tv_event_description;
    TextView tv_event_date;
    TextView tv_event_rating;
    TextView tv_event_price;
    FloatingActionButton fab_favourite;
    FloatingActionButton fab_map;
    StorageManager storageManager;
    User currentUser;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        storageManager= StorageManagerImplFirebase.getInstance(this.getContext());
        return inflater.inflate(R.layout.fragment_event_preview, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        currentUser=storageManager.getCurrentUser();

        //looking for event in eventbrite
        foundEvent = ((WeatheventActivity) getActivity()).getCurrentEvent();

        //setting parameters for layout
        iv_event_image = view.findViewById(R.id.iv_event_image);
        tv_event_title = view.findViewById(R.id.tv_event_title);
        tv_event_description = view.findViewById(R.id.tv_event_description);
        tv_event_date = view.findViewById(R.id.tv_event_date);
        tv_event_rating = view.findViewById(R.id.tv_event_rating);
        tv_event_price = view.findViewById(R.id.tv_event_price);
        fab_favourite = view.findViewById(R.id.fab_favourite);
        fab_map = view.findViewById(R.id.fab_map);

        //geting parameters from object Event
        Log.i("ID2",foundEvent.getId().toString());

        Name name = foundEvent.getName();
        Description description = foundEvent.getDescription();
        Start start = foundEvent.getStart();
        End end = foundEvent.getEnd();
        Boolean isFree = foundEvent.isFree();
        Logo eventLogo = foundEvent.getLogo();
        String eventLogoURL = eventLogo.getUrl();
        String eventName = name.getText();
        String eventDescription = description.getText();
        String eventStart = start.getLocal();
        String eventEnd = end.getLocal();

        //setting parameters to layout
        tv_event_title.setText(eventName);
        tv_event_description.setText(eventDescription);
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
            new EventBriteDownloadImage((ImageView) iv_event_image)
                    .execute(eventLogoURL).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        fab_favourite.setOnClickListener(this);
        fab_map.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v == fab_favourite){

            //information thet we have added this event to favoutire
            Log.i("ID1",uriTreatment(foundEvent.getResourceUri()).toString());

            currentUser.addFavoriteEventId(uriTreatment(foundEvent.getResourceUri()));
            new Thread(new Runnable() {
                @Override
                public void run(){
                    storageManager.updateUser(currentUser);
                }
            }).start();



        }else if(v == fab_map){
            ((WeatheventActivity) getActivity()).setEvent(foundEvent);
            ((WeatheventActivity) getActivity()).showMapFragment();
            //do getEvent in map and set necessary latitude, longitude etc.
        }
    }
    public Long uriTreatment(String str) {
        Long i = null;
        if (str != null && str.length() > 0) {
            str = str.substring(0, str.length() - 1);
            str = str.substring(str.lastIndexOf("/")+1);

            i= Long.parseLong(str);

        }
        return i;
    }
}
