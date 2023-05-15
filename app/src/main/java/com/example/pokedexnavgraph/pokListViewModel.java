package com.example.pokedexnavgraph;

import android.service.autofill.UserData;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;

public class pokListViewModel extends ViewModel {

    public static int score;
    public static String getPokemonArrayList;
    public int scoreTeamA = 0;

    // Tracks the score for Team B
    public int scoreTeamB = 0;

    public ArrayList<String> pokemonArrayList = new ArrayList<>();

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


}
