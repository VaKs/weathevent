package Database.Tasks;
import android.os.AsyncTask;

import java.lang.ref.WeakReference;
import Database.StorageManager;
import Database.StorageManagerImplFirebaseRoom;
import POJO.User;
import weathevent.weathevent.LogInActivity;

public class AsyncUserCreateAcountTask extends AsyncTask<User, Void, String> {
    public AsyncResponse delegate;
    private WeakReference<LogInActivity> activity = null;
    public User user;
    private StorageManager storageManager;

    public AsyncUserCreateAcountTask(LogInActivity activity) {
        this.activity = new WeakReference<>(activity);
        storageManager = StorageManagerImplFirebaseRoom.getInstance(activity.getApplicationContext());
    }

    public AsyncUserCreateAcountTask() {
    }

    @Override
    protected String doInBackground(User... params) {

        //TODO: add this string through region file

        storageManager.addNewUser(params[0]);
        String message="User added successfully";

        return message;
    }

    @Override
    protected void onPostExecute(String message) {
        activity.get().processFinish(message);
    }

}

