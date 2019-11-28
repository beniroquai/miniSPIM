package de.minispim.tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

public abstract class ImageProgressTask extends AsyncTask<Float, Integer, Void> {
    protected Context context;
    protected ProgressDialog progressDialog;

    public ImageProgressTask(Context context) {
        this.context = context;
        this.progressDialog = new ProgressDialog(context);
        this.progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
    }

    @Override
    protected void onProgressUpdate(Integer... progress){
        if (progress.length > 1) {
            if (progress[0] >= 0)
                progressDialog.setProgress(progress[0]);
            if (progress[1] >= 0)
                progressDialog.setSecondaryProgress(progress[1]);
        }
    }

    @Override
    protected void onPreExecute() {
        progressDialog.show();
    }

    @Override
    protected void onPostExecute(Void result) {
        if (progressDialog.isShowing())
            progressDialog.dismiss();
    }
}