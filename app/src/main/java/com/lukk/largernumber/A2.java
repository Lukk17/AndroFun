package com.lukk.largernumber;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class A2 extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a2);

        Intent intent = getIntent();
        String message = intent.getExtras().get("message").toString();

        TextView textView = findViewById(R.id.A2);
        textView.setText(message);

        Scanner scan = new Scanner(getResources().openRawResource(R.raw.simple_text));
        String lines = "start \n";
        while (scan.hasNextLine())
        {
            lines = lines.concat(scan.nextLine()+ "\n");
        }

        TextView simpleText = findViewById(R.id.simpleText);
        simpleText.setText(lines);
    }

    public void mainActivity_button(View view)
    {
        Intent mainActivity = new Intent(this, MainActivity.class);
        startActivity(mainActivity);
    }
}
