package com.lukk.largernumber;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends Activity
{

    private int points;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        points = 0;
        scoreIt();
        randomizeNumbers();
    }


    public void leftClick(View view)
    {
        if (isLeftNumberLarger())
        {
            points++;
            makeToast(true);
        } else
        {
            points--;
            makeToast(false);
        }

        scoreIt();
        randomizeNumbers();
    }

    public void rightClick(View view)
    {
        if (!isLeftNumberLarger())
        {
            points++;
            makeToast(true);
        } else
        {
            points--;
            makeToast(false);
        }

        scoreIt();
        randomizeNumbers();
    }

    public void randomizeNumbers()
    {
        int left = 0;
        int right = 0;
        Random random = new Random();

        left = (random.nextInt(10));
        Button lbutton = findViewById(R.id.left_button);
        lbutton.setText(Integer.toString(left));

        Button rbutton = findViewById(R.id.right_button);
        do
        {
            right = random.nextInt(10);
        }
        while (left == right);
        rbutton.setText(Integer.toString(right));
    }

    public boolean isLeftNumberLarger()
    {
        Button lbutton = findViewById(R.id.left_button);
        int left = Integer.parseInt(lbutton.getText().toString());

        Button rbutton = findViewById(R.id.right_button);
        int right = Integer.parseInt(rbutton.getText().toString());

        return left > right;
    }

    public void scoreIt()
    {
        TextView score = findViewById(R.id.points);
        score.setText("points: " + Integer.toString(points));
    }

    public void makeToast(boolean win)
    {
        if (win)
        {
            Toast.makeText(this, "Well done", Toast.LENGTH_SHORT).show();
        } else
        {
            Toast.makeText(this, "Ekhmm..", Toast.LENGTH_SHORT).show();
        }
    }
}
