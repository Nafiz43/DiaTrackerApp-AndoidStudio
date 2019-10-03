package com.example.idp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class tracker extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracker);

        final ListView list;
        final ArrayAdapter<String> adapter;

        final List<String> slist;

        slist=new ArrayList<>();

        list=findViewById(R.id.list);

        adapter=new ArrayAdapter<String>(this,R.layout.sample_layout,R.id.t1,slist);



        DatabaseReference reference;
        reference= FirebaseDatabase.getInstance().getReference("history");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                slist.clear();

                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                {
                    history h=dataSnapshot1.getValue(history.class);
                    //s1=s1+"Name :"+student.getName().toString()+"\n"+"ID :"+student.getAge()+"\n";
                    slist.add("   "+h.dat+"  "+h.tim+" "+"       "+h.type+"               "+h.glu+"\n");



                    //Toast.makeText(getApplicationContext(),"entering", Toast.LENGTH_LONG).show();

                }

               // textView.setText(s1);
                //ists.reverse(list)
                list.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
