package com.example.talkges;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {

    public EditText txt1;
    public EditText txt2;
    public Button btn2;
    DatabaseHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getActionBar().setTitle("LOGIN TO TALKFIN");
        btn2=findViewById(R.id.btn2);
        txt1=findViewById(R.id.edittxt1);
        txt2=findViewById(R.id.edittxt2);


        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                next();
            }
        });
    }

    private void next() {

        if(txt1.getText().toString().equals("Admin") || txt1.getText().toString().equals("admin") && txt2.getText().toString().equals("1234")) {
            Intent intent = new Intent(this, Main2Activity.class);
            startActivity(intent);
        }
        else
            Toast.makeText(LoginActivity.this, "Please Enter Correct Value", Toast.LENGTH_SHORT).show();
        /*
        boolean isExist = mydb.checkUserExist(txt1.getText().toString());

        if(isExist){
            Intent intent = new Intent(this, Main2Activity.class);
            startActivity(intent);
        } else {
            txt1.setText(null);
            txt2.setText(null);
            Toast.makeText(LoginActivity.this, "Login failed. Invalid username or password.", Toast.LENGTH_SHORT).show();
        }

         */
    }



    public void nextActivity(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}
