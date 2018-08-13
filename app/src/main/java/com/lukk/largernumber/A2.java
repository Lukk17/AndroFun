package com.lukk.largernumber;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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
    }

    public void mainActivity_button(View view)
    {
        Intent mainActivity = new Intent(this, MainActivity.class);
        startActivity(mainActivity);
    }
}
