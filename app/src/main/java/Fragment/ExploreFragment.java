package Fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;

import weathevent.weathevent.R;

/**
 * Created by Rafal on 2018-03-25.
 */

public class ExploreFragment extends Fragment implements FragmentsInterface {

    public static final String TAG = "ExploreFragment";
    android.support.v7.widget.GridLayout mainGrid;
    private Context context;

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

}
