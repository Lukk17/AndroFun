package com.lukk.a3;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lukk.androfun.R;
import com.lukk.service.FunService;

import java.util.Random;

public class A3 extends FragmentActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a3);

        LinearLayout layout = findViewById(R.id.grid);

        Random  random = new Random();
        int max = random.nextInt(5)+2;

        for(int i=1; i <= max ; i++)
        {
            View additional = getLayoutInflater().inflate(R.layout.additional, null);
            additional.setPadding(100, 100, 100, 100);
            TextView textView1 = additional.findViewById(R.id.text1);
            textView1.setText("t" + i);
            layout.addView(additional);
        }

        try
        {
            Thread.sleep(10000);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        noti();


    }

    public void saveWord(View view)
    {
        TextView showGiven = findViewById(R.id.showGivenText);
        EditText editText = findViewById(R.id.editText);
        showGiven.setText(editText.getText());

        // share saved word to others activity as well (used in A2)
        SharedPreferences sharedPreferences = getSharedPreferences("pref", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("editText",editText.getText().toString());
        editor.apply();

        AlertDialog.Builder builder =new AlertDialog.Builder(this);
        builder.setTitle("ok");
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePicker();
        FragmentManager fg = this.getSupportFragmentManager();
        newFragment.show(fg, "datePicker");
    }

    public void serviceButton(View view)
    {
        Intent service = new Intent(this, FunService.class);
        service.putExtra("intent",A3.this.toString());
        startService(service);

    }


    public void noti(){
        createNotificationChannel();
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, "notiChanel")
                .setSmallIcon(R.drawable.goku_ssj)
                .setContentTitle("titleeeee")
                .setContentText("coooooooooooooooontent")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        Notification notification = mBuilder.build();

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(1992, notification);
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "notiChanel";
            String description = "channel for notifications";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("notiChanel", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
