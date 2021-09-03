package com.example.basicmvvm.repositories;


import androidx.lifecycle.MutableLiveData;
import com.example.basicmvvm.models.NicePlace;
import java.util.ArrayList;


/**
 * Singleton pattern
 */


public class NicePlaceRepository {

    private static NicePlaceRepository instance;
    private ArrayList<NicePlace> dataSet = new ArrayList<>();

    public static NicePlaceRepository getInstance() {
        if (instance == null) {
            instance = new NicePlaceRepository();
        }
        return instance;
    }


    // Pretend to get data from a webservice or online source
    public MutableLiveData<ArrayList<NicePlace>> getNicePlaces() {
        setNicePlaces();
        MutableLiveData<ArrayList<NicePlace>> data = new MutableLiveData<>();
        data.setValue(dataSet);
        return data;
    }

    private void setNicePlaces() {
        dataSet.add(
                new NicePlace("https://i.picsum.photos/id/735/536/354.jpg?hmac=QbcithUsGapa5RK_Oa1ZnnPIh3X-IDt1BD0sYRvDyR8",
                        "Havasu Falls")
        );
        dataSet.add(
                new NicePlace("https://i.redd.it/tpsnoz5bzo501.jpg",
                        "Trondheim")
        );
        dataSet.add(
                new NicePlace("https://i.redd.it/qn7f9oqu7o501.jpg",
                        "Portugal")
        );
        dataSet.add(
                new NicePlace("https://i.redd.it/j6myfqglup501.jpg",
                        "Rocky Mountain National Park")
        );
        dataSet.add(
                new NicePlace("https://i.redd.it/0h2gm1ix6p501.jpg",
                        "Havasu Falls")
        );
        dataSet.add(
                new NicePlace("https://i.redd.it/k98uzl68eh501.jpg",
                        "Mahahual")
        );
        dataSet.add(
                new NicePlace("https://i.picsum.photos/id/23/536/354.jpg?hmac=U269wMTMm4XnnhSFkRYwT84CYWi4qWzeWId1-2TfFxI",
                        "Frozen Lake")
        );
        dataSet.add(
                new NicePlace("https://i.redd.it/obx4zydshg601.jpg",
                        "Austrailia")
        );
    }
}











