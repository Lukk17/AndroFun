package com.lukk.a2;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.lukk.androfun.MainActivity;
import com.lukk.androfun.R;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class A2 extends Activity
{
    private String TEMP_FILE = "androidFile";
    private MediaPlayer mp;

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

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        TextView shared = findViewById(R.id.sharedA3);
        SharedPreferences sharedPreferences = getSharedPreferences("pref", MODE_PRIVATE);
        String savedWord = sharedPreferences.getString("editText", "");
        shared.setText(savedWord);

        // Check for existing Google Sign In account, if the user is already signed in
// the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        TextView title = findViewById(R.id.A2);
        title.setText(account.getEmail());
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        try
        {
            mp.stop();
        }
        catch (NullPointerException e)
        {
            System.out.println("no music was played");
        }
    }

    public void mainActivity_button(View view)
    {
        Intent mainActivity = new Intent(this, MainActivity.class);
        mainActivity.putExtra("a2_msg", "chiiicken");
        setResult(RESULT_OK, mainActivity);
        finish();

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

    public void wipeClick(View view)
    {
        wipeFile(TEMP_FILE);
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

    public void musicClick(View view)
    {
        mp = MediaPlayer.create(this,R.raw.kshmr_wildcard);
        mp.start();
    }

    public List<String> scanFile(String name) throws FileNotFoundException
    {
        Scanner scan = new Scanner(openFileInput(name));
        List<String> lines = new ArrayList<>();
        while (scan.hasNextLine())
        {
            lines.add(scan.nextLine());
        }
        scan.close();
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
            List<String> androidList = scanFile(fileName);
            Random random = new Random();
            PrintStream file;

            if(androidList.size()<=22)
            {
                 file = new PrintStream(openFileOutput(fileName, MODE_APPEND));
            }
            else
            {
                 file = new PrintStream(openFileOutput(fileName, MODE_PRIVATE));
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

        try
        {
            if (!"start".equals(androidList.get(0)))
            {
                androidString += "start\n";
            }
        }
        catch (IndexOutOfBoundsException e)
        {
            System.out.println("File was wiped");
        }

        for (String s : androidList)
        {
            androidString += s + "\n";
        }
        return androidString;
    }

    public void wipeFile(String fileName)
    {
        try
        {
            PrintStream file = new PrintStream(openFileOutput(fileName, MODE_PRIVATE));
            file.append("");
            file.close();

        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        showFile(readFile(fileName));
    }

    public void showFile (String contentToShow)
    {
        TextView androidFile = findViewById(R.id.androidFile);
        androidFile.setText(contentToShow);
    }
}
