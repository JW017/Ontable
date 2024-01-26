//package com.example.ontable2;
//
//import android.os.Bundle;
//
//import androidx.annotation.NonNull;
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//
//import com.firebase.ui.database.FirebaseRecyclerOptions;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//import java.util.ArrayList;
//
///**
// * A simple {@link Fragment} subclass.
// * Use the {@link display_memo#newInstance} factory method to
// * create an instance of this fragment.
// */
//public class display_memo extends Fragment {
//    RecyclerView memoList;
//    MainAdapter mainAdapter;
//    DatabaseReference database;
//    ArrayList<MainModel> list;
//    private static final String TAG = "DisplayMemoFragment";
//
//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    public display_memo() {
//        // Required empty public constructor
//    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment display_memo.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static display_memo newInstance(String param1, String param2) {
//        display_memo fragment = new display_memo();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_display_memo, container, false);
//
//        memoList = view.findViewById(R.id.memolist);
//        database = FirebaseDatabase.getInstance().getReference("notes");
//        memoList.setHasFixedSize(true);
//        memoList.setLayoutManager(new LinearLayoutManager(getActivity()));
//
//        list = new ArrayList<>();
//
//        FirebaseRecyclerOptions<MainModel> options = new FirebaseRecyclerOptions.Builder<MainModel>()
//                .setQuery(database, MainModel.class)
//                .build();
//
//        mainAdapter = new MainAdapter(options);
//        memoList.setAdapter(mainAdapter);
//
//        mainAdapter.startListening();
//
//        database.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
//
//                    // assuming that your MainModel has a 'memodetails' field of type String
//                    String memodetails = dataSnapshot.child("memodetails").getValue(String.class);
//
//                    MainModel mainModel = new MainModel(memodetails);
//                    list.add(mainModel);
//                    Log.d(TAG, "Memo Details: " + mainModel);
//
//                }
//                mainAdapter.notifyDataSetChanged();
//
//            }
//
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//
//
//
//        Button btnReturn = view.findViewById(R.id.btnReturn);
//        btnReturn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                getParentFragmentManager().beginTransaction().remove(display_memo.this).commit();
//
//                memo fragment = new memo();
//                getParentFragmentManager().beginTransaction()
//                        .replace(R.id.container, fragment)
//                        .addToBackStack(null)
//                        .commit();
//            }
//        });
//        return view;
//    }
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        if (mainAdapter != null) {
//            mainAdapter.stopListening();
//        }
//    }
//}