package com.gmail.mlwhal.dinnerhalp.ui.main;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.gmail.mlwhal.dinnerhalp.CustomListAdapter;
import com.gmail.mlwhal.dinnerhalp.R;

public class ManageFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";

    public static ManageFragment newInstance(int index) {
        ManageFragment fragment = new ManageFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PageViewModel pageViewModel = new ViewModelProvider(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);

        //Tell fragment that there's an options menu
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_manage, container, false);

        //Create ListView and add ArrayAdapter to display search options
        final ListView listView = rootView.findViewById(R.id.list);

        //Get list item names and image ids from array resources
        Resources res = getResources();
        String[] itemName = res.getStringArray(R.array.manage_dinners_array);
        TypedArray imageResArray = res.obtainTypedArray(R.array.manage_dinner_icon_array);
        int lgth = imageResArray.length();
        Integer[] imageID = new Integer[lgth];
        for (int i = 0; i < lgth; i++) {
            imageID[i] = imageResArray.getResourceId(i, 0);
        }
        imageResArray.recycle();

        //Create custom adapter and pass in context, image, and name info
        CustomListAdapter adapter = new CustomListAdapter(this.getActivity(), itemName, imageID);
        listView.setAdapter(adapter);
//        listView.setOnItemClickListener(new );
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        binding = null;
    }
}
