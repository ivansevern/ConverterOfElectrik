package com.example.ConverterOfElectrik;

import static com.example.ConverterOfElectrik.R.id;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button_P = (Button) findViewById(id.button_P);
        Button button_A = (Button) findViewById(R.id.button_A);

        button_P.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPower = new Intent(MainActivity.this, PowerActivity.class);
                startActivity(intentPower);
            }
        });

        button_A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAmp = new Intent(MainActivity.this, com.example.ConverterOfElectrik.AmperActivity.class);
                startActivity(intentAmp);
            }
        });
    }
}
