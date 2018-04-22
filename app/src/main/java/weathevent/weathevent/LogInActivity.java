package weathevent.weathevent;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;

import Fragment.*;
import Database.Tasks.AsyncFirebasePost;
import Database.Tasks.AsyncResponse;

public class LogInActivity extends AppCompatActivity implements AsyncResponse, AsyncFirebasePost{

    private String activeFragmentTag;
    private ConstraintLayout loginMainLayout;
    private FragmentTransaction fragmentTransaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        //references
        loginMainLayout = findViewById(R.id.login_cl_container);


        //initializeFragments
        initializeLogoFragment();
        initializeFragmentByTag(LoginFragment.TAG);
    }

    protected void initializeLogoFragment() {
        //fragmentTransaction = getSupportFragmentManager().beginTransaction();

    }

    public void initializeFragmentByTag(String fragmentTag) {
        switch (fragmentTag) {
            case LoginFragment.TAG:
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.login_cl_container, new LoginFragment(), LoginFragment.TAG);
                fragmentTransaction.commit();
                break;
            case RegisterFragment.TAG:
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.login_cl_container, new RegisterFragment(), RegisterFragment.TAG);
                fragmentTransaction.commit();
                break;


        }
    }

    public void showFragmentByTag(String fragmentTag) {
        switch (fragmentTag) {
            case LoginFragment.TAG:
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right);
                fragmentTransaction.replace(R.id.login_cl_container, new LoginFragment(), LoginFragment.TAG);
                fragmentTransaction.commit();
                break;
            case RegisterFragment.TAG:
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
                fragmentTransaction.replace(R.id.login_cl_container, new RegisterFragment(), RegisterFragment.TAG);
                fragmentTransaction.commit();
                break;
            case RegisterStatusFragment.TAG:
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right);
                fragmentTransaction.replace(R.id.login_cl_container, new RegisterStatusFragment(), RegisterStatusFragment.TAG);
                fragmentTransaction.commit();
                break;
        }

    }
    @Override
    public void processFinish(boolean loginPassed) {
        if(loginPassed){
            Intent intent = new Intent(this, WeatheventActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void processFinish(String message) {
        //TODO: Toast showing the message
        this.showFragmentByTag(RegisterStatusFragment.TAG);
    }
}
