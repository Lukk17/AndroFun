package com.lukk.a3;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lukk.androfun.R;

public class A3 extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a3);
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
    }
}
