package com.example.pokedexnavgraph;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class pokListViewModel extends ViewModel {

    public static int score;
    public static String getPokemonArrayList;
    public int scoreTeamA = 0;

    // Tracks the score for Team B
    public int scoreTeamB = 0;

    pokListViewModel userModel;

    public String selectedPolkemonInList;

    public String getSelectedPolkemonInList() {
        return selectedPolkemonInList;
    }

    public static ArrayList<String> pokemonArrayList = new ArrayList<>();
    public static JSONArray pokemonJsonList = new JSONArray();

    public String selectedPokemonURL;




    public ArrayList<String> getPokemonArrayList() {
        return pokemonArrayList;
    }

    public int getScoreTeamA() {
        return scoreTeamA;
    }

    public int getScoreTeamB() {
        return scoreTeamB;
    }

    public static String getGetPokemonArrayList() {
        return getPokemonArrayList;
    }

    public void setScoreTeamA(int scoreTeamA) {
        this.scoreTeamA = scoreTeamA;
    }

    public void setPokemonArrayList(ArrayList<String> pokemonArrayList) {
        this.pokemonArrayList = pokemonArrayList;
    }

    public static void setPokemonJsonList(JSONArray pokemonJsonList) {
        pokListViewModel.pokemonJsonList = pokemonJsonList;
    }

    public static String getPokemonArrayListPositionElement(int position)
    {


        return pokemonArrayList.get(position);


    }

    public void setSelectedPolkemonInList(String selectedPolkemonInList, int position) {
        this.selectedPolkemonInList = selectedPolkemonInList;


        try {
           // pokemonJsonList.getJSONObject(position);
            Log.e("Pokemon Full Data"," " + pokemonJsonList.getJSONObject(position));

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }


    }

    public void setSelectedPokemonURL(String selectedPokemonURL) {

        this.selectedPokemonURL = selectedPokemonURL;
    }

    public String getSelectedPokemonURL() {
        return selectedPokemonURL;
    }
}
