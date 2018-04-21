package Database.Tasks;
import android.os.AsyncTask;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.ref.WeakReference;
import Database.AppDatabase;
import Database.UserDAO;
import POJO.StoredUser;
import POJO.User;
import weathevent.weathevent.LogInActivity;

public class AsyncUserCreateAcountTask extends AsyncTask<User, Void, String> {
    public AsyncResponse delegate;
    private WeakReference<LogInActivity> activity = null;
    public User user;
    private DatabaseReference mDatabase= FirebaseDatabase.getInstance().getReference();
    private String password;
    private UserDAO userDAO;

    public AsyncUserCreateAcountTask(LogInActivity activity) {
        this.activity = new WeakReference<>(activity);
        userDAO = AppDatabase.getInstance(activity.getApplicationContext()).userDAO();
    }

    public AsyncUserCreateAcountTask() {
    }

    @Override
    protected String doInBackground(User... params) {

        //TODO: add this string through region file
        String message="There was a problem adding the new user";
        final User[] user = {null};
        try {
            DatabaseReference firebase = mDatabase.child("Users").child(params[0].getEmail());

            ValueEventListener postListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    user[0] = dataSnapshot.getValue(StoredUser.class);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            };

            User userFromDb= userDAO.getUserByEmail(params[0].getEmail());
            firebase.addListenerForSingleValueEvent(postListener);
            Thread.sleep(1200);

            if (userFromDb== null && user[0] == null){
                userDAO.addUser(params[0]);
                firebase.setValue(params[0]);
                //TODO: add this string through region file
                message="User added successfully";
            }
       } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        return message;
    }

    @Override
    protected void onPostExecute(String message) {
        activity.get().processFinish(message);
    }

}

