package Database;

import android.content.Context;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import POJO.StoredUser;
import POJO.User;

/**
 * Created by vaks on 07/04/2018.
 */

public class StorageManagerImplFirebase implements StorageManager {

    private DatabaseReference mDatabase= FirebaseDatabase.getInstance().getReference();
    private DatabaseReference firebaseUsers = mDatabase.child("Users");
    private static StorageManagerImplFirebase storageManagerImplFirebase;
    private User currentUser;
    private Context context;


    private StorageManagerImplFirebase(Context context){
        this.context=context;
    }
    public static StorageManagerImplFirebase getInstance(Context context){
        if(storageManagerImplFirebase==null) storageManagerImplFirebase= new StorageManagerImplFirebase(context);
        return storageManagerImplFirebase;

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

            if (finalUserfb[0] == null) {
                firebaseUsers.child(user.getEmail()).setValue(user);
                currentUser=user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void updateUser(User user) {
        firebaseUsers.child(user.getEmail()).setValue(user);
        if(user.getEmail()==currentUser.getEmail()){
            currentUser=user;
        }
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
            Thread.sleep(1300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return user[0];


    }
    @Override
    public boolean validateLogin(String email, String password) {
        final User[] user = {null};
        try {
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

            if(user[0]!= null && user[0].checkPassword(password)){
                currentUser = user[0];
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
    public User getCurrentUser(){
        return currentUser;
    }
}
