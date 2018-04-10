package Database;

import android.annotation.SuppressLint;
import android.content.Context;

import java.util.List;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import POJO.User;
import Tasks.AsyncUserLogInTask;
import weathevent.weathevent.LogInActivity;

/**
 * Created by vaks on 07/04/2018.
 */

public class StorageManagerImplFirebaseRoom implements StorageManager {

    private DatabaseReference mDatabase= FirebaseDatabase.getInstance().getReference();
    private UserDAO userDAO;
    private static StorageManagerImplFirebaseRoom storageManagerImplFirebaseRoom;

    private StorageManagerImplFirebaseRoom(Context context){
        userDAO = AppDatabase.getInstance(context).userDAO();
    }
    public static StorageManagerImplFirebaseRoom getInstance(Context context){
        if(storageManagerImplFirebaseRoom==null) storageManagerImplFirebaseRoom= new StorageManagerImplFirebaseRoom(context);
        return storageManagerImplFirebaseRoom;

    }

    @Override
    public void addUser(final User user) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if (userDAO.getUserByEmail(user.getEmail()) == null) {
                        mDatabase.child("Users").child(user.getEmail()).setValue(user);
                        userDAO.addUser(user);
                    }
                }
            }).start();
    }

    @Override
    public void deleteUser(User user) {

    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public User getUserByEmail(final String email) {






        return null;

        /*
        final User[] user = {null};
        new Thread(new Runnable() {
            @Override
            public void run() {
                user[0] =userDAO.getUserByEmail(email);
                if(user[0] ==null) {

                    DatabaseReference firebaseUser = mDatabase.child("Users").child(email);

                    ValueEventListener postListener = new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            user[0] = dataSnapshot.getValue(User.class);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    };
                    firebaseUser.addListenerForSingleValueEvent(postListener);

                }
            }
        }).start();

        Thread
        return user[0];
        */

    }

    @Override
    public Boolean validateLogin(String email) {
        return null;
    }

    @Override
    public List<User> getUsers() {
        return null;
    }

    @Override
    public void addCategory(User user) {

    }

    @Override
    public void deleteCategory(User user) {

    }

    @Override
    public void updateCategory(User user) {

    }

    @Override
    public List<User> getCategories() {
        return null;
    }
}
