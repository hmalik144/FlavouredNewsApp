package com.example.h_mal.flavourednewsapp.ui.main.overview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.h_mal.flavourednewsapp.R;
import com.example.h_mal.flavourednewsapp.ui.main.MainActivity;
import com.example.h_mal.flavourednewsapp.ui.main.MainViewModel;


public class OverviewFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";

    private MainViewModel mViewModel;

    private String mUrl;

    public OverviewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param url Parameter 1.
     * @return A new instance of fragment OverviewFragment.
     */
    public static OverviewFragment newInstance(String url) {
        OverviewFragment fragment = new OverviewFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, url);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mUrl = getArguments().getString(ARG_PARAM1);
        }
        mViewModel = ((MainActivity) requireActivity()).mViewModel;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_overview, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView overviewRecycler = view.findViewById(R.id.overview_recycler_view);

        mViewModel.getSingleNews(mUrl).observe(getViewLifecycleOwner(), newsEntity ->
                overviewRecycler.setAdapter( new OverviewRecyclerAdapter(requireContext(), newsEntity))
        );
    }
}