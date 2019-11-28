package de.minispim.datasets;

import android.util.Log;

import java.io.File;
import java.io.FileFilter;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Bene on 18.04.2015.
 */

public class Dataset implements Serializable {

    // Constants for current image dataset
        private String TAG = "Dataset";

        private static final long serialVersionUID = 1L;

        public static final String UNITS = "mm";
        public static final int SIZE = 7;
        public static final int LED_DISTANCE = 4;
        public static final int F_CONDENSER = 60; //mm
        public static final double F1 = 16.5;
        public static final double F2 = 25.4 * 4;
        public static final double M = F2/F1;
        public static final double DX0 = 0.0053;
        public static final double DX = DX0/M; //spatial resolution

        public static int apertureCount = 0;
        public static int avgNoiseCount = 0;

        public static final float MAX_DEPTH = 0.1f;
        public static final float DEPTH_INC = 0.01f;

        public static String DATASET_PATH = "";
        public static String DATASET_NAME = "";
        public static String DATASET_TYPE = ""; // Can be multimode, brightfield_scan, full_scan
        public static String DATASET_HEADER = "";

        public boolean USE_COLOR_DPC = false;

        public float ZMIN = -100;
        public float ZINC = 10;
        public float ZMAX = 100;

        public int XCROP = 507;
        public int YCROP = 96;

        public int WIDTH  = 3264;   // 3264
        public int HEIGHT = 2448;  //2448

        public File[] fileList = null;
        public int fileCount = 0;

        public  String getRawImagePath(int idx, int deleteMe) {
            return String.format("%s%s%d.jpeg", DATASET_PATH, DATASET_HEADER,idx);
            //return DATASET_PATH + DATASET_NAME + "/image" + String.format("%d%d", row, col) + ".bmp";
        }

        public  String getResultImagePath(String name, float depth) {
            return DATASET_PATH + DATASET_NAME + "/" + name + String.format("%d", (int)(depth*100)) + ".png";
        }

        public  String getResultImagePath(String name) {
            return DATASET_PATH + DATASET_NAME + "/" + name + ".png";
        }

        public void buildFileListFromPath(String path) {

            Log.d(TAG,String.format("FilePath is: %s", path));
            // Extract directory:
            DATASET_PATH = path;
            Log.d(TAG,String.format("Path is: %s", DATASET_PATH));
            File fileList2 = new File(DATASET_PATH);

            fileList = fileList2.listFiles(new FileFilter() {
                @Override
                public boolean accept(File pathname) {
                    String name = pathname.getName().toLowerCase();
                    return name.endsWith(".jpeg") && pathname.isFile();
                }
            });
        /*
        for (int i = 0; i<mDataset.fileList.length; i++)
        {
      	  Log.d(TAG,mDataset.fileList[i].toString());
        }
        */

            fileCount = fileList.length;
            String firstFileName = fileList[0].getAbsoluteFile().toString();
            // Define the dataset type
            if (firstFileName.contains("Brightfield_Scan"))
            {
                DATASET_TYPE = "brightfield";
                DATASET_HEADER = firstFileName.substring(path.lastIndexOf(File.separator)+1,firstFileName.lastIndexOf("_scanning_"))+"_scanning_";
                Log.d(TAG,String.format("BF Scan Header is: %s", DATASET_HEADER));
            }

            else if (firstFileName.contains("multimode"))
            {
                DATASET_TYPE = "multimode";
                DATASET_HEADER = "milti_";
                Log.d(TAG,String.format("Header is: %s", DATASET_HEADER));
            }

            else if (firstFileName.contains("Full_Scan"))
            {
                DATASET_TYPE = "full_scan";
                DATASET_HEADER = firstFileName.substring(firstFileName.lastIndexOf(File.separator)+1,firstFileName.lastIndexOf("_scanning_"));
                Log.d(TAG,String.format("Full Scan Header is: %s", DATASET_HEADER));
            }

            // Name the Dataset after the directory
            DATASET_NAME = DATASET_PATH.substring(0, DATASET_PATH.lastIndexOf(File.separator));
        }


    public static String DATA_PATH_HOLOGRAM;
    public static String DATA_PATH_PHASE;
    public static List<File> SUPERRESOLUTION_FILE_LIST;



    //TODO REMOVE all PATH Methods:


}



