package com.lukk.a3;

import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.support.v4.app.Fragment;

import com.lukk.androfun.R;

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

}
