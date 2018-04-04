package Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import weathevent.weathevent.LogInActivity;
import weathevent.weathevent.R;
import weathevent.weathevent.WeatheventActivity;

/**
 * Created by Rafal on 2018-03-25.
 */

public class LoginFragment extends Fragment implements FragmentsInterface {

    public static final String TAG = "LoginFragment";
    private Context context;
    private Button btn_login;
    private TextView link_signup;

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

    }

    private void validateLogin() {
        Intent intent = new Intent(context, WeatheventActivity.class);
        startActivity(intent);
    }

}
