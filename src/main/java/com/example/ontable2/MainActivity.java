package com.example.ontable2;

import static com.google.android.material.internal.ViewUtils.dpToPx;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import java.util.Objects;


public class MainActivity extends AppCompatActivity {

    MainAdapter mainAdapter;
    Button button;
    FrameLayout container;
    GlobalVariables globalVariables = GlobalVariables.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        container = findViewById(R.id.container);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.button) {
                    boolean isMemoOpened = globalVariables.isMemoOpened();
                    if (isMemoOpened == false){
                        openMemo();
                    } else if (isMemoOpened == true) {
                        closeMemo();
                    }
                }
            }
        });
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        mainAdapter.startListening();
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        mainAdapter.stopListening();
//    }

    private void openMemo(){
        globalVariables.setMemoOpened(true);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new memo()).commit();
    }

    private void closeMemo(){
        globalVariables.setMemoOpened(false);
        getSupportFragmentManager().beginTransaction()
                // Remove the current fragment from the container
                .remove(Objects.requireNonNull(getSupportFragmentManager().findFragmentById(R.id.container)))
                // Commit the transaction
                .commit();
    }
}