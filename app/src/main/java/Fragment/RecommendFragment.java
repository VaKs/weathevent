package Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import EventbriteAPI.Models.EventsList;
import EventbriteAPI.Models.Search;
import weathevent.weathevent.R;
import weathevent.weathevent.WeatheventActivity;

/**
 * Created by Rafal on 2018-03-25.
 */

public class RecommendFragment extends Fragment implements FragmentsInterface {

    public static final String TAG = "RecommendFragment";
    RecyclerView recyclerView;
    EventAdapter adapter;
    EventsList eventList;
    Context context;
    String categories;
    String location;
    TextView weatherInfo;
    String textRecommended;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recommend, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        //getting the recyclerview from xml

        //categories = ((WeatheventActivity) getActivity()).getCategories();
        location = ((WeatheventActivity) getActivity()).getLocation();
        Search searchEvents = new Search();
        if(){
            //weatherInfo.setBackground();
            textRecommended=getString(R.string.clear_recommendation);
            weatherInfo.setText(textRecommended);
            categories = "107,108,117,110";
        }else if(){
            //weatherInfo.setBackground();
            textRecommended=getString(R.string.cloudy_recommendation);
            weatherInfo.setText(textRecommended);
            categories = "114,106,112";
        }else if(){
            //weatherInfo.setBackground();
            textRecommended=getString(R.string.foggy_recommendation);
            weatherInfo.setText(textRecommended);
            categories = "115,118";
        }else if(){
            //weatherInfo.setBackground();
            textRecommended=getString(R.string.hazy_recommendation);
            weatherInfo.setText(textRecommended);
            categories = "105,102";
        }else if(){
            //weatherInfo.setBackground();
            textRecommended=getString(R.string.rainy_recommendation);
            weatherInfo.setText(textRecommended);
            categories = "110";
        }else if(){
            //weatherInfo.setBackground();
            textRecommended=getString(R.string.snowy_recommendation);
            weatherInfo.setText(textRecommended);
            categories = "119,120";
        }else if(){
            //weatherInfo.setBackground();
            textRecommended=getString(R.string.stormy_recommendation);
            weatherInfo.setText(textRecommended);
            categories = "116";
        }else if(){
            //weatherInfo.setBackground();
            textRecommended=getString(R.string.windy_recommendation);
            weatherInfo.setText(textRecommended);
            categories = "101,115";
        }else(){
            textRecommended=getString(R.string.unknown_recommendation);
            weatherInfo.setText(textRecommended);
            categories = "199";
        }
        searchEvents.setLocationAddress(location);
        searchEvents.setSortBy("best");
        searchEvents.setCategories(categories);
        eventList = ((WeatheventActivity) getActivity()).eventbriteSearch(searchEvents);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        adapter = new EventAdapter(context, eventList);
        recyclerView.setAdapter(adapter);
    }
}
