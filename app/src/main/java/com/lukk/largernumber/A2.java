package com.lukk.largernumber;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
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


        List<String> lines = scanFile(R.raw.simple_text);
        spiner(lines);

        writeToFile();
    }

    public void mainActivity_button(View view)
    {
        Intent mainActivity = new Intent(this, MainActivity.class);
        startActivity(mainActivity);
    }

    public List<String> scanFile(int id)
{
    Scanner scan = new Scanner(getResources().openRawResource(id));
    List<String> lines = new ArrayList<>();
    while (scan.hasNextLine())
    {
        lines.add(scan.nextLine());
    }
    return lines;
}
    public List<String> scanFile(String name) throws FileNotFoundException
    {
        Scanner scan = new Scanner(openFileInput(name));
        List<String> lines = new ArrayList<>();
        while (scan.hasNextLine())
        {
            lines.add(scan.nextLine());
        }
        return lines;
    }

    public void spiner(List<String> lines)
    {
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

    public void writeToFile()
    {
        String androidString = "start\n";
        List<String> androidList = new ArrayList<>();

        try
        {
            PrintStream file = new PrintStream(openFileOutput("file_to_write.txt", MODE_PRIVATE));
            file.print("one");
            file.print("two");
            file.print("three");
            file.print("four");
            file.close();

            androidList = scanFile("file_to_write.txt");

        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        for(String s: androidList)
        {
            androidString+= s+"\n";
        }
        TextView androidFile = findViewById(R.id.androidFile);
        androidFile.setText(androidString);
    }
}
