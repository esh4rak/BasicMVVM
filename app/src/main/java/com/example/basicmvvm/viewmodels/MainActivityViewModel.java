package com.example.basicmvvm.viewmodels;


import android.annotation.SuppressLint;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.basicmvvm.models.NicePlace;
import com.example.basicmvvm.repositories.NicePlaceRepository;
import java.util.ArrayList;



public class MainActivityViewModel extends ViewModel {

    private MutableLiveData<ArrayList<NicePlace>> mNicePlaces;
    private NicePlaceRepository mRepo;
    private MutableLiveData<Boolean> mIsUpdating = new MutableLiveData<>();

    public void init(){
        if(mNicePlaces != null){
            return;
        }
        mRepo = NicePlaceRepository.getInstance();
        mNicePlaces = mRepo.getNicePlaces();
    }

    public void addNewValue(final NicePlace nicePlace){
        mIsUpdating.setValue(true);

        new AsyncTask<Void, Void, Void>(){
            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                ArrayList<NicePlace> currentPlaces = mNicePlaces.getValue();
                currentPlaces.add(nicePlace);
                mNicePlaces.postValue(currentPlaces);
                mIsUpdating.postValue(false);
            }

            @SuppressLint("StaticFieldLeak")
            @Override
            protected Void doInBackground(Void... voids) {

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();
    }

    public LiveData<ArrayList<NicePlace>> getNicePlaces(){
        return mNicePlaces;
    }


    public LiveData<Boolean> getIsUpdating(){
        return mIsUpdating;
    }
}
