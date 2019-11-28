package de.minispim.utils;

import android.graphics.Bitmap;

import org.opencv.android.Utils;
import org.opencv.core.Mat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import static org.opencv.core.Core.NORM_MINMAX;
import static org.opencv.core.Core.normalize;
import static org.opencv.imgcodecs.Imgcodecs.imwrite;

/**
 * Created by Bene on 16.06.2015.
 */
public class FileUtils {

    public static void copy(File src, File dst) throws IOException {

        InputStream inStream = null;
        OutputStream outStream = null;

        try{

            inStream = new FileInputStream(src);
            outStream = new FileOutputStream(dst);

            byte[] buffer = new byte[1024];

            int length;
            //copy the file content in bytes
            while ((length = inStream.read(buffer)) > 0){

                outStream.write(buffer, 0, length);

            }

            inStream.close();
            outStream.close();


        }catch(IOException e){
            e.printStackTrace();
        }
    }




    public static List<File> getListFiles(File parentDir) {
        ArrayList<File> inFiles = new ArrayList<File>();
        File[] files = parentDir.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                inFiles.addAll(getListFiles(file));
            } else {
                inFiles.add(file);
            }
        }
        return inFiles;
    }


    public static void imwriteNorm(Bitmap mBitmap, String filename){
        // Save image from light-source
        Mat tmp = new Mat ();
        Utils.bitmapToMat(mBitmap, tmp);
        normalize(tmp, tmp, 0, 255, NORM_MINMAX);
        imwrite(filename, tmp);

    }

    public static void imwriteNorm(Mat mMat, String filename){
        // save image
        Mat MatQDPC_norm = new Mat();
        normalize(mMat, MatQDPC_norm, 0, 255, NORM_MINMAX);
        imwrite(filename, MatQDPC_norm);

    }





}
