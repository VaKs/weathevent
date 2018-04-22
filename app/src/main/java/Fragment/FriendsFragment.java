package Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import Database.StorageManager;
import Database.StorageManagerImplFirebaseRoom;

import POJO.User;
import weathevent.weathevent.R;


/**
 * Created by Rafal on 2018-03-25.
 */

public class FriendsFragment extends Fragment implements FragmentsInterface {

    public static final String TAG = "FriendsFragment";
    private User currentUser;
    private Context context;
    private StorageManager storageManager;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_friends, container, false);
        context = getActivity().getApplicationContext();
        storageManager = StorageManagerImplFirebaseRoom.getInstance(context);
        return view;
    }
    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        currentUser = storageManager.getCurrentUser();



    }

}
