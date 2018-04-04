package weathevent.weathevent;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import Database.AppDatabase;
import Database.PreferenceDAO;
import Database.UserDAO;
import POJO.Preference;
import POJO.User;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class LocalDatabaseTest {
    static Context appContext = InstrumentationRegistry.getTargetContext();
    static UserDAO userDAO = AppDatabase.getInstance(appContext).userDAO();
    static PreferenceDAO preferenceDAO = AppDatabase.getInstance(appContext).preferenceDAO();
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

        preference=new Preference(user.getEmail(),1000);

        userList= new ArrayList<>();
        userList.add(user);
        userList.add(user1);
        userList.add(user2);
    }
    @AfterClass
    public static void LimpiarDb(){
        userDAO.clearUsers();
        preferenceDAO.clearPreferences();
    }

    @Test
    public void dbAddUser() throws Exception {
        try {
            userDAO.addUser(user);
            userDAO.addUser(user1);
            userDAO.addUser(user2);

            List<User> userList = userDAO.getUsers();

            assertEquals(user.getName(),userList.get(0).getName());
            assertEquals(user.getEmail(),userList.get(0).getEmail());
            assertEquals(user.getSurname(),userList.get(0).getSurname());
            assertEquals(user.getPassword(),userList.get(0).getPassword());

            assertEquals(user1.getName(),userList.get(1).getName());
            assertEquals(user1.getEmail(),userList.get(1).getEmail());
            assertEquals(user1.getSurname(),userList.get(1).getSurname());
            assertEquals(user1.getPassword(),userList.get(1).getPassword());

            assertEquals(user2.getName(),userList.get(2).getName());
            assertEquals(user2.getEmail(),userList.get(2).getEmail());
            assertEquals(user2.getSurname(),userList.get(2).getSurname());
            assertEquals(user2.getPassword(),userList.get(2).getPassword());

        } catch (Exception e){
            System.out.print(e.toString());
        }
    }

    @Test
    public void dbGetUsers() throws Exception {
        try {
            List<User> userListDb = userDAO.getUsers();
            for(int i=0;i<=userListDb.size();i++){
                assertEquals(userListDb.get(i).getName(),userList.get(i).getName());
                assertEquals(userListDb.get(i).getEmail(),userList.get(i).getEmail());
                assertEquals(userListDb.get(i).getSurname(),userList.get(i).getSurname());
                assertEquals(userListDb.get(i).getPassword(),userList.get(i).getPassword());
            }

        } catch (Exception e) {
            System.out.print(e.toString());
        }
    }
    @Test
    public void dbFriendsList(){
        User firtUser = userDAO.getUserByEmail("email");

        assertEquals(user.getName(),firtUser.getName());
        assertEquals(user.getEmail(),firtUser.getEmail());
        assertEquals(user.getSurname(),firtUser.getSurname());
        assertEquals(user.getPassword(),firtUser.getPassword());


        for(int i=0;i<firtUser.getFriendsList().size();i++){
            assertTrue(user1.getEmail().equals(firtUser.getFriendsList().get(i).getEmail()) || user2.getEmail().equals(firtUser.getFriendsList().get(i).getEmail()));
        }
    }

    @Test
    public void dbAddAndGetPreference() throws Exception {
        try {
            preferenceDAO.addPreference(preference);

            Preference prefFromDb = preferenceDAO.getPreferenceByUserEmail(user.getEmail());

            assertEquals(preference.getUserEmail(),prefFromDb.getUserEmail());
            assertEquals(preference.getDistance(),prefFromDb.getDistance());
            assertEquals(preference.getId(),prefFromDb.getId());


        } catch (Exception e){
            System.out.print(e.toString());
        }
    }

    @Test
    public void dbUpdatePreference() throws Exception {
        try {

            preference.setDistance(3000);
            preferenceDAO.updatePreference(preference);

            Preference prefFromDb = preferenceDAO.getPreferenceByUserEmail(user.getEmail());



            assertEquals(preference.getUserEmail(),prefFromDb.getUserEmail());
            assertEquals(preference.getDistance(),prefFromDb.getDistance());
            assertEquals(preference.getId(),prefFromDb.getId());


        } catch (Exception e){
            System.out.print(e.toString());
        }
    }

    @Test
    public void dbClearUsers() throws Exception {
        try {
            userDAO.clearUsers();
            List<User> userListDb = userDAO.getUsers();
            assertEquals(userListDb.isEmpty(),true);


        } catch (Exception e) {
            System.out.print(e.toString());
        }
    }
}

