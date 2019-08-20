package com.example.todolist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class welcome extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference myRef;
    FloatingActionButton floatbutton;
    List<Model> nm = new ArrayList<>();
    RecyclerView recyclerView;
    List<Model> retrieveDataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        recyclerView = findViewById(R.id.recycler);
        floatbutton = findViewById(R.id.floatButton);

        database = FirebaseDatabase.getInstance();
        myRef = FirebaseDatabase.getInstance().getReference("Notes");

        floatbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog();
            }
        });

    }

    private void dialog() {
        View view = LayoutInflater.from(this).inflate(R.layout.notesadd, null);
        final EditText edittitle = view.findViewById(R.id.editTitle);
        final EditText editdescription = view.findViewById(R.id.editDesc);
        Button b = view.findViewById(R.id.submit);

        final AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setView(view)
                .setCancelable(false)
                .create();

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = edittitle.getText().toString();
                String desc = editdescription.getText().toString();
                Model m = new Model();

                m.setTitle(title);
                m.setDescription(desc);

                nm.add(m);

                String id = myRef.push().getKey();
                myRef.child(id).setValue(m);


                myAdapter myAdapter = new myAdapter(nm, getApplicationContext());
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerView.setAdapter(myAdapter);
                alertDialog.dismiss();

            }
        });
        alertDialog.show();
    }


    @Override
    protected void onStart() {
        super.onStart();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                retrieveDataList.clear();
                for(DataSnapshot notesShot : dataSnapshot.getChildren()){

                    Model model = notesShot.getValue(Model.class);
                    retrieveDataList.add(model);
                }
                myAdapter myadapter =new myAdapter(retrieveDataList,getApplicationContext());
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

                recyclerView.setAdapter(myadapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}

