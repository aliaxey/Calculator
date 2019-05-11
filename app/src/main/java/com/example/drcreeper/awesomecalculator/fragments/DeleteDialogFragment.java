package com.example.drcreeper.awesomecalculator.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import com.example.drcreeper.awesomecalculator.R;

public class DeleteDialogFragment extends DialogFragment {

    DialogInterface.OnClickListener onConfirm = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            dialog.cancel();
        }
    };

    public DialogInterface.OnClickListener getOnConfirm() {
        return onConfirm;
    }

    public void setOnConfirm(DialogInterface.OnClickListener onConfirm) {
        this.onConfirm = onConfirm;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setTitle(R.string.confirm_dialog);
        dialog.setMessage(R.string.text_dialog);
        dialog.setPositiveButton(R.string.yes_button, onConfirm);
        dialog.setNegativeButton(R.string.no_button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        return dialog.create();
    }
}
