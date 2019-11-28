package de.minispim.acquisition;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import de.minispim.R;

import static android.R.layout.simple_spinner_item;

public class AcquireSettings extends DialogFragment {

    public static String TAG = "Settings Dialog";

    public interface NoticeDialogListener {
        public void onDialogPositiveClick(DialogFragment dialog);
        public void onDialogNegativeClick(DialogFragment dialog);
    }

    // Use this instance of the interface to deliver action events
    AcquireSettings.NoticeDialogListener mListener;

    private TextView acquireSettingsSetDatasetName;
    private TextView acquireSettingsMultiModeCountTextView;
    private TextView acquireSettingsSetMultiModeDelayEditText;
    private TextView acquireSettingsAECCompensationEditText;

    // Different Imaging Modes
    String BFMode;
    String FPMMode;
    String DFMode;
    String DPCMode;
    String MMMode;



    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);


        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (AcquireSettings.NoticeDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }

    public static interface OnCompleteListener {
        public abstract void onComplete(String time);
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_acquire_settings, null);



        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(view);
        // Add action buttons
        builder.setPositiveButton("Set", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                int mmCountValue = Integer.parseInt(acquireSettingsMultiModeCountTextView.getText().toString());
                String mmDelayValue = acquireSettingsSetMultiModeDelayEditText.getText().toString();
                String aecCompensationVal = acquireSettingsAECCompensationEditText.getText().toString();
                String datasetName = acquireSettingsSetDatasetName.getText().toString();

                Log.d(TAG,String.format("nMaxSearchApertures: %s", mmCountValue));
                Log.d(TAG,String.format("mmDelay: %s", mmDelayValue));
                AcquireActivity callingActivity = (AcquireActivity) getActivity();

                //callingActivity.setMmCount(mmCountValue);

            }
        })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });

        AcquireActivity callingActivity = (AcquireActivity) getActivity();




        acquireSettingsMultiModeCountTextView = (TextView) view.findViewById(R.id.acquireSettingsMultiModeCountTextView);
        acquireSettingsMultiModeCountTextView.setInputType(InputType.TYPE_CLASS_NUMBER);
        //acquireSettingsMultiModeCountTextView.setText(String.format("%d", callingActivity.mmCount));

        acquireSettingsAECCompensationEditText = (TextView) view.findViewById(R.id.acquireSettingsAECCompensationEditText);
        acquireSettingsAECCompensationEditText.setInputType(InputType.TYPE_NUMBER_FLAG_SIGNED);
        //acquireSettingsAECCompensationEditText.setText(String.format("%d", callingActivity.aecCompensation));

        acquireSettingsSetMultiModeDelayEditText = (TextView) view.findViewById(R.id.acquireSettingsSetMultiModeDelayEditText);
        acquireSettingsSetMultiModeDelayEditText.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
        //acquireSettingsSetMultiModeDelayEditText.setText(String.format("%.2f", callingActivity.mmDelay));

        acquireSettingsSetDatasetName = (TextView) view.findViewById(R.id.acquireSettingsSetDatasetName);
        acquireSettingsSetDatasetName.setInputType(InputType.TYPE_CLASS_TEXT);
        //acquireSettingsSetDatasetName.setText(callingActivity.datasetName);

        //acquireSettingsHDRCheckbox.setChecked(callingActivity.usingHDR);


        // Select imaging method as a spinner
        Spinner dropdownAcqMethods = (Spinner) view.findViewById(R.id.acqSettingsModeSpinner);
        String[] items = new String[]{BFMode, MMMode, FPMMode, DFMode, DPCMode};
        ArrayAdapter<String>acquireSettingsSpinnerMethods = new ArrayAdapter<String>(this.getActivity(), simple_spinner_item, items);
        dropdownAcqMethods.setAdapter(acquireSettingsSpinnerMethods);

        dropdownAcqMethods.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                String spinnerVal= parent.getSelectedItem().toString();
                AcquireActivity callingActivity = (AcquireActivity) getActivity();
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });



        // Select ISO value using a spinner
        Spinner dropdownISOVal = (Spinner) view.findViewById(R.id.acqSettingsISOSpinner);
        String[] ISOitems = new String[]{"100", "200", "300", "500", "1000"};
        ArrayAdapter<String>acquireSettingsSpinnerISO = new ArrayAdapter<String>(this.getActivity(), simple_spinner_item, ISOitems);
        dropdownISOVal.setAdapter(acquireSettingsSpinnerISO);

        dropdownISOVal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                String spinnerVal= parent.getSelectedItem().toString();
                AcquireActivity callingActivity = (AcquireActivity) getActivity();

            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });



        return builder.create();
    }


}


