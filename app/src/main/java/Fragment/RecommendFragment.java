package Fragment;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

import EventbriteAPI.Models.EventsList;
import EventbriteAPI.Models.Search;
import EventbriteAPI.service.EventBriteDownloadImage;
import Fragment.Adapters.RecommendedAdapter;
import POJO.MyWeather;
import weathevent.weathevent.R;
import weathevent.weathevent.WeatheventActivity;

/**
 * Created by Rafal on 2018-03-25.
 */

public class RecommendFragment extends Fragment implements FragmentsInterface,View.OnClickListener, WeatherInterface {

    public static final String TAG = "RecommendFragment";
    RecyclerView recyclerView;
    RecommendedAdapter adapter;
    EventsList eventList;
    Context context;
    String categories;
    String location;
    TextView weatherInfo;
    String textRecommended;
    Button seeMore;
    MyWeather myWeather;
    String conditionNow;
    WeatheventActivity activity;
    EventsList eventsRecommended;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        activity = (WeatheventActivity) getActivity();
        context = getActivity().getApplicationContext();
        return inflater.inflate(R.layout.fragment_recommended_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        activity.getMyWeatherRecommended(this);
        //Location should be taken from userPreferences
        location = ((WeatheventActivity) getActivity()).getLocation();
        Search searchEvents = new Search();
        eventsRecommended=new EventsList();

        seeMore=view.findViewById(R.id.button_more_events);
        seeMore.setOnClickListener(this);
        conditionNow = "cloudy";
        String textRecommended;
        TextView weatherInfo;
        weatherInfo = view.findViewById(R.id.recommendedText);

        if(conditionNow=="clear"){
            //weatherInfo.setBackground(clear);
            textRecommended= getString(R.string.clear_recommendation);
            weatherInfo.setText(textRecommended);
            categories="101,102,103";
        }
        else if(conditionNow=="cloudy"){
            //weatherInfo.setBackground();
            textRecommended=getString(R.string.cloudy_recommendation);
            weatherInfo.setText(textRecommended);
            categories="113,112,111";
        }
        else if(conditionNow=="foggy"){
            //weatherInfo.setBackground()
            textRecommended=getString(R.string.foggy_recommendation);
            weatherInfo.setText(textRecommended);
            categories="113,112,114";
        }
        else if(conditionNow=="hazy"){
            //weatherInfo.setBackground();
            textRecommended=getString(R.string.hazy_recommendation);
            weatherInfo.setText(textRecommended);
            categories="104,105";
        }else if(conditionNow=="rainy"){
            //weatherInfo.setBackground();
            textRecommended=getString(R.string.rainy_recommendation);
            weatherInfo.setText(textRecommended);
            categories="106,107";
        }else if(conditionNow=="snowy"){
            //weatherInfo.setBackground();
            textRecommended=getString(R.string.snowy_recommendation);
            weatherInfo.setText(textRecommended);
            categories="109,108";
        }else if(conditionNow=="stormy"){
            //weatherInfo.setBackground();
            textRecommended=getString(R.string.stormy_recommendation);
            weatherInfo.setText(textRecommended);
            categories="114,110";
        }else if(conditionNow=="windy"){
            //weatherInfo.setBackground();
            textRecommended=getString(R.string.windy_recommendation);
            weatherInfo.setText(textRecommended);
            categories="114,115";
        }else{
            
            textRecommended=getString(R.string.unknown_recommendation);
            weatherInfo.setText(textRecommended);
            categories="119,199";
        }

        //searchEvents.setLocationAddress(location);
        searchEvents.setSortBy("best");
        searchEvents.setRangeStartKeyWord("today");
        searchEvents.setCategories(categories);
        eventList = ((WeatheventActivity) getActivity()).eventbriteSearch(searchEvents);
        for(int i=0; i<10; i++){
            if(eventList.size()>i) {
                eventsRecommended.add(eventList.getEvent(i));
            }
        }
        recyclerView = view.findViewById(R.id.recyclerView_recommended);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new RecommendedAdapter(context, eventList,RecommendFragment.this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        if( v == seeMore){
            ((WeatheventActivity) getActivity()).showExploreFragment();
        }
    }

    @Override
    public void weatherReceived(MyWeather myWeather) {
        //TODO
        /*
        HERE IS WHERE YOU NEED TO WORK
         */
        conditionNow = myWeather.getConditions();
    }
    public void goToPreview(String eventId){
        ((WeatheventActivity) getActivity()).showEventPreviewFragment(eventId);
    }
    public void goToEvents(){
        ((WeatheventActivity) getActivity()).showExploreFragment();
    }
}
