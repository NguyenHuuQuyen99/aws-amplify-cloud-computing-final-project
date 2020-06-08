package com.example.myexampleproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobile.client.SignInUIOptions;

public class MainActivity extends AppCompatActivity {

    Button btnLogout;
    private final String TAG = AuthenticationActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogout = findViewById(R.id.btnLogout);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try{
                    AWSMobileClient.getInstance().signOut();
                    showSignIn();
                } catch (Error e){
                    Toast.makeText(MainActivity.this, "Error signing out", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void showSignIn(){
        try {
            AWSMobileClient.getInstance().showSignIn(this,
                    SignInUIOptions.builder().nextActivity(MainActivity.class).build());
        }
        catch (Exception e){
            Log.e(TAG, e.toString());
        }
    }
}
