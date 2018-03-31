package Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import weathevent.weathevent.R;

/**
 * Created by Rafal on 2018-03-25.
 */

public class MapFragment extends Fragment implements FragmentsInterface {

    private static final String tag = "MapFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_map, container, false);
    }

    public static String getFragmentTag() {
        return tag;
    }

}
