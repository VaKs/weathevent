package Fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import Database.StorageManager;
import Database.StorageManagerImplFirebase;
import EventbriteAPI.Models.Description;
import EventbriteAPI.Models.End;
import EventbriteAPI.Models.Event;
import EventbriteAPI.Models.EventsList;
import EventbriteAPI.Models.Logo;
import EventbriteAPI.Models.Name;
import EventbriteAPI.Models.Search;
import EventbriteAPI.Models.Start;
import EventbriteAPI.service.EventBriteDownloadImage;
import POJO.Preference;
import POJO.User;
import weathevent.weathevent.R;
import weathevent.weathevent.WeatheventActivity;

public class RecentEventFragment extends Fragment implements FragmentsInterface,View.OnClickListener {

    public static final String TAG = "RecentEventFragment";
    FragmentActivity listener;

    private EventbriteAPI.Models.Event event;
    private Button btn_recentEventDetails;
    ImageView iv_recentEvent_image;
    TextView tv_recentEvent_title;
    Button btn_toEventDetails;
    Event recentEvent;
    User user;
    StorageManager storageManager;
    Long id = null;
    String city;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            this.listener = (FragmentActivity) context;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recent_event, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        storageManager = StorageManagerImplFirebase.getInstance(getActivity().getApplicationContext());

        //references
        btn_recentEventDetails = view.findViewById(R.id.btn_toEventDetails);

        //listeners
        btn_recentEventDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((WeatheventActivity) getActivity()).showEventPreviewFragment(recentEvent.getResourceUri());
            }
        });
        iv_recentEvent_image = view.findViewById(R.id.iv_recentEvent_image);
        btn_toEventDetails = view.findViewById(R.id.btn_toEventDetails);
        tv_recentEvent_title = view.findViewById(R.id.tv_recentEvent_title);

        user=storageManager.getCurrentUser();
        Search bestEvent = new Search();
        bestEvent.setRangeStartKeyWord("today");
        bestEvent.setLocationAddress("Spain");
        EventsList bestEvents = new EventsList();
        bestEvents = ((WeatheventActivity) getActivity()).eventbriteGetEvents();
        List<Event> bestList = new ArrayList<>();
        recentEvent = bestEvents.getEvent(0);

        Name name = recentEvent.getName();
        Logo eventLogo = recentEvent.getLogo();

        //binding the data with the viewholder views
        String eventLogoURL = eventLogo.getUrl();
        String eventName = name.getText();

        tv_recentEvent_title.setText(eventName);

        if(eventLogo.getUrl()==null){
            iv_recentEvent_image.setImageResource(R.drawable.bgevent_blue);
        }else {
            try {
                new EventBriteDownloadImage((ImageView) iv_recentEvent_image)
                        .execute(eventLogoURL).get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        btn_toEventDetails.setTag(recentEvent.getResourceUri());
        btn_toEventDetails.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == btn_toEventDetails.getId()) {
            ((WeatheventActivity) getActivity()).showEventPreviewFragment(btn_toEventDetails.getTag().toString());
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.listener = null;
    }
}

