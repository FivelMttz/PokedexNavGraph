package com.example.pokedexnavgraph;

import android.app.Application;
import android.os.Bundle;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.pokedexnavgraph.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    pokListViewModel viewModel;



    //Pokemon API Elements
    // POkemon Array
    String pokemonList="";
    String pokemonListString;
    List<String> items = new ArrayList<>();
    JSONArray pokemonArrayList ;

    JSONObject selectedPokemon;

    String selectedPokemonString="";
    JSONArray pokemonArraySelectedPokemonSprites ;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());


        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);


         viewModel = new ViewModelProvider(this).get(pokListViewModel.class);
       // viewModel = ViewModelProviders.of(this).get(pokListViewModel.class);
        Log.e("Score Team A", "" + viewModel.getScoreTeamA()  );
        viewModel.setScoreTeamA(5000);

        Log.e("Score Team A", "" + viewModel.getScoreTeamA()  );

        httpCall ("pokemonList", "");








        //  NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
       // appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
      //  NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);


        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();



                //  navController.navigate(R.id.action_FirstFragment_to_SecondFragment);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void addOneForTeamA(View v) {
        viewModel.scoreTeamA =  viewModel.scoreTeamA + 1;
        int scoreTeamA = viewModel.scoreTeamA;
    }

    public void httpCall (String callStyle, String selectedPokemon)
    {
        Log.e("Callstyle",callStyle);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String URL = "";

        if(callStyle=="pokemonList")
        {
             URL = "https://pokeapi.co/api/v2/pokemon";

        }
        else if (callStyle=="pokemonInfo" && selectedPokemon!=null )
        {
            URL = "https://pokeapi.co/api/v2/pokemon/" + selectedPokemon;

        }
        JSONObject jsonBody = new JSONObject();
        final String requestBody = jsonBody.toString();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("VOLLEY", response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("VOLLEY error", error.toString());
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/json";
            }

            @Override
            public byte[] getBody() throws AuthFailureError {

                Log.e("VOLLEY getBody", "");

                try {



                    Log.e("VOLLEY Body", requestBody);
                    return requestBody == null ? null : requestBody.getBytes("utf-8");


                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                    Log.e("VOLLEY Body Error", String.valueOf(uee));





                    return null;
                }
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                String responseString = "";
                if (response != null) {
                    responseString = String.valueOf(response.statusCode);
                    // can get more details such as response.headers

                    try {
                        String json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
                        Log.e("VOLLEY Network", String.valueOf(json));


                        if(callStyle=="pokemonList")
                        {
                            pokemonList=json;
                            buildJson();

                        }
                        else if (callStyle=="pokemonInfo" && selectedPokemon!=null )
                        {


                           selectedPokemonString=json;
                            buildPokemonJson();









                        }




                    } catch (UnsupportedEncodingException e) {
                        throw new RuntimeException(e);
                    }



                }
                return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
            }
        };

        requestQueue.add(stringRequest);
    }

    public void buildJson()
    {

        try {
            JSONObject root = new JSONObject(pokemonList);


            JSONArray array= root.getJSONArray("results");

            pokemonArrayList = array;

            Log.e("Pokemon Array", String.valueOf(array));
            pokemonListString=String.valueOf(array);

            for(int i=0;i<array.length();i++)
            {
                JSONObject object= array.getJSONObject(i);
                items.add(object.getString("name"));
            }

            Log.e("Items", String.valueOf(items));
           // buildRecyclerView();


            viewModel.setPokemonArrayList((ArrayList<String>) items);
            Log.e("Pokemon Array in Seter ViewModel", String.valueOf(items));

            pokListViewModel.setPokemonJsonList(array);




        } catch (JSONException e) {
            throw new RuntimeException(e);

        }
    }

    public void buildPokemonJson()
    {

        Log.e("buildPokemonJson","");
        try {
            JSONObject selectedPokemon = new JSONObject(selectedPokemonString);
            //JSONArray array= selectedPokemon.getJSONArray("sprites");


            JSONObject selectedPokemonSprite = selectedPokemon.getJSONObject("sprites");

            Log.e("Pokemon Array", String.valueOf(selectedPokemonSprite));

            String pokemonSpriteURL = selectedPokemonSprite.getString("back_default");
            Log.e("Pokemon sprite URL", pokemonSpriteURL);


            viewModel.setSelectedPokemonURL(pokemonSpriteURL);

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }


}

/*
class ScoreViewModel extends ViewModel {
    // Tracks the score for Team A
    public int scoreTeamA = 2000;

    // Tracks the score for Team B
    public int scoreTeamB = 0;

    public void setScoreTeamA(int scoreTeamA) {
        this.scoreTeamA = scoreTeamA;
    }


    public int getScoreTeamA() {
        return scoreTeamA;
    }



}
*/








