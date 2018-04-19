package Tasks;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.ref.WeakReference;

import Database.AppDatabase;
import Database.UserDAO;
import Fragment.LoginFragment;
import POJO.User;
import weathevent.weathevent.LogInActivity;
import weathevent.weathevent.WeatheventActivity;

public class AsyncUserLogInTask extends AsyncTask<String, Void, User> {
    public AsyncResponse delegate;
    private WeakReference<LogInActivity> activity = null;
    public User user;
    private DatabaseReference mDatabase= FirebaseDatabase.getInstance().getReference();
    private String password;
    private UserDAO userDAO;

    public AsyncUserLogInTask(LogInActivity activity) {
        this.activity = new WeakReference<>(activity);
        userDAO = AppDatabase.getInstance(activity.getApplicationContext()).userDAO();
    }

    public AsyncUserLogInTask() {
    }

    @Override
    protected User doInBackground(final String... params) {
        this.password=params[1];
        final User[] user = {null};
        try {
            DatabaseReference firebaseUser = mDatabase.child("Users").child(params[0]);

            ValueEventListener postListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    user[0] = dataSnapshot.getValue(User.class);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            };

            User userFromDb= userDAO.getUserByEmail(params[0]);

            if (userFromDb!= null){
                user[0]=userFromDb;


            } else {
                firebaseUser.addListenerForSingleValueEvent(postListener);
                Thread.sleep(1200);
            }
       } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        return user[0];
    }

    @Override
    protected void onPostExecute(User user) {
        activity.get().processFinish(user,password);
    }

}

