package Fragment.Adapters;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.List;

import Database.StorageManager;
import Database.StorageManagerImplFirebaseRoom;
import Fragment.FriendsFragment;
import POJO.User;
import weathevent.weathevent.R;
import weathevent.weathevent.WeatheventActivity;

public class AsyncAddFriendsListChange extends AsyncTask<String, Void, Boolean> {
    public AsyncListChange delegate;
    private WeakReference<WeatheventActivity> activity = null;
    public User user;
    private StorageManager storageManager;
    private WeakReference<FriendsFragment> fragmentWeak;
    private Context context;

    public AsyncAddFriendsListChange(WeatheventActivity activity, FriendsFragment fragmentActivity) {
        this.activity = new WeakReference<>(activity);
        this.fragmentWeak=new WeakReference<FriendsFragment>(fragmentActivity);
        this.context=activity.getApplicationContext();
        storageManager = StorageManagerImplFirebaseRoom.getInstance(context);
    }

    public AsyncAddFriendsListChange() {
    }

    @Override
    protected Boolean doInBackground(String... params) {
        String newEmail=params[0];

        User currentUser=storageManager.getCurrentUser();

        User newFriend = null;
        try{
            newFriend=storageManager.getUserByEmail(newEmail);
        } catch(Exception e){
            Log.e("error",e.getMessage());
        }
        if(newFriend!=null){
            currentUser.addFriend(newFriend.getEmail());
            storageManager.updateUser(currentUser);

            fragmentWeak.get().friends.removeAll(fragmentWeak.get().friends);
            List<String> friendsEmails=currentUser.getFriendsList();
            for (String email: friendsEmails) {
                User friend = storageManager.getUserByEmail(email);
                fragmentWeak.get().friends.add(friend);
            }

        }else{
            return false;
        }
        return true;
    }

    @Override
    protected void onPostExecute(Boolean updated) {
        fragmentWeak.get().processFinish(updated);
    }

}

