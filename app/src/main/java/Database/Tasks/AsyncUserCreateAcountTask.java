package Database.Tasks;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import java.lang.ref.WeakReference;
import Database.StorageManager;
import Database.StorageManagerImplFirebase;
import POJO.User;
import weathevent.weathevent.LogInActivity;
import weathevent.weathevent.R;

import static android.content.Context.MODE_PRIVATE;

public class AsyncUserCreateAcountTask extends AsyncTask<User, Void, String> {
    public AsyncResponse delegate;
    private WeakReference<LogInActivity> activity = null;
    public User user;
    private StorageManager storageManager;
    private Context context;

    public AsyncUserCreateAcountTask(LogInActivity activity) {
        this.activity = new WeakReference<>(activity);
        storageManager = StorageManagerImplFirebase.getInstance(activity.getApplicationContext());
        this.context=activity.getApplicationContext();

    }

    public AsyncUserCreateAcountTask() {
    }

    @Override
    protected String doInBackground(User... params) {


        storageManager.addNewUser(params[0]);

        String message=context.getString(R.string.create_account);

        return message;
    }

    @Override
    protected void onPostExecute(String message) {
        activity.get().processFinish(message);
    }

}

