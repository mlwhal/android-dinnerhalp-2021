package com.gmail.mlwhal.dinnerhalp.ui.main;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.gmail.mlwhal.dinnerhalp.R;

//Custom class for servings Alert Dialog
public class ServingsDialogFragment extends AppCompatDialogFragment {

    private static final String TAG = "ServingsDialogFrag";

//    public ServingsDialogFragment() {
//        // Required empty public constructor
//    }

    public static ServingsDialogFragment newInstance(int title) {
        ServingsDialogFragment frag = new ServingsDialogFragment();
        Bundle args = new Bundle();
        args.putInt("title", title);
        frag.setArguments(args);
        return frag;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        int title = getArguments().getInt("title");
        //Create a string array to hold serving values; these will be the db query terms.
        final String[] servings = getResources().getStringArray(R.array.servings_array);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title)
                .setItems(R.array.servings_array, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position) {
//                            Log.d(TAG, "Position clicked " + position);
                        //Query DB for selected no. of servings and display a list of dinners
                        Intent intent = new Intent(getActivity(), DinnerListActivity.class);
                        intent.putExtra("SEARCH_COLUMN", "servings LIKE ?");
                        intent.putExtra("SEARCH_STRING", servings[position]);
                        startActivity(intent);
                    }
                });
        return builder.create();
    }
}
