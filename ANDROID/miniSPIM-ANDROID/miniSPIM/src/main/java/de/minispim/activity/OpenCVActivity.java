package de.minispim.activity;

import android.app.Activity;
import android.util.Log;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;

public class OpenCVActivity extends Activity {
    private static final String TAG = "OpenCVActivity";

    private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            switch (status) {
                case LoaderCallbackInterface.SUCCESS: {
                    Log.i(TAG, "OpenCV loaded successfully");

                    // Load native libraries after(!) OpenCV initialization
                    postOpenCVLoad();

                } break;
                default: {
                    super.onManagerConnected(status);
                } break;
            }
        }
    };

    // override this to use opencv dependent libraries
    public void postOpenCVLoad() {
        //Toast.makeText(this, "OpenCV initialized successfully", Toast.LENGTH_SHORT).show();
        return;
    }

    @Override
    public void onResume() {
        super.onResume();
        OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_2_4_8, this, mLoaderCallback);
    }
}
