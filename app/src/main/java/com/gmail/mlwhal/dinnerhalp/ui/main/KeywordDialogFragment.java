package com.gmail.mlwhal.dinnerhalp.ui.main;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.gmail.mlwhal.dinnerhalp.R;

//Custom class for keyword Alert Dialog
public class KeywordDialogFragment extends AppCompatDialogFragment {
    public KeywordDialogFragment() {
        // Required empty public constructor
    }

    public static KeywordDialogFragment newInstance(int title) {
        KeywordDialogFragment fragment = new KeywordDialogFragment();
        Bundle args = new Bundle();
        args.putInt("title", title);
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        assert getArguments() != null;
        int title = getArguments().getInt("title");

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(),
                R.style.CustomAlertDialogTheme);
        //Get the layout inflater
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        //Cast the Dialog as a View so I can get text out of the EditText
        //http://stackoverflow.com/questions/12799751/android-how-do-i-retrieve-edittext-gettext-in-custom-alertdialog
        //Inflate and set the layout
        //Pass null as the parent view because it's going in the dialog layout
        final View dialogView = inflater.inflate(R.layout.dialog_keywordsearch, null);
        builder.setTitle(title).setView(dialogView)
                //add action buttons
                .setPositiveButton(R.string.button_search, (dialogInterface, i) -> {
                    //Pull user input from EditText to construct db query
                    EditText keywordEditText = dialogView.findViewById(R.id.dialog_edittext_keyword);
                    String searchString = "%" + keywordEditText.getText().toString() + "%";
                    String whereClause = "name LIKE ? OR recipe LIKE ?";

                    Intent intent = new Intent(getActivity(),DinnerListActivity.class);
                    /*Flag this as a keyword search so that two WhereArgs are used
                     * when the database is searched */
                    intent.putExtra("KEYWORD_SEARCH", true);
                    intent.putExtra("SEARCH_COLUMN", whereClause);
                    intent.putExtra("SEARCH_STRING", searchString);
                    startActivity(intent);
                })
                .setNegativeButton(R.string.button_cancel, (dialogInterface, i) ->
                        KeywordDialogFragment.this.getDialog().cancel());
        return builder.create();
    }

}