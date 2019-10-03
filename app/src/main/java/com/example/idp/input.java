package com.example.idp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class input extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String type=" ";
    Button submit;
    EditText glucose,date,time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        glucose=findViewById(R.id.glucose);
        date=findViewById(R.id.date);
        time=findViewById(R.id.time);
        submit=findViewById(R.id.submit);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Random");
        categories.add("Fasting");
        categories.add("After meal");


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String glu;
                String dat;
                String tim;
                glu=glucose.getText().toString();
                dat=date.getText().toString();
                tim=time.getText().toString();

                int check = 0;
                try{
                    check=Integer.parseInt(glu);
                }
                catch (Exception ex)
                {
                    Toast.makeText(getApplicationContext(),"Enter a number",Toast.LENGTH_LONG).show();
                }
                if(check>0 && check<=60)
                {
                    FirebaseDatabase database;
                    DatabaseReference myRef;
                    //String nam=name.getText().toString();
                    //String ag=age.getText().toString();

                    history s1=new history(glu,dat,tim,type);

                    database = FirebaseDatabase.getInstance();
                    myRef = FirebaseDatabase.getInstance().getReference("history");

                    String key=myRef.push().getKey();

                    //myRef.setValue(s1);
                    myRef.child(key).setValue(s1);
                    Toast.makeText(getApplicationContext(),"Saved onto Database",Toast.LENGTH_LONG).show();

                    Intent intent=new Intent(input.this,MainActivity.class);
                    startActivity(intent);

                }
                else if(dat.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Enter a date",Toast.LENGTH_LONG).show();
                }
                else if(tim.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Enter a time",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Enter a number between 0 to 60",Toast.LENGTH_LONG).show();
                }






            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String item = adapterView.getItemAtPosition(i).toString();
        type=item;

        // Showing selected spinner item
       // Toast.makeText(adapterView.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
