package Database.Tasks;
import android.os.AsyncTask;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.ref.WeakReference;

import Database.AppDatabase;
import Database.StorageManager;
import Database.StorageManagerImplFirebase;
import Database.UserDAO;
import POJO.StoredUser;
import POJO.User;
import weathevent.weathevent.LogInActivity;

public class AsyncUserLogInTask extends AsyncTask<String, Void, Boolean> {
    public AsyncResponse delegate;
    private WeakReference<LogInActivity> activity = null;
    public User user;
    private StorageManager storageManager;

    public AsyncUserLogInTask(LogInActivity activity) {
        this.activity = new WeakReference<>(activity);
        storageManager = StorageManagerImplFirebase.getInstance(activity.getApplicationContext());
    }

    public AsyncUserLogInTask() {
    }

    @Override
    protected Boolean doInBackground(final String... params) {
        String email=params[0];
        String password=params[1];

        return storageManager.validateLogin(email,password);
    }

    @Override
    protected void onPostExecute(Boolean loginPassed) {
        activity.get().processFinish(loginPassed);
    }

}

