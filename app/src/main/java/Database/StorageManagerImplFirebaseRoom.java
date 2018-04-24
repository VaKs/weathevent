package Database;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.List;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import POJO.StoredUser;
import POJO.User;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by vaks on 07/04/2018.
 */

public class StorageManagerImplFirebaseRoom implements StorageManager {

    private DatabaseReference mDatabase= FirebaseDatabase.getInstance().getReference();
    private DatabaseReference firebaseUsers = mDatabase.child("Users");
    private UserDAO userDAO;
    private static StorageManagerImplFirebaseRoom storageManagerImplFirebaseRoom;
    private User currentUser;
    private Context context;
    private SharedPreferences shared;

    private StorageManagerImplFirebaseRoom(Context context){
        userDAO = AppDatabase.getInstance(context).userDAO();
        this.context=context;
    }
    public static StorageManagerImplFirebaseRoom getInstance(Context context){
        if(storageManagerImplFirebaseRoom==null) storageManagerImplFirebaseRoom= new StorageManagerImplFirebaseRoom(context);
        return storageManagerImplFirebaseRoom;

    }

    @Override
    public void addNewUser(final User user) {
        try {
            final StoredUser[] finalUserfb = new StoredUser[1];
            ValueEventListener postListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    finalUserfb[0] = dataSnapshot.getValue(StoredUser.class);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            };
            firebaseUsers.child(user.getEmail()).addListenerForSingleValueEvent(postListener);
            Thread.sleep(1200);

            if (userDAO.getUserByEmail(user.getEmail()) == null && finalUserfb[0] == null) {
                firebaseUsers.child(user.getEmail()).setValue(user);
                userDAO.addUser(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void updateUser(User user) {
        userDAO.updateUser(user);
        firebaseUsers.child(user.getEmail()).setValue(user);
    }

    @Override
    public User getUserByEmail(String email) {


        final User[] user = {null};
        DatabaseReference firebaseUser = mDatabase.child("Users").child(email);

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                user[0] = dataSnapshot.getValue(StoredUser.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        firebaseUser.addListenerForSingleValueEvent(postListener);

        try {
            Thread.sleep(1200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(user[0] ==null) {
            user[0] =userDAO.getUserByEmail(email);
        }

        return user[0];


    }
    /*
    * This method
    * */
    @Override
    public boolean validateLogin(String email, String password) {
        final User[] user = {null};
        try {
            User userFromDb= userDAO.getUserByEmail(email);

            if (userFromDb!= null){ // if User is in local database
                user[0]=userFromDb;

            } else { // if User is not in local database
                DatabaseReference firebaseUser = mDatabase.child("Users").child(email);

                ValueEventListener postListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        user[0] = dataSnapshot.getValue(StoredUser.class);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                };
                firebaseUser.addListenerForSingleValueEvent(postListener);
                Thread.sleep(1200);
            } if(user[0]!= null && user[0].checkPassword(password)){
                currentUser = user[0];


                SharedPreferences.Editor editor;
                shared = context.getSharedPreferences("weatheventSharedPreferences", MODE_PRIVATE);
                editor = shared.edit();
                editor.putBoolean("logged",true);
                editor.putString("currentUserEmail",currentUser.getEmail());

                editor.apply();
                return true;
            } else {
                return false;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public void setCurrentUser(){
        shared = context.getSharedPreferences("weatheventSharedPreferences", MODE_PRIVATE);
        String email= shared.getString("currentUserEmail","");
        this.currentUser=getUserByEmail(email);
    }
    public User getCurrentUser(){
        return currentUser;
    }

    @Override
    public void addCategory(User user) {
        //TODO
    }

    @Override
    public void deleteCategory(User user) {
        //TODO
    }

    @Override
    public void updateCategory(User user) {
        //TODO
    }

    @Override
    public List<User> getCategories() {
        //TODO
        return null;
    }
}
