package com.gmail.mlwhal.dinnerhalp.data;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//Class is based on https://developer.android.com/codelabs/android-room-with-a-view#7

@Database(entities = {Dinner.class}, version = 3)
public abstract class DinnersRoomDatabase extends RoomDatabase {

    public abstract DinnerDao dinnerDao();

    private static volatile DinnersRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;

    //Migration objects will allow migration from SQLite (which had two versions)
    static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            //empty because table is unchanged from version 2
        }
    };

    static final Migration MIGRATION_1_3 = new Migration(1, 3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE dinners ADD COLUMN picdata BLOB");
        }
    };

    //The ExecutorService is used for running db operations asynchronously
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static DinnersRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (DinnersRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            DinnersRoomDatabase.class, "dinnerData")
                            .addMigrations(MIGRATION_1_3, MIGRATION_2_3)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
