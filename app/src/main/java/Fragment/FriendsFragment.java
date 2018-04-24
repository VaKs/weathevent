package Fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Database.StorageManager;
import Database.StorageManagerImplFirebaseRoom;

import Database.Tasks.AsyncResponse;
import Fragment.Adapters.AsyncAddFriendsListChange;
import Fragment.Adapters.AsyncFriendsListChange;
import Fragment.Adapters.FriendsListAdapter;
import POJO.User;
import weathevent.weathevent.R;
import weathevent.weathevent.WeatheventActivity;


/**
 * Created by Rafal on 2018-03-25.
 */

public class FriendsFragment extends Fragment implements FragmentsInterface,AsyncResponse {

    public static final String TAG = "FriendsFragment";
    private User currentUser;
    private Context context;
    private StorageManager storageManager;
    RecyclerView rvFriendList;
    FloatingActionButton btn_add;
    public List<User> friends;
    FriendsListAdapter adapter;

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

        rvFriendList=view.findViewById(R.id.rv_friends);
        rvFriendList.setLayoutManager(new LinearLayoutManager(context));
        friends = new ArrayList<>();
        adapter = new FriendsListAdapter(context,friends);
        rvFriendList.setAdapter(adapter);

        new AsyncFriendsListChange(((WeatheventActivity) getActivity()),this).execute(currentUser);

        btn_add = view.findViewById(R.id.btn_addFriends);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(getContext());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.dialog_add_friend);

                Button positiveButton = dialog.findViewById(R.id.btn_accept);
                Button negativeButton = dialog.findViewById(R.id.btn_cancel);

                positiveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText ed_email = dialog.findViewById(R.id.ed_email);
                        String email = ed_email.getText().toString();
                        addFriend(email);

                        dialog.dismiss();
                    }
                });

                negativeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });



    }
    public void addFriend(String email){
        new AsyncAddFriendsListChange(((WeatheventActivity) getActivity()),this).execute(email);

    }

    @Override
    public void processFinish(boolean updated) {
        if(updated){
            adapter.notifyDataSetChanged();
        } else{
            CharSequence text = getString(R.string.toast_friendNotFound);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }
}
