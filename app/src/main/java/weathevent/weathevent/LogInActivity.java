package weathevent.weathevent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Fragment;

public class LogInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        //getSupportActionBar().hide();
        Intent intent = new Intent(LogInActivity.this, WeatheventActivity.class);
        startActivity(intent);
        finish();
    }
}
