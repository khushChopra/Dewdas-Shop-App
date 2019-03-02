package com.khush.dewdasshopping;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class Orders extends Fragment {

    private DatabaseReference mDatabase;
    private ListView lv;
    private ArrayList<String> myArrayList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_orders, container, false);

        lv = v.findViewById(R.id.orders);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        DatabaseReference myRef = mDatabase.child("orders");

        final ProgressDialog dialog = ProgressDialog.show(getContext(), "Dewdas Shopping",
                "Loading. Please wait...", true);
        dialog.create();

        final ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,myArrayList);

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String order = dataSnapshot.getValue(String.class);
                myArrayList.add(order);
                myAdapter.notifyDataSetChanged();

                dialog.dismiss();

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Toast.makeText(getContext(), "Reload this page for fresh info", Toast.LENGTH_SHORT).show();

                dialog.dismiss();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        lv.setAdapter(myAdapter);



        return v;
    }

}
