package com.example.ex1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button Btn1;
    Button Btn2;
    int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Btn1=findViewById(R.id.btn1);
        Btn2=findViewById(R.id.btn2);
        Btn1.setText("눌러봐");
        Btn2.setText(R.string.message3);
        Btn1.setOnClickListener(this::onClick);
        Btn2.setOnClickListener(this::onClick);
    }

    public void onClick(View v){
        if(v==Btn1)
        {
            i++;
            if(i%2==1)
                Btn1.setText("눌렀냐?");
            else
                Btn1.setText("눌러봐");
        }
        else if(v==Btn2)
        {
            Intent intent=new Intent(this, MainActivity2.class);
            startActivity(intent);
        }
    }
}