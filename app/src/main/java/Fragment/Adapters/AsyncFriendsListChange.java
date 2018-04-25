package Fragment.Adapters;
import android.os.AsyncTask;
import java.lang.ref.WeakReference;
import java.util.List;

import Database.StorageManager;
import Database.StorageManagerImplFirebase;
import Fragment.FriendsFragment;
import POJO.User;
import weathevent.weathevent.WeatheventActivity;

public class AsyncFriendsListChange extends AsyncTask<User, Void, Boolean> {
    public AsyncListChange delegate;
    private WeakReference<WeatheventActivity> activity = null;
    public User user;
    private StorageManager storageManager;
    private WeakReference<FriendsFragment> fragmentWeak;

    public AsyncFriendsListChange(WeatheventActivity activity,FriendsFragment fragmentActivity) {
        this.activity = new WeakReference<>(activity);
        this.fragmentWeak=new WeakReference<FriendsFragment>(fragmentActivity);
        storageManager = StorageManagerImplFirebase.getInstance(activity.getApplicationContext());
    }

    public AsyncFriendsListChange() {
    }

    @Override
    protected Boolean doInBackground(User... params) {
        User currentUser=params[0];

        fragmentWeak.get().friends.removeAll(fragmentWeak.get().friends);
        List<String> friendsEmails=currentUser.getFriendsList();
        for (String email: friendsEmails) {
            User friend = storageManager.getUserByEmail(email);
            fragmentWeak.get().friends.add(friend);
        }
        return true;
    }

    @Override
    protected void onPostExecute(Boolean updated) {
        fragmentWeak.get().processFinish(updated);
    }

}

