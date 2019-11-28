package de.minispim.acquisition;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;

import org.opencv.android.Utils;
import org.opencv.core.Mat;

import de.minispim.R;

import static org.opencv.imgcodecs.Imgcodecs.CV_LOAD_IMAGE_ANYDEPTH;
import static org.opencv.imgcodecs.Imgcodecs.imread;

/**
 * Created by Bene on 20.10.17.
 */

public class AcquireResultActivity extends Activity {

    String TAG = "AcquireResultActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //setContentView(R.layout.activity_crop);

        // extract infos from Intent-Bundle and Shared Preferences
        Bundle extraValues = getIntent().getExtras();
        String imagepath = ""; // or other values
        if(extraValues != null)
            imagepath = extraValues.getString("imagepath");




        if(imagepath!=""){
            Mat qDPCresultMat = imread(imagepath, CV_LOAD_IMAGE_ANYDEPTH);
            Bitmap qDPCresultBMP = null;
            qDPCresultBMP = Bitmap.createBitmap(qDPCresultMat.cols(), qDPCresultMat.rows(), Bitmap.Config.ARGB_8888);
            Utils.matToBitmap(qDPCresultMat, qDPCresultBMP);

            ImageView imageView = (ImageView) findViewById(R.id.imageView);
            imageView.setImageBitmap(qDPCresultBMP);

        }
    }


}
