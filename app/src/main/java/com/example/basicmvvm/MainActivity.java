package com.example.basicmvvm;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;
import com.example.basicmvvm.adapters.RecyclerAdapter;
import com.example.basicmvvm.databinding.ActivityMainBinding;
import com.example.basicmvvm.models.NicePlace;
import com.example.basicmvvm.viewmodels.MainActivityViewModel;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private RecyclerAdapter mAdapter;
    private MainActivityViewModel mMainActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        init();
    }

    private void init(){

        mMainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        mMainActivityViewModel.init();


        mMainActivityViewModel.getNicePlaces().observe(this, new Observer<ArrayList<NicePlace>>() {
            @Override
            public void onChanged(@Nullable ArrayList<NicePlace> nicePlaces) {
                mAdapter.notifyDataSetChanged();
            }
        });

        mMainActivityViewModel.getIsUpdating().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if(aBoolean){
                    showProgressBar();
                }
                else{
                    hideProgressBar();
                    binding.recyclerView.smoothScrollToPosition(mMainActivityViewModel.getNicePlaces().getValue().size()-1);
                }
            }
        });


        binding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMainActivityViewModel.addNewValue(
                        new NicePlace(
                                "https://i.imgur.com/ZcLLrkY.jpg",
                                "Washington"
                        )
                );
            }
        });

        initRecyclerView();
    }

    private void initRecyclerView(){
        mAdapter = new RecyclerAdapter(this, mMainActivityViewModel.getNicePlaces().getValue());
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.recyclerView.setLayoutManager(linearLayoutManager);
        binding.recyclerView.setAdapter(mAdapter);
    }

    private void showProgressBar(){
        binding.progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar(){
        binding.progressBar.setVisibility(View.GONE);
    }
}