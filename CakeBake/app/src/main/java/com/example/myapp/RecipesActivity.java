package com.example.myapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RecipesActivity extends AppCompatActivity {

    private static final String URL_DATA="https://jsonkeeper.com/b/TK3H";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private List<ListItem> listItems;

    public Button buttonTimer;
    public Button button_exit_app_on_recipe;
    public Button button_search_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar3;
        actionBar3 = getSupportActionBar();
        ColorDrawable colorDrawable3= new ColorDrawable(Color.parseColor("#CE88A5"));
        actionBar3.setBackgroundDrawable(colorDrawable3);

        setContentView(R.layout.activity_recipes);

        buttonTimer=(Button)findViewById(R.id.btn_timer_change);
        buttonTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_timer=new Intent(RecipesActivity.this, TimerActivity.class);
                startActivity(intent_timer);
            }
        });

        button_exit_app_on_recipe = (Button) findViewById(R.id.btn_recipe_exit);
        button_exit_app_on_recipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Intent.ACTION_MAIN);
                intent1.addCategory(Intent.CATEGORY_HOME);
                startActivity(intent1);

            }
        });

        recyclerView= (RecyclerView) findViewById(R.id.scrool_recipes);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listItems=new ArrayList<>();

        loadRecyclerViewData();

        button_search_button=(Button) findViewById(R.id.btn_search);
        button_search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loadRecyclerViewDataSearched();
            }
        });

    }

    private void loadRecyclerViewDataSearched() {
        listItems.clear();
        ProgressDialog progressDialog= new ProgressDialog(this);
        progressDialog.setMessage("Loading recepies...");
        progressDialog.show();

        EditText search_text = (EditText)findViewById(R.id.search_bar);
        String search_parameter = search_text.getText().toString();

        StringRequest stringRequest= new StringRequest(Request.Method.GET,
                URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            JSONArray array = jsonObject.getJSONArray("cakes");
                            for(int i=0; i<array.length();i++){
                                JSONObject o=array.getJSONObject(i);
                                ListItem item=new ListItem(
                                        o.getString("CakeId"),
                                        o.getString("Difficulty"),
                                        o.getString("Ingredients"),
                                        o.getString("Recipe"),
                                        o.getString("Image")
                                );
                                if(o.getString("CakeId").contains(search_parameter)){
                                    listItems.add(item);
                                }
                            }
                            adapter=new RecyclerAdapter(listItems, getApplicationContext());
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), volleyError.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    private void loadRecyclerViewData(){
        ProgressDialog progressDialog= new ProgressDialog(this);
        progressDialog.setMessage("Loading recipes...");
        progressDialog.show();

        StringRequest stringRequest= new StringRequest(Request.Method.GET,
                URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            JSONArray array = jsonObject.getJSONArray("cakes");
                            for(int i=0; i<array.length();i++){
                                JSONObject o=array.getJSONObject(i);
                                ListItem item=new ListItem(
                                        o.getString("CakeId"),
                                        o.getString("Difficulty"),
                                        o.getString("Ingredients"),
                                        o.getString("Recipe"),
                                        o.getString("Image")
                                );
                                listItems.add(item);
                            }
                            adapter=new RecyclerAdapter(listItems, getApplicationContext());
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), volleyError.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

}