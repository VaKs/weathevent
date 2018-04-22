package Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import Database.StorageManager;
import Database.StorageManagerImplFirebaseRoom;
import Database.UserDAO;
import POJO.StoredUser;
import POJO.User;
import weathevent.weathevent.R;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Rafal on 2018-03-25.
 */

public class PreferencesFragment extends Fragment implements FragmentsInterface {

    public static final String TAG = "PreferencesFragment";
    private Context context;
    private User currentUser;
    private StorageManager storageManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        storageManager = StorageManagerImplFirebaseRoom.getInstance(context);
        return inflater.inflate(R.layout.fragment_preferences, container, false);
    }
    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        //Get current user
        currentUser=storageManager.getCurrentUser();
    }
}
