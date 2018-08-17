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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class A2 extends Activity
{
    private String TEMP_FILE = "androidFile";

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
    }

    public void mainActivity_button(View view)
    {
        Intent mainActivity = new Intent(this, MainActivity.class);
        startActivity(mainActivity);
    }

    public void readClick(View view)
    {
        showFile(readFile(TEMP_FILE));
    }

    public void writeClick(View view)
    {
        List<String> lines = scanFile(R.raw.file_to_write);
        writeToFile(lines, TEMP_FILE);
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
        ArrayAdapter data = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, lines);
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

    public void writeToFile(List<String> linesToWrite, String fileName)
    {
        try
        {
            String temp = readFile(fileName);
            Random random = new Random();
            PrintStream file = new PrintStream(openFileOutput(fileName, MODE_PRIVATE));

            if(countLines(temp)<=22)
            {
                file.append(temp);
            }

            for (String s : linesToWrite)
            {
                file.append(s + "\n");
            }
            file.append("one ");
            file.append("two ");
            file.append("three ");
            file.append("four ");
            file.append("\n" + random.nextInt() + "\n\n");
            file.close();

        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    public String readFile(String fileName)
    {
        String androidString = "";
        List<String> androidList = new ArrayList<>();

        try
        {
            androidList = scanFile(fileName);

        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        if(!androidList.get(0).equals("start"))
        {
            androidString += "start\n";
        }
        for (String s : androidList)
        {


            androidString += s + "\n";
        }
        return androidString;
    }

    public int countLines(String str)
    {
        String[] lines = str.split("\n");
        return  lines.length;
    }

    public void showFile (String contentToShow)
    {
        TextView androidFile = findViewById(R.id.androidFile);
        androidFile.setText(contentToShow);
    }
}
