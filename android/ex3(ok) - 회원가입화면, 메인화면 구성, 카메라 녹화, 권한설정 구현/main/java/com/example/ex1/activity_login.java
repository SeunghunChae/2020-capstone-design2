package com.example.ex1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class activity_login extends AppCompatActivity {

    String[] id=new String[]{"shchae822","gogocb1","gogo822"};
    String[] pass=new String[]{"1234","abcd","5678"};
    int no_id=3;
    Button btn_login;
    TextInputEditText text_id;
    TextInputEditText text_password;
    TextView signup;
    TextView forgetpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_login=findViewById(R.id.btn_login);
        text_id=findViewById(R.id.txtid);
        text_password=findViewById(R.id.txtpass);
        signup=findViewById(R.id.register);
        forgetpass=findViewById(R.id.forgetpassword);


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("shchae822",text_id.getText().toString()+" , "+id[0].equals(text_id.getText().toString()));
                if(text_id.getText().toString().equals("") || text_password.getText().toString().equals("")) {

                    Toast t=Toast.makeText(activity_login.this, "아이디와 비밀번호를 입력해 주세요.", Toast.LENGTH_SHORT);//아이디나 비번 확인 토스트 생성
                    t.show();
                }
                else
                {
                    int i=0;
                    for(i=0;i<no_id;i++)
                    {
                        if(id[i].equals(text_id.getText().toString()))
                        {
                            if(pass[i].equals(text_password.getText().toString()))
                            {
                                Intent intent=new Intent(activity_login.this, MainActivity.class);//액티비티 전환
                                startActivity(intent);
                                break;
                            }
                            else
                            {
                                Toast t=Toast.makeText(activity_login.this, "비밀번호를 확인해주세요.", Toast.LENGTH_SHORT);//패스워드확인 토스트
                                t.show();
                                break;
                            }
                        }
                    }
                    if(i==3)
                    {
                        Toast t=Toast.makeText(activity_login.this, "등록되지 않은 아이디입니다.", Toast.LENGTH_SHORT);//등록되지 않은 아이디입니다 토스트
                        t.show();
                    }
                }
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(activity_login.this, activity_register.class);//액티비티 전환
                startActivity(intent);
            }
        });
        forgetpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(activity_login.this, activity_password.class);//액티비티 전환
                startActivity(intent);
            }
        });
    }
}