package com.example.ontable2;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link add_memo#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class add_memo extends Fragment {

    FirebaseFirestore firestore;
    Button btnClear, btnReturn, btnSave;
    EditText txtDetails;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "add_memo";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment add_memo.
     */
    // TODO: Rename and change types and number of parameters
    public static add_memo newInstance(String param1, String param2) {
        add_memo fragment = new add_memo();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public add_memo() {
        // Required empty public constructor
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_memo, container, false);

        firestore = FirebaseFirestore.getInstance();
        btnClear = view.findViewById(R.id.btnClear);
        btnReturn = view.findViewById(R.id.btnReturn);
        btnSave = view.findViewById(R.id.btnSave);
        txtDetails = view.findViewById(R.id.txtDetails);

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Implement the clear text function here
                txtDetails.setText("");
            }
        });


        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager().beginTransaction().remove(add_memo.this).commit();

                memo fragment = new memo();
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.container, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txtdetails = txtDetails.getText().toString();

                if (txtdetails.isEmpty()){
                    Toast.makeText(getActivity(), "Cannot save memo with empty field.", Toast.LENGTH_SHORT).show();
                    return;
                }
                insertData(txtdetails);

                getParentFragmentManager().beginTransaction().remove(add_memo.this).commit();

                display_memo fragment = new display_memo();
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.container, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
        return view;
    }

    private void insertData(String txtdetails) {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference notesRef = database.getReference("notes");

        // Get the previous note's id and calculate the next id
        notesRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    int lastId = 0;
                    for (DataSnapshot noteSnapshot: dataSnapshot.getChildren()) {
                        int currentId = noteSnapshot.getKey().equals("counter") ?
                                noteSnapshot.getValue(Integer.class) : Integer.parseInt(noteSnapshot.getKey());
                        if (currentId > lastId) {
                            lastId = currentId;
                        }
                    }
                    int nextId = lastId + 1;

                    // Insert the new note data with the next id
                    notesRef.child(String.valueOf(nextId)).child("memodetails").setValue(txtdetails);

                    // Update the id counter in the Firebase Realtime Database
                    DatabaseReference counterRef = database.getReference("notes/counter");
                    counterRef.runTransaction(new Transaction.Handler() {
                        @NonNull
                        @Override
                        public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                            Integer currentCounter = mutableData.getValue(Integer.class);
                            if (currentCounter == null) {
                                mutableData.setValue(nextId);
                            } else {
                                mutableData.setValue(currentCounter + 1);
                            }
                            return Transaction.success(mutableData);
                        }

                        @Override
                        public void onComplete(DatabaseError databaseError, boolean b, DataSnapshot dataSnapshot) {
                            if (databaseError != null) {
                                Log.w(TAG, "onComplete: Transaction failed.");
                            } else {
                                Log.d(TAG, "onComplete: Transaction successful, updated id counter is " + dataSnapshot.getValue());
                            }
                        }
                    });
                } else {
                    // Handle the case when there are no notes yet
                    notesRef.child("1").child("memodetails").setValue(txtdetails);
                }
                Toast.makeText(getActivity(), "Memo saved.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w(TAG, "onCancelled: Failed to read note data.", databaseError.toException());
            }
        });
    }
}