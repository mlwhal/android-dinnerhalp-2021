package com.gmail.mlwhal.dinnerhalp.data;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

public interface DinnerDao {

    //clearAllDinners
    @Query("DELETE FROM dinners")
    int clearAllDinners();

    //createDinner INSERT INTO dinners (stuff) VALUES()
    //onConflict ignores but returns -1
    //Todo: Figure out how to handle this in AddDinnerActivity or wherever
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    LiveData<Integer> createDinner(Dinner dinner);

    //deleteDinner - can use @Delete !
    @Query("DELETE FROM dinners WHERE id = :id")
    boolean deleteDinner(Long id);

    //fetchDinner - get one dinner
    @Query("SELECT * FROM dinners WHERE id = :id")
    Dinner fetchDinner(Long id);

    //fetchDinnerSearch - get dinners that match user's search criteria
    //Todo: This method and fetchAllDinners() can use a predefined List<Tuple> rather than List<Dinner>
    @Query("SELECT id, name FROM dinners WHERE :whereClause = :searchString")
    LiveData<List<Dinner>> fetchDinnerSearch(String whereClause, String searchString);

    //fetchAllDinners - just need id and name for DinnerListActivity
    @Query("SELECT id, name FROM dinners ORDER BY name ASC")
    LiveData<List<Dinner>> fetchAllDinners();

    //FYI, rather than LiveData, you can have these methods return a Cursor;
    //see https://developer.android.com/training/data-storage/room/accessing-data

    //updateDinner
    @Update(entity = Dinner.class, onConflict = OnConflictStrategy.REPLACE)
    int updateDinner(Dinner dinner);
}
