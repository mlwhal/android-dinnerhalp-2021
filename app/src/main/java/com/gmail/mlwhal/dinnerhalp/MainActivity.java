package com.gmail.mlwhal.dinnerhalp;

import android.content.Intent;
import android.os.Bundle;

import com.gmail.mlwhal.dinnerhalp.ui.main.CookMethodDialogFragment;
import com.gmail.mlwhal.dinnerhalp.ui.main.CookTimeDialogFragment;
import com.gmail.mlwhal.dinnerhalp.ui.main.KeywordDialogFragment;
import com.gmail.mlwhal.dinnerhalp.ui.main.ServingsDialogFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.gmail.mlwhal.dinnerhalp.ui.main.SectionsPagerAdapter;
import com.gmail.mlwhal.dinnerhalp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Get the toolbar w/the options menu up and running
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);

        //Use FRAGMENT_TRACKER to load requested tab from other activities
        Intent intent = getIntent();
        int startingTab = intent.getIntExtra("FRAGMENT_TRACKER", 0);
        if (startingTab != 0) {
            viewPager.setCurrentItem(startingTab);
        }

//        //Set up floating action button
//        //Todo: Do we want/need a FAB?
//        FloatingActionButton fab = binding.fab;
//
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_add_dinner) {
/*                Intent intent = new Intent(this, AddDinnerActivity.class);
                this.startActivity(intent);*/
                return true;
        } else if (id == R.id.action_settings) {
            Intent intent2 = new Intent(this, SettingsActivity.class);
            this.startActivity(intent2);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    //Todo: Add onSaveInstanceState() and onRestoreInstanceState() for tab tracking

    //Methods to invoke various search dialog fragments
    //Todo: Are these best in MainActivity or in SearchFragment? The custom classes seem to use
    // getActivity()
    void confirmKeyword() {
        AppCompatDialogFragment newFragment =
                KeywordDialogFragment.newInstance(R.string.keyword_alert_title);
        newFragment.show(getSupportFragmentManager(), "keywordConfirm");
    }

    void confirmMethod() {
        AppCompatDialogFragment newFragment =
                CookMethodDialogFragment.newInstance(R.string.method_alert_title);
        newFragment.show(getSupportFragmentManager(), "methodConfirm");
    }

    void confirmTime() {
        AppCompatDialogFragment newFragment =
                CookTimeDialogFragment.newInstance(R.string.time_alert_title);
        newFragment.show(getSupportFragmentManager(), "timeConfirm");
    }

    void confirmServings() {
        AppCompatDialogFragment newFragment =
                ServingsDialogFragment.newInstance(R.string.servings_alert_title);
        newFragment.show(getSupportFragmentManager(), "servingsConfirm");
//        Log.d(TAG, "Servings clicked!");
    }

}