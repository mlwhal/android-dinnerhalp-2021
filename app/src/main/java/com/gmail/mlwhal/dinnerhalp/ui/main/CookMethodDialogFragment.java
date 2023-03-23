package com.gmail.mlwhal.dinnerhalp.ui.main;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gmail.mlwhal.dinnerhalp.R;

//Custom class for cooking method Alert Dialog
public class CookMethodDialogFragment extends AppCompatDialogFragment {

    public CookMethodDialogFragment() {
        // Required empty public constructor
    }

    public static CookMethodDialogFragment newInstance(int title) {
        CookMethodDialogFragment fragment = new CookMethodDialogFragment();
        Bundle args = new Bundle();
        args.putInt("title", title);
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        int title = getArguments().getInt("title");
        //Create a string array to hold method values; these will be the db query terms.
        final String[] methods = getResources().getStringArray(R.array.method_array);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title).setItems(R.array.method_array, (dialogInterface, position) -> {
            //Query DB for selected method and display a list of dinners
            Intent intent = new Intent(getActivity(), DinnerListActivity.class);
            intent.putExtra("SEARCH_COLUMN", "method LIKE ?");
            intent.putExtra("SEARCH_STRING", methods[position]);
            startActivity(intent);
        });
        return builder.create();
    }
}