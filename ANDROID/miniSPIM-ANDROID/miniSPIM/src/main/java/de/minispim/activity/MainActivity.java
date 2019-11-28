package de.minispim.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import de.minispim.R;
import de.minispim.acquisition.AcquireActivity;


public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";


    private Button btnStartActivity1;
    private Button btnStartActivity2;
    private Button btnStartActivity3;
    private Button btnAcqActivity;
    private Button btnGammaCalibration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // btnAcqActivity = (Button) findViewById(R.id.btnAcqActivity);
        //btnStartActivity1 = (Button) findViewById(R.id.btnStartActivity1);
        //btnStartActivity2 = (Button) findViewById(R.id.btnAcquireActivity3);

        btnAcqActivity = (Button) findViewById(R.id.btnAcqActivity);
        btnGammaCalibration = (Button) findViewById(R.id.btnGammaCalibration);


        btnAcqActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AcquireActivity.class);
                startActivity(intent);
            }
        });



        /*
        btnStartActivity1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AcquireActivity2.class);
                startActivity(intent);
            }
        });

        btnStartActivity2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CaptureImageActivity.class);
                startActivity(intent);
            }
        });

        btnStartActivity3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        */

    }

    //ensure app is in portrait orientation
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }



}
