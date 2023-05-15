package com.example.pokedexnavgraph;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.lifecycle.ViewModelStore;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pokedexnavgraph.placeholder.PlaceholderContent;


/**
 * A fragment representing a list of Items.
 */
public class PokemonListFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */

   // pokListViewModel viewModel;
    pokListViewModel viewModel;
    String string;
    public PokemonListFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static PokemonListFragment newInstance(int columnCount) {
        PokemonListFragment fragment = new PokemonListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }




       // viewModel =ViewModelProviders.of(this).get(pokListViewModel.class);


        //viewModel =ViewModelProviders.of(this).get(ScoreViewModel.class);
      //  int number = viewModel.getScoreTeamA();

       // final ViewModel UserModel = viewModel = ViewModelProviders.of(this).get(UserModel.class);


        pokListViewModel userModel = ViewModelProviders.of(getActivity()).get(pokListViewModel.class);
        int number = userModel.getScoreTeamA();


       // ViewModelStore viewModelStore;


        Log.e("Fragment Final Value"," "+  number);

      //  Toast.makeText(getActivity(),number,Toast.LENGTH_SHORT).show();










    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pokemon_list_array, container, false);



        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;


            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new PokemonListRecyclerViewAdapter(PlaceholderContent.ITEMS));
        }
        return view;
    }



}