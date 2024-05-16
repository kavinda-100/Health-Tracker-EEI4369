package com.s22010170.heathtrakerapp;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;

import androidx.appcompat.app.AlertDialog;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ShowMessage {

    private boolean isConfirmed;
    public void show(String title, String message, Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    // show confirm dialog that return turn if the user click on the positive button else return false
//    public boolean showConfirmDialog(String title, String message, Context context) {
//
//        new AlertDialog.Builder(context)
//                .setTitle(title)
//                .setMessage(message)
//                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
//
//                    public void onClick(DialogInterface dialog, int whichButton) {
//                        // Handle the positive button action (e.g., perform the action)
//                        isConfirmed = true;
//                    }
//                })
//                .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int whichButton) {
//                        // Handle the negative button action
//                        isConfirmed = false;
//                    }
//                })
//                .show();
//        return isConfirmed;
//    }
}
