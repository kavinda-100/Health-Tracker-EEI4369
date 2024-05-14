package com.s22010170.heathtrakerapp;

import android.content.Context;
import android.os.Build;

import androidx.appcompat.app.AlertDialog;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ShowMessage {
    public void show(String title, String message, Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    // show confirm dialog that return turn if the user click on the positive button else return false

}
