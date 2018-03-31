package Fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import weathevent.weathevent.R;
import weathevent.weathevent.WeatheventActivity;

/**
 * Created by Rafal on 2018-03-25.
 */

public class DashboardFragment extends Fragment implements FragmentsInterface {

    private static final String tag = "DashboardFragment";
    FragmentActivity listener;

    private Button btn_explore;
    private Button btn_recommended;
    private Button btn_map;
    private Button btn_weather;
    private Button btn_favourites;
    private Button btn_friends;

    public static String getFragmentTag() {
        return tag;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            this.listener = (FragmentActivity) context;
        }
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //references
        btn_explore = view.findViewById(R.id.btn_navigation_explore);
        btn_recommended = view.findViewById(R.id.btn_navigation_recommended);
        btn_map = view.findViewById(R.id.btn_navigation_map);
        btn_weather = view.findViewById(R.id.btn_navigation_weather);
        btn_favourites = view.findViewById(R.id.btn_navigation_favourites);
        btn_friends = view.findViewById(R.id.btn_navigation_friends);

        //listeners

        btn_explore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((WeatheventActivity) getActivity()).showExploreFragment();
            }
        });

        btn_recommended.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((WeatheventActivity) getActivity()).showRecommendedFragment();
            }
        });

        btn_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((WeatheventActivity) getActivity()).showMapFragment();
            }
        });

        btn_weather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((WeatheventActivity) getActivity()).showWeatherFragment();
            }
        });

        btn_favourites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((WeatheventActivity) getActivity()).showFavouriteEventsFragment();
            }
        });

        btn_friends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((WeatheventActivity) getActivity()).showFriendsFragment();
            }
        });
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.listener = null;
    }
}
