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

import POJO.Event;
import weathevent.weathevent.R;
import weathevent.weathevent.WeatheventActivity;

public class RecentEventFragment extends Fragment implements FragmentsInterface {

    public static final String TAG = "RecentEventFragment";
    FragmentActivity listener;

    private Event event;
    private Button btn_recentEventDetails;


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

        //references
        btn_recentEventDetails = view.findViewById(R.id.btn_toEventDetails);

        //listeners
        /*btn_recentEventDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((WeatheventActivity) getActivity()).showEventPreviewFragment(event);
            }
        });*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.listener = null;
    }
}

