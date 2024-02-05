package com.example.memorymastermind;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class AboutDialogFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("About Me")
                .setMessage("Name: Vageesh Ramesh\nEmail: vageesh07ramesh@gmail.com")
                .setPositiveButton("OK", (dialog, id) -> {
                    // User clicked OK button
                    dismiss();
                });

        return builder.create();
    }
}

