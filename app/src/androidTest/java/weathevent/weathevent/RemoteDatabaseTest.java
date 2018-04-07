package weathevent.weathevent;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
// firebase
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import POJO.Preference;
import POJO.User;


@RunWith(AndroidJUnit4.class)
public class RemoteDatabaseTest {
    static Context appContext = InstrumentationRegistry.getTargetContext();

    private DatabaseReference mDatabase;

    List<User> userList= new ArrayList<>();
    User user,user1,user2;
    Preference preference;

    @Before
    public void declaraciones(){



        user = new User("email","name","surname","pass");
        user1 = new User("email1","name1","surname1","pass1");
        user2 = new User("email2","name2","surname2","pass2");

        user.addFriend(user1);
        user.addFriend(user2);

        preference=new Preference(1000);

        userList= new ArrayList<>();
        userList.add(user);
        userList.add(user1);
        userList.add(user2);
    }
    @AfterClass
    public static void LimpiarDb() {

    }

    @Test
    public void generalTest() throws Exception {
        try {

            mDatabase = FirebaseDatabase.getInstance().getReference();

            DatabaseReference test = mDatabase.child("Test");

            ValueEventListener postListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot scoreSnapshot : dataSnapshot.getChildren()) {
                        User score = scoreSnapshot.getValue(User.class);
                        System.out.println("***Esta es la buena "+score.getEmail());
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            };
            test.addValueEventListener(postListener);

            User usuario = new User("EmailDePrueba","Nombre","Apellido","Contraseña3");

            test.child(usuario.getId().toString()).setValue(usuario);


        } catch (Exception e){
            System.out.print(e.toString());
        }
    }
}
