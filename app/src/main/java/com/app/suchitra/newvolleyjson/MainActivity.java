package com.app.suchitra.newvolleyjson;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String JSON_URL = "http://192.168.0.19/json.php";

    private Button buttonGet;

    private ListView listView;

    @Override
       protected void onCreate(Bundle savedInstanceState)//start an activity
    {
        super.onCreate(savedInstanceState);//state of an activity
        setContentView(R.layout.activity_main);//connection view of an xmlfile dtata

        buttonGet = (Button) findViewById(R.id.buttonGet);//finding  by id from R.javafile
        buttonGet.setOnClickListener(this);// activity button action
        listView = (ListView) findViewById(R.id.listView);
    }

    private void sendRequest()//sending request
    {

        StringRequest stringRequest = new StringRequest(JSON_URL,
                new Response.Listener<String>() {
                    @Override//send to url
                    public void onResponse(String response) {
                        showJSON(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);//sending the request to
        requestQueue.add(stringRequest);
    }

    private void showJSON(String json) {
        ParseJSON pj = new ParseJSON(json);
        pj.parseJSON();
        CustomList cl = new CustomList(this, ParseJSON.ids, ParseJSON.names, ParseJSON.emails);
        listView.setAdapter(cl);
    }

    @Override
    public void onClick(View v) {
        sendRequest();//s1
    }
}