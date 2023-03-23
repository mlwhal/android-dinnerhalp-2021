package com.gmail.mlwhal.dinnerhalp.ui.main;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.gmail.mlwhal.dinnerhalp.R;

//Custom class for cooking time Alert Dialog
public class CookTimeDialogFragment extends AppCompatDialogFragment {
    public CookTimeDialogFragment() {
        // Required empty public constructor
    }

    public static CookTimeDialogFragment newInstance(int title) {
        CookTimeDialogFragment fragment = new CookTimeDialogFragment();
        Bundle args = new Bundle();
        args.putInt("title", title);
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        int title = getArguments().getInt("title");
        //Create a string array to hold time values; these will be the db query terms.
        final String[] times = getResources().getStringArray(R.array.time_array);

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title)
                .setItems(R.array.time_array, (dialog, position) -> {
//                            Log.d(TAG, "Position clicked " + position);
//                            Log.d(TAG, "Item clicked " + times[position]);
                    //Query DB for selected cook time and display a list of dinners
                    //Also pass in "time" as column to search
                    Intent intent = new Intent(getActivity(), DinnerListActivity.class);
                    intent.putExtra("SEARCH_COLUMN", "time LIKE ?");
                    intent.putExtra("SEARCH_STRING", times[position]);
                    startActivity(intent);
                });
        return builder.create();
    }

}
