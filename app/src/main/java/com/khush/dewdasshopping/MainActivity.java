package com.khush.dewdasshopping;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    protected BottomNavigationView bottom_navigation;
    protected FrameLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottom_navigation = findViewById(R.id.bottom_navigation);

        bottom_navigation.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        String title = menuItem.getTitle().toString().toLowerCase();

                        if(title.equals("orders")){
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Orders()).commit();

                        }
                        else if(title.equals("home")){
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Home()).commit();
                        }
                        else if(title.equals("search")){

                        }
                        return true;
                    }
                }
        );


    }
}
