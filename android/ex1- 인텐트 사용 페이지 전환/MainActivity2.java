package com.example.ex1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        btn=findViewById(R.id.btn3);
        btn.setOnClickListener(this::onClick);
    }

    public void onClick(View v)
    {
        if(v==btn) {
            Intent intent=new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
}