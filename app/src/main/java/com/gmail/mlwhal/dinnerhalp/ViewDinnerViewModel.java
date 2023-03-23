package com.gmail.mlwhal.dinnerhalp;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import com.gmail.mlwhal.dinnerhalp.data.Dinner;
import com.gmail.mlwhal.dinnerhalp.data.DinnerRepository;

public class ViewDinnerViewModel extends AndroidViewModel {

    private DinnerRepository mRepository;
    private Dinner mCurrentDinner;

    public ViewDinnerViewModel (Application application) {
        super(application);
        mRepository = new DinnerRepository(application);
    }

    Dinner fetchDinner(Long id) {
        mCurrentDinner = mRepository.fetchDinner(id);
        return mCurrentDinner;
    }
}
