package com.lukk.api;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.lukk.androfun.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ApiActivity extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api);
    }

    public void ChuckNorrisButton(View view)
    {
        Ion.with(this).load("http://api.icndb.com/jokes/random")
                                .asString()
                                .setCallback(new FutureCallback<String>()
                                {
                                    @Override
                                    public void onCompleted(Exception e, String result)
                                    {
                                        /*
                                          { "type": "success",
                                            "value": { "id": , "joke":}
                                          }
                                         */
                                        try
                                        {
                                            JSONObject jsonObject = new JSONObject(result);
                                            JSONObject value = jsonObject.getJSONObject("value");
                                            String joke = value.getString("joke");

                                            TextView textView = findViewById(R.id.ChuckNorrisText);
                                            textView.setText(joke);

                                        } catch (JSONException e1)
                                        {
                                            e1.printStackTrace();
                                        }
                                    }
                                });
    }

    public void catPict(View view)
    {
        Ion.with(this).load("https://api.thecatapi.com/v1/images/search")
                .asString()
                .setCallback(new FutureCallback<String>()
                {
                    @Override
                    public void onCompleted(Exception e, String result)
                    {
                                        /*
                                            [
                                            {
                                            "id":"7uk",
                                            "url":"https://24.media.tumblr.com/tumblr_lhb6leMh001qcn249o1_400.gif",
                                            "breeds":[],
                                            "categories":[]
                                            }
                                            ]
                                         */
                        try
                        {
                            JSONArray jsonArray = new JSONArray(result);
                            JSONObject jUrl = jsonArray.getJSONObject(jsonArray.length()-1);
                            String url = jUrl.getString("url");
                            Log.i("url", url);

                            ImageView  imageView = findViewById(R.id.catPicture);

                            Picasso.with(ApiActivity.this).load(url).into(imageView);


                        } catch (JSONException e1)
                        {
                            e1.printStackTrace();
                        }
                    }
                });

    }
}
