package com.lukk.largernumber;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends Activity
{

    private int points;
    private int left;
    private int right;
    private Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        points=0;
        TextView score = findViewById(R.id.points);
        score.setText(Integer.toString(points));

        random = new Random();

        left = (random.nextInt(10));
        right = 0;

        Button lbutton = findViewById(R.id.left_button);
        lbutton.setText(Integer.toString(left));

        Button rbutton = findViewById(R.id.right_button);
        do {
            right = random.nextInt(10);
        }
        while (left==right);
        rbutton.setText(Integer.toString(right));


    }

    public void leftClick(View view)
    {

        Button lbutton = findViewById(R.id.left_button);
        Button rbutton = findViewById(R.id.right_button);

        int left = Integer.parseInt(lbutton.getText().toString());
        int right = Integer.parseInt(rbutton.getText().toString());

        if(left>right)
        {
            points ++;
        }

        else
        {
            points --;
        }

        TextView score = findViewById(R.id.points);
        score.setText(Integer.toString(points));

        left = (random.nextInt(10));
        lbutton.setText(Integer.toString(left));

        do {
            right = random.nextInt(10);
        }
        while (left==right);
        rbutton.setText(Integer.toString(right));



    }

    public void rightClick(View view)
    {
        Button lbutton = findViewById(R.id.left_button);
        Button rbutton = findViewById(R.id.right_button);

        int left = Integer.parseInt(lbutton.getText().toString());
        int right = Integer.parseInt(rbutton.getText().toString());

        if(right>left)
        {
            points ++;
        }

        else
        {
            points --;
        }

        TextView score = findViewById(R.id.points);
        score.setText(Integer.toString(points));

        left = (random.nextInt(10));
        lbutton.setText(Integer.toString(left));

        do {
            right = random.nextInt(10);
        }
        while (left==right);
        rbutton.setText(Integer.toString(right));
    }
}
