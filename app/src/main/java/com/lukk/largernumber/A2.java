package com.lukk.largernumber;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
        List<String> lines = new ArrayList<>();
        while (scan.hasNextLine())
        {
            lines.add(scan.nextLine());
        }

        final Spinner spinner = findViewById(R.id.simpleText);
        ArrayAdapter data = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item, lines);
        spinner.setAdapter(data);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                Toast.makeText(A2.this, spinner.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {

            }
        });
    }

    public void mainActivity_button(View view)
    {
        Intent mainActivity = new Intent(this, MainActivity.class);
        startActivity(mainActivity);
    }
}
