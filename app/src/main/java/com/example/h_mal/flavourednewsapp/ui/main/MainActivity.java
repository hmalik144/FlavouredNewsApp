package com.example.h_mal.flavourednewsapp.ui.main;

import android.os.Bundle;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.h_mal.flavourednewsapp.R;
import com.example.h_mal.flavourednewsapp.app.FlavouredNewsAppClass;
import com.example.h_mal.flavourednewsapp.ui.main.home.MainFragment;

import javax.inject.Inject;

import static com.example.h_mal.flavourednewsapp.utils.ViewUtils.displayToast;
import static com.example.h_mal.flavourednewsapp.utils.ViewUtils.hide;
import static com.example.h_mal.flavourednewsapp.utils.ViewUtils.show;

public class MainActivity extends AppCompatActivity {
    @Inject
    MainViewModelFactory mainViewModelFactory;

    public MainViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ProgressBar progressBar = findViewById(R.id.progress_circular);

        // Retrieve Dagger2 component from Application class
        ((FlavouredNewsAppClass) getApplication()).appComponent.inject(this);
        // Create viewmodel
        mViewModel = new ViewModelProvider(this, mainViewModelFactory).get(MainViewModel.class);

        // Observe operation state to display progress bar
        mViewModel.operationState.observe(this, aBoolean -> {
            if (aBoolean){
                show(progressBar);
            }else {
                hide(progressBar);
            }
        });

        // Display a toast error if no internet
        mViewModel.operationError.observe(this, stringEvent -> {
            String mes = stringEvent.getContentIfNotHandled();
            if (mes != null){
                displayToast(this, mes);
            }
        });

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commit();
        }
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
    }
}