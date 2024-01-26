package com.example.ontable2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link memo#newInstance} factory method to
 * create an instance of this fragment.
 */
public class memo extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public memo() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment add_memo.
     */
    // TODO: Rename and change types and number of parameters
    public static memo newInstance(String param1, String param2) {
        memo fragment = new memo();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_memo, container, false);

        Button btnnew = view.findViewById(R.id.btnNew);
        btnnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Close the current fragment
                getParentFragmentManager().beginTransaction().remove(memo.this).commit();

                // Open another fragment
                add_memo fragment = new add_memo();
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.container, fragment) // Replace "container" with your actual container ID
                        .addToBackStack(null)
                        .commit();
            }
        });

        Button btnview = view.findViewById(R.id.btnView);
        btnview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager().beginTransaction().remove(memo.this).commit();

                display_memo fragment = new display_memo();
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.container, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        Button btnback = view.findViewById(R.id.btnBack);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Close or remove the current fragment
                closeFragment();
            }
        });

        return view;
    }

    GlobalVariables globalVariables = GlobalVariables.getInstance();
    private void closeFragment() {
            globalVariables.setMemoOpened(false);
            requireActivity().getSupportFragmentManager().beginTransaction()
                    // Remove the current fragment from the container
                    .remove(this)
                    // Commit the transaction
                    .commit();
    }
}