package com.example.pokedexnavgraph;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;

import com.example.pokedexnavgraph.databinding.FragmentSecondBinding;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;

    pokListViewModel userModel;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState





    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();



    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        userModel = ViewModelProviders.of(getActivity()).get(pokListViewModel.class);
        binding.pokemonName.setText(userModel.getSelectedPolkemonInList());


        ((MainActivity) getActivity()).httpCall("pokemonInfo", userModel.getSelectedPolkemonInList());


        URL url = null;
        try {
            url = new URL(userModel.getSelectedPokemonURL());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        Bitmap bmp = null;
        try {
            bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

      //  binding.imageViewPokemon.setImageBitmap(bmp);



        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}