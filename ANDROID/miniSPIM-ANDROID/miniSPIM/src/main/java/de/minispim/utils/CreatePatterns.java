package de.minispim.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfDouble;

import java.util.ArrayList;
import java.util.Random;

import static java.lang.Double.NaN;

/**
 * Created by Bene on 07.10.16.
 */

public class CreatePatterns {


    static String TAG = "CreatePatterns";

    static int width = 1920;//320*2;
    static int height = 1080;//240*2;


    public static Bitmap drawRectIntensities(int nElements, ArrayList<Double> valList ){
        // this creates a 2D output bitmap which shows the intensities measured with the max Int activity
        final int w = nElements;
        final int h = nElements;

        Bitmap bmp_result = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);





        int ii = 0;
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                int val = (int)Math.floor(valList.get(ii));


                // Differentiate between Highest and "normal" values
                if (val >= 100)
                    bmp_result.setPixel(x, y, Color.rgb(val, 0, 0));
                else
                    bmp_result.setPixel(x, y, Color.rgb(val, val, val));
                Log.i(TAG, String.valueOf(ii) + ", " + String.valueOf(val));
                ii++;
            }
        }
        Bitmap bmpScaled = Bitmap.createScaledBitmap(bmp_result, nElements, nElements, false);
        return bmpScaled;
    }





    public static Bitmap getSegments(Mat data, int cx, int cy){

        int data_cols = data.cols();
        float[] data_float = new float[(int) (data_cols)];

        Log.i(TAG, String.valueOf(data));
        for(int col=0;col<data_cols;col++) {
            data_float[col] = (float)data.get(0, col)[0];
            Log.i("TF output conversion", String.valueOf(data_float[col]));

        }

        return getSegments(data_float, cx, cy, 100);
    }

    public static Bitmap getSegments(float[] data, int cx, int cy, int NA){
        // creates a Bitmap with 4 rings 12 segments each
        // data-vector has to be a 4*12=48 element vector with values 0..1
        // it represents the intensities of each segment

        int radius = NA/2;
        int num_inner_circles = 4;
        int num_inner_segments = 12;

        int[] Radius = {2*radius, (int)(1.5*radius), radius, (int)(.5* radius)};
        float start_rho=0;
        float delta_rho= (float) (360./num_inner_segments);

        // normalize parameters
        float minval = 1e5f;
        float maxval = -1e5f;

        // find minimum
        for(int i=0; i<data.length; i++){if (data[i]<minval) minval = data[i];}
        // subtract minimum
        for(int i=0; i<data.length; i++) data[i] = (data[i]-minval);
        // find maximum
        for(int i=0; i<data.length; i++) if (data[i]>maxval) maxval = data[i];
        Log.i(TAG, String.valueOf(minval)+"_"+String.valueOf(maxval));
        // subtract maximum
        for(int i=0; i<data.length; i++) data[i] = (data[i]/maxval);
        // find maximum



        // Initialize a new Bitmap object
        Bitmap bitmap = Bitmap.createBitmap(
                width, // Width
                height, // Height
                Bitmap.Config.ARGB_8888 // Config
        );

        // Initialize a new Canvas instance
        Canvas canvas = new Canvas(bitmap);

        canvas.drawColor(Color.BLACK);
        Paint pSegment = new Paint();
        pSegment.setAntiAlias(true);
        pSegment.setColor(Color.BLACK);
        pSegment.setStyle(Paint.Style.STROKE);
        pSegment.setStrokeWidth(0);
        pSegment.setStyle(Paint.Style.FILL);
        pSegment.setColor(Color.BLACK);

        int index = 0;

        for(int j = 1; j<num_inner_circles+1; j++){

            int radius_i = Radius[j-1];

            RectF rectF = new RectF(width/2-radius_i + cx,
                    height/2-radius_i + cx,
                    width/2+radius_i + cy,
                    height/2+radius_i + cy) ;

            for(int i=0;i<num_inner_segments;i++){

                double data_i = data[index];
                if (data_i == NaN) {
                    data_i = 0;
                    Log.i(TAG, "NaN reset");
                }

                // data is in the range of 0..1
                int input_data = (int)(data[index]*255);
                int colorRGB = Color.rgb(input_data, input_data, input_data);

                pSegment.setColor(colorRGB);
                pSegment.setStyle(Paint.Style.FILL);

                canvas.drawArc(rectF,start_rho,delta_rho,true,pSegment);
                start_rho=start_rho+delta_rho;

                index ++;
            }

        }

        return bitmap;
    }


    public static Bitmap getDarkfield(int cx, int cy, int NA_i, int NA_o){
        // creates a Bitmap with 1 ring

        int radius_inner = NA_i;
        int radius_outer = NA_o;


        // Initialize a new Bitmap object
        Bitmap bitmap = Bitmap.createBitmap(
                width, // Width
                height, // Height
                Bitmap.Config.ARGB_8888 // Config
        );

        // Initialize a new Canvas instance
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(Color.BLACK);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);

        // Finally, draw the circle on the canvas
        canvas.drawCircle(width/2 + cx, // cx
                height/2 + cy, // cy
                radius_outer, // Radius
                paint // Paint
        );


        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);

        // Finally, draw the circle on the canvas
        canvas.drawCircle(width/2 + cx, // cx
                height/2 + cy, // cy
                radius_inner, // Radius
                paint // Paint
        );



        return bitmap;
    }


    public static Bitmap getDPC(int cx, int cy, int rotAngle, int radius){
        // creates a Bitmap with a half circle rotated by an angle

        // Initialize a new Bitmap object
        Bitmap bitmap = Bitmap.createBitmap(
                width, // Width
                height, // Height
                Bitmap.Config.ARGB_8888 // Config
        );

        // Initialize a new Canvas instance
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(Color.BLACK);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);

        // define space to draw on
        RectF rectF = new RectF(width/2-radius + cx,
                height/2-radius + cy,
                width/2+radius + cx,
                height/2+radius + cy);

        // Finally, draw the circle on the canvas
        canvas.drawArc(rectF,rotAngle,180,true,paint);

        return bitmap;
    }


    public static Bitmap getLightsheet(int posz, int thickness){
        // Initialize a new Bitmap object
        Bitmap bitmap = Bitmap.createBitmap(
                width, // Width
                height, // Height
                Bitmap.Config.ARGB_8888 // Config
        );

        // Initialize a new Canvas instance
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(Color.BLACK);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);

        // Finally, draw the circle on the canvas
        canvas.drawRect(posz, 0, thickness+posz, height, paint);

        return bitmap;
    }


    public static Bitmap getCircle(int cx, int cy, int radius){
        // creates a Bitmap with a brightfield aperture
        // cx; cy: centre coordinates in x/y direction
        // radius: pixelradius of aperture


        // Initialize a new Bitmap object
        Bitmap bitmap = Bitmap.createBitmap(
                width, // Width
                height, // Height
                Bitmap.Config.ARGB_8888 // Config
        );

        // Initialize a new Canvas instance
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(Color.BLACK);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);

        // Finally, draw the circle on the canvas
        canvas.drawCircle(width/2 + cx, // cx
                height/2 + cy, // cy
                radius, // Radius
                paint // Paint
        );
        return bitmap;
    }

    public static Bitmap getCircle(int cx, int cy, int radius, double intensity){
        // creates a Bitmap with a brightfield aperture
        // cx; cy: centre coordinates in x/y direction
        // radius: pixelradius of aperture


        // Initialize a new Bitmap object
        Bitmap bitmap = Bitmap.createBitmap(
                width, // Width
                height, // Height
                Bitmap.Config.ARGB_8888 // Config
        );

        // Initialize a new Canvas instance
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(Color.BLACK);
        Paint paint = new Paint();
        paint.setAntiAlias(true);

        // intensity is in the range of 0..1
        int input_data = (int)(intensity*255);
        int colorRGB = Color.rgb(input_data, input_data, input_data);

        paint.setColor(colorRGB);
        paint.setStyle(Paint.Style.FILL);

        // Finally, draw the circle on the canvas
        canvas.drawCircle(width/2 + cx, // cx
                height/2+ cy, // cy
                radius, // Radius
                paint // Paint
        );
        return bitmap;
    }

    public static float[] generateRanNumVect(int nNumbers){

        Random rander = new Random();
        float[] random_number_array = new float[nNumbers];
        for (int i=0; i< nNumbers; i++) {
            random_number_array[i] = (float)rander.nextDouble();
            //Log.i(TAG, String.valueOf(random_number_array[i]));
        }
        return random_number_array;
    }

    public static double[] mat2vec(Mat inputMat){
        // convert input Mat object into double vector
        // The input Mat hast size of nx1

        int lengthMat = inputMat.width();
        double[] outvec = new double[lengthMat];

        for(int i=0; i<lengthMat; i++){
            outvec[i] = (double)inputMat.get(0, i)[0];
            Log.i(TAG, String.valueOf(i)+", Vectorval: "+ String.format("%.2f", outvec[i]));
        }
        return outvec;
    }


    }


