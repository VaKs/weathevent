package Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import Database.StorageManager;
import Database.StorageManagerImplFirebaseRoom;
import Database.Tasks.AsyncUserLogInTask;
import weathevent.weathevent.LogInActivity;
import weathevent.weathevent.R;
import weathevent.weathevent.WeatheventActivity;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Rafal on 2018-03-25.
 */

public class LoginFragment extends Fragment implements FragmentsInterface {

    public static final String TAG = "LoginFragment";
    private Context context;
    private Button btn_login;
    private TextView link_signup;
    private EditText inputedEmail;
    private EditText inputedPassword;
    private SharedPreferences shared;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_log_in, container, false);
        context = getActivity().getApplicationContext();
        return view;
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {

        //initialize variables
        btn_login = view.findViewById(R.id.btn_login);
        link_signup = view.findViewById(R.id.link_signup);
        inputedEmail = getView().findViewById(R.id.input_email);
        inputedPassword = getView().findViewById(R.id.input_password);

        shared = context.getSharedPreferences("weatheventSharedPreferences", MODE_PRIVATE);
        SharedPreferences.Editor editor;
        Boolean logged = shared.getBoolean("logged",false);


        //listeners
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateLogin();
            }
        });

        link_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((LogInActivity) getActivity()).showFragmentByTag(RegisterFragment.TAG);
            }
        });

        if(logged){
            Intent intent = new Intent(context, WeatheventActivity.class);
            startActivity(intent);
        }

    }

    private void validateLogin() {

<<<<<<< HEAD
        Intent intent = new Intent(context, WeatheventActivity.class);
        startActivity(intent);
        /*
            String email = inputedEmail.getText().toString();
            String password = inputedPassword.getText().toString();
=======

            //TODO: to Enable the Login delete this lines and uncomment the lines of below
            //String email = "testPref";
            //String password = "pass";

            String email = inputedEmail.getText().toString();
            String password = inputedPassword.getText().toString();

            new AsyncUserLogInTask(((LogInActivity) getActivity())).execute(new String[]{email, password});


>>>>>>> 4f30e2f1bcc5ecd78763b8b73772e0b2afdac5a8

            new AsyncUserLogInTask(((LogInActivity) getActivity())).execute(new String[]{email, password});
         */
    }

}
