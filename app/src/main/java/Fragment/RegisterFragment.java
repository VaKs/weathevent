package Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import weathevent.weathevent.LogInActivity;
import weathevent.weathevent.R;

/**
 * Created by Rafal on 2018-03-25.
 */

public class RegisterFragment extends Fragment implements FragmentsInterface {

    public static final String TAG = "RegisterFragment";
    private TextView link_login;
    private Button btn_signup;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //references
        btn_signup = view.findViewById(R.id.btn_signup);
        link_login = view.findViewById(R.id.link_login);


        //listeners
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateSignup();
            }
        });


        link_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((LogInActivity) getActivity()).showFragmentByTag(LoginFragment.TAG);
            }
        });

    }

    private void validateSignup() {
        // validate and
        ((LogInActivity) getActivity()).showFragmentByTag(RegisterStatusFragment.TAG);
    }
}
