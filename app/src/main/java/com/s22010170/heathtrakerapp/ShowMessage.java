package com.s22010170.heathtrakerapp;

import android.content.Context;

import androidx.appcompat.app.AlertDialog;

public class ShowMessage {
    public void show(String title, String message, Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
