package Fragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.Calendar;

import EventbriteAPI.Models.Search;
import weathevent.weathevent.R;
import weathevent.weathevent.WeatheventActivity;

/**
 * Created by Rafal on 2018-03-25.
 */

public class ExploreFragment extends Fragment implements FragmentsInterface, View.OnClickListener {

    public static final String TAG = "ExploreFragment";
    android.support.v7.widget.GridLayout mainGrid;
    private Context context;
    Button buttonDate, buttonPeriod, buttonDistance;
    Button buttonSearch;
    private int dayChoosed, monthChoosed, yearChoosed;
    EditText locationText;
    String categories;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_explore, container, false);
        context = getActivity().getApplicationContext();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mainGrid = (android.support.v7.widget.GridLayout) view.findViewById(R.id.grid_fragment_explore);
        setColorEvent(mainGrid);
        buttonDate = view.findViewById(R.id.button_date);
        buttonPeriod = view.findViewById(R.id.button_partOfTime);
        buttonDistance = view.findViewById(R.id.button_distance);
        buttonSearch = view.findViewById(R.id.button_searchEvent);
        locationText = view.findViewById(R.id.select_city);
        buttonDate.setOnClickListener(this);
        buttonPeriod.setOnClickListener(this);
        buttonDistance.setOnClickListener(this);
        buttonSearch.setOnClickListener(this);
    }

    private void setColorEvent(android.support.v7.widget.GridLayout mainGrid){
        for(int i=0; i<mainGrid.getChildCount(); i++)
        {
            final android.support.v7.widget.CardView cardView = (android.support.v7.widget.CardView)mainGrid.getChildAt(i);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(cardView.getCardBackgroundColor().getDefaultColor() == -1){
                        cardView.setCardBackgroundColor(Color.parseColor("#FFBBDEFB"));
                    }else{
                        cardView.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"));
                    }
                }
            });

        }
    }


    @Override
    public void onClick(View v) {
        if(v == buttonDate){
            final Calendar c = Calendar.getInstance();
            dayChoosed = c.get(Calendar.DAY_OF_MONTH);
            monthChoosed = c.get(Calendar.MONTH);
            yearChoosed = c.get(Calendar.YEAR);
            //Unable to add window -- token null is not for an application -> error with context.
            DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    buttonDate.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                }
            },dayChoosed,monthChoosed,yearChoosed);
            datePickerDialog.show();
        }

        if( v == buttonSearch){
            WeatheventActivity weatheventActivity = new WeatheventActivity();
            Search searchEvents = new Search();
            searchEvents.setLocationAddress(locationText.getText().toString());
            searchEvents.setSortBy("best");
            searchEvents.setCategories(getCategories(mainGrid));
            weatheventActivity.eventbriteSearch(searchEvents);
        }
    }

    public String getCategories(android.support.v7.widget.GridLayout mainGrid){
        categories="";
        for(int i=0; i<mainGrid.getChildCount(); i++)
        {
            final android.support.v7.widget.CardView cardView = (android.support.v7.widget.CardView)mainGrid.getChildAt(i);
            if(!(cardView.getCardBackgroundColor().getDefaultColor() == -1)){
                categories = categories + ',' + cardView.getTag().toString();
                }
        }
        if(categories.equals(""))
            categories="null";
        else
            categories = categories.substring(1);

        return categories;
    }
}
