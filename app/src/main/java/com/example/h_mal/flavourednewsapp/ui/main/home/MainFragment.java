package com.example.h_mal.flavourednewsapp.ui.main.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.h_mal.flavourednewsapp.R;
import com.example.h_mal.flavourednewsapp.ui.main.MainActivity;
import com.example.h_mal.flavourednewsapp.ui.main.MainViewModel;

public class MainFragment extends Fragment implements SearchView.OnQueryTextListener {

    private MainViewModel mViewModel;

    private  RecyclerView recyclerView;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // setup menu for searching
        setHasOptionsMenu(true);
        recyclerView = view.findViewById(R.id.recycler_view);
        mViewModel = ((MainActivity) requireActivity()).mViewModel;

        // observe livedata to populate recycler view of articles
        mViewModel.getNewsLiveData().observe(getViewLifecycleOwner(), newsEntities ->
                recyclerView.setAdapter(
                        new NewsRecyclerAdapter((MainActivity) requireActivity(), newsEntities)
                )
        );
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu, menu);

        // Setup search bar in fragment
        MenuItem filter = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView) filter.getActionView();
        searchView.setOnQueryTextListener(this);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (newText.length() >= 3){
            mViewModel.getNews(newText);
        }
        return false;
    }
}