package com.gmail.mlwhal.dinnerhalp.data;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "dinners", indices = {@Index(value = {"name"},
        unique = true)})

public class Dinner {
    @PrimaryKey(autoGenerate = true)
    public Long id;

    public String name;

    public String method;

    public String time;

    public String servings;

    public String picpath;

    public byte[] picdata;

    public String recipe;
}
