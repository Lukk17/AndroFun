package com.lukk.largernumber;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends Activity
{
    private int points;
    private String[] STARS = {"*", "*", "*", "*", "*", "*", "*"};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        points = 0;
        scoreIt();
        randomizeNumbers();

        ListView listView = findViewById(R.id.star_list);
        ArrayAdapter<String> starAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, STARS);
        listView.setAdapter(starAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                Toast.makeText(MainActivity.this, "STAR", Toast.LENGTH_SHORT).show();
            }
        });
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

    public void Sayian(View view)
    {
        ImageView imageView = findViewById(R.id.goku_image);

        List <Integer> radioList = new ArrayList<>();
        radioList.add(R.id.SSJ);
        radioList.add(R.id.SSJ4);
        radioList.add(R.id.Smile);
        radioList.add(R.id.SSJ2);

        switch (view.getId())
        {
            case R.id.SSJ:
            {
                imageView.setImageResource(R.drawable.goku_ssj);
                uncheckRadio(radioList,view.getId());
                break;
            }
            case R.id.SSJ4:
            {
                imageView.setImageResource(R.drawable.goku_ssj4);
                uncheckRadio(radioList,view.getId());
                break;
            }
            case R.id.Smile:
            {
                imageView.setImageResource(R.drawable.goku_ssj4_side);
                uncheckRadio(radioList,view.getId());
                break;
            }
            case R.id.SSJ2:
            {
                imageView.setImageResource(R.drawable.goku_ssj2);
                uncheckRadio(radioList,view.getId());
                break;
            }
        }
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

    public void uncheckRadio(List<Integer> radiobuttons ,int id)
    {
        for(Integer i : radiobuttons)
        {
            if(i==id) continue;
            else
            {
                RadioButton radioButton = findViewById(i);
                radioButton.setChecked(false);
            }
        }
    }
}
