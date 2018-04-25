package Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

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

    private EditText et_distance;
    private EditText et_city;


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
        et_distance = view.findViewById(R.id.et_distance);
        et_city = view.findViewById(R.id.et_city);






    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if(!"".equals(et_distance.getText().toString())){
            int distance = Integer.parseInt(et_distance.getText().toString());
            currentUser.getPreference().setDistance(distance);
        }
        String city = et_city.getText().toString();
        if(!"".equals(city)){
            currentUser.getPreference().setCity(city);
        }

        //save new preferences if they are different
        new Thread(new Runnable() {
            @Override
            public void run(){
                storageManager.updateUser(currentUser);
            }
        }).start();

    }
}
