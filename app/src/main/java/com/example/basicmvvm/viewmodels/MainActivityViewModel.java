package com.example.basicmvvm.viewmodels;


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


    private NicePlace newPlace;


    public void init(){
        if(mNicePlaces != null){
            return;
        }
        mRepo = NicePlaceRepository.getInstance();
        mNicePlaces = mRepo.getNicePlaces();
    }




    public void addValue(final NicePlace nicePlace){
        mIsUpdating.setValue(true);
        newPlace = nicePlace;
        new AsyncCaller().execute();

    }




    public LiveData<ArrayList<NicePlace>> getNicePlaces(){
        return mNicePlaces;
    }


    public LiveData<Boolean> getIsUpdating(){
        return mIsUpdating;
    }




    private class AsyncCaller extends AsyncTask<Void, Void, Void>
    {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }
        @Override
        protected Void doInBackground(Void... params) {

            //this method will be running on background thread so don't update UI from here
            //do your long running http tasks here,
            // you don't want to pass argument and u can access the parent class' variable url over here

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;


        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            //this method will be running on UI thread

            ArrayList<NicePlace> currentPlaces = mNicePlaces.getValue();
            currentPlaces.add(newPlace);
            mNicePlaces.postValue(currentPlaces);
            mIsUpdating.postValue(false);
        }

    }
}
