package Database.Tasks;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import java.lang.ref.WeakReference;
import Database.StorageManager;
import Database.StorageManagerImplFirebaseRoom;
import POJO.User;
import weathevent.weathevent.LogInActivity;

import static android.content.Context.MODE_PRIVATE;

public class AsyncUserCreateAcountTask extends AsyncTask<User, Void, String> {
    public AsyncResponse delegate;
    private WeakReference<LogInActivity> activity = null;
    public User user;
    private StorageManager storageManager;
    private SharedPreferences shared;

    public AsyncUserCreateAcountTask(LogInActivity activity) {
        shared = activity.getApplicationContext().getSharedPreferences("weatheventSharedPreferences", MODE_PRIVATE);
        this.activity = new WeakReference<>(activity);
        storageManager = StorageManagerImplFirebaseRoom.getInstance(activity.getApplicationContext());

    }

    public AsyncUserCreateAcountTask() {
    }

    @Override
    protected String doInBackground(User... params) {


        storageManager.addNewUser(params[0]);


        SharedPreferences.Editor editor;
        editor = shared.edit();
        editor.putBoolean("logged",true);
        editor.putString("currentUserEmail",params[0].getEmail());
        editor.apply();

        storageManager.setCurrentUser();

        //TODO: add this string through region file
        String message="User added successfully";

        return message;
    }

    @Override
    protected void onPostExecute(String message) {
        activity.get().processFinish(message);
    }

}

