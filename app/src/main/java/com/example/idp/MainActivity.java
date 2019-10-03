package com.example.idp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.seismic.ShakeDetector;

public class MainActivity extends AppCompatActivity implements ShakeDetector.Listener{


    Button dia,log;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        log=findViewById(R.id.log);
        dia=findViewById(R.id.dia);

        SensorManager SM=(SensorManager)getSystemService(SENSOR_SERVICE);
        ShakeDetector SD=new ShakeDetector(this);
        SD.start(SM);

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,input.class);
                startActivity(intent);

            }
        });

        dia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,tracker.class);
                startActivity(intent);

            }
        });

    }

    @Override
    public void hearShake() {
        getWindow().getDecorView().setBackgroundColor(Color.RED);
        Toast.makeText(getApplicationContext(),"Contacting emergency number",Toast.LENGTH_LONG).show();

        Uri u = Uri.parse("tel:" + "01795950319");
        Intent i = new Intent(Intent.ACTION_DIAL, u);
        startActivity(i);
    }
}
