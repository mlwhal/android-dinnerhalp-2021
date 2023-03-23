package com.gmail.mlwhal.dinnerhalp.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class DinnerRepository {

    private DinnerDao mDinnerDao;
    private LiveData<List<Dinner>> mFetchedDinners;
    private LiveData<List<Dinner>> mAllDinners;

    public DinnerRepository(Application application) {
        DinnersRoomDatabase db = DinnersRoomDatabase.getDatabase(application);
        mDinnerDao = db.dinnerDao();
        mAllDinners = mDinnerDao.fetchAllDinners();
    }

    //All methods for ViewModel to call on the database via this Repository
    public int clearAllDinners() {
        return mDinnerDao.clearAllDinners();
    }

    public LiveData<Integer> createDinner(Dinner dinner) {
         return mDinnerDao.createDinner(dinner);
    }

    public boolean deleteDinner(Long id) {
        return mDinnerDao.deleteDinner(id);
    }

    public Dinner fetchDinner(Long id) {
        return mDinnerDao.fetchDinner(id);
    }

    public LiveData<List<Dinner>> fetchDinnerSearch(String whereClause, String searchString) {
        return mDinnerDao.fetchDinnerSearch(whereClause, searchString);
    }

    public LiveData<List<Dinner>> fetchAllDinners() {
        return mAllDinners;
    }

    public int updateDinner(Dinner dinner) {
        return mDinnerDao.updateDinner(dinner);
     }
}
