package com.example.fragmenttypetransaction;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String ACTIVITY_NAME=MainActivity.class.getSimpleName();
    private static final String TAG=ACTIVITY_NAME;

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    private Button buttonAddFragment;
    private TextView textViewFragmentCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonAddFragment=findViewById(R.id.buttonAddFragment);
        textViewFragmentCount=findViewById(R.id.textViewFragmentCount);

        fragmentManager=getSupportFragmentManager();

        textViewFragmentCount.setText("Fragment count in back stack: "+fragmentManager.getBackStackEntryCount());

        fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                textViewFragmentCount.setText("Fragment count in back stack :"+fragmentManager.getBackStackEntryCount());
            }
        });
        Log.i(TAG,"Initial BackStackEntryCount: "+fragmentManager.getBackStackEntryCount());
        buttonAddFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFragment();
            }
        });

    }

    private void addFragment() {
        Fragment fragment;
        switch (fragmentManager.getBackStackEntryCount()){
            case 0:fragment=new Fragment1();break;
            case 1:fragment=new Fragment2();break;
            case 2:fragment=new Fragment3();break;
            default:fragment=new Fragment1();break;
        }
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frame,fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
