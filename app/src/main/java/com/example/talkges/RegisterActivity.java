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

public class RegisterActivity extends Activity {

    public EditText txt1;
    public EditText txt2;

    DatabaseHelper mydb;
    public Button add;
    public Button viewall;
    public Button delete;
    public Button update;
    public EditText txt3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getActionBar().setTitle("REGISTER TO TALKFIN");
        txt1=findViewById(R.id.edittxt1);
        txt2=findViewById(R.id.edittxt2);
        txt3=findViewById(R.id.edittxt3);

        mydb=new DatabaseHelper(this);
        add=(Button)findViewById(R.id.add);
        viewall=(Button)findViewById(R.id.viewall);
        update=(Button)findViewById(R.id.update);
        delete=(Button)findViewById(R.id.delete);

        viewall.setVisibility(View.INVISIBLE);
        update.setVisibility(View.INVISIBLE);
        delete.setVisibility(View.INVISIBLE);

        add_data();
        //view_All();
        //Update();
        //Delete();
    }

    public void nextActivity(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void Delete(){
        delete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Integer deleteRows = mydb.deleteData(txt1.getText().toString());
                        if(deleteRows>0)
                            Toast.makeText(RegisterActivity.this,"Data Deleted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(RegisterActivity.this,"Data Not Deleted",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public  void Update(){
        update.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdate=mydb.updateData(txt1.getText().toString(),txt2.getText().toString());
                        if(isUpdate==true)
                            Toast.makeText(RegisterActivity.this,"Data Updated",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(RegisterActivity.this,"Data Not Updated",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void view_All(){
        viewall.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res =mydb.getAllData();
                        if(res.getCount()==0){
                            //show message
                            showMessage("Error","Nothing Found");
                            return;
                        }
                        StringBuffer buffer =new StringBuffer();
                        while (res.moveToNext()){
                            buffer.append("Name :"+res.getString(0)+"\n");
                            buffer.append("Password :"+res.getString(1)+"\n\n");
                        }
                        //Show all data
                        showMessage("Data",buffer.toString());
                    }
                }
        );
    }

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    public void add_data(){
        add.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(txt1.getText().toString().isEmpty() && txt2.getText().toString().isEmpty()) {
                            Toast.makeText(RegisterActivity.this, "Enter Some Data", Toast.LENGTH_LONG).show();
                        }
                        else{
                            boolean isInserted = mydb.insert_data(txt1.getText().toString(), txt2.getText().toString());
                            if (isInserted == true) {
                                txt1.setText(null);
                                txt2.setText(null);
                                txt3.setText(null);
                                Toast.makeText(RegisterActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                            }
                            else
                                Toast.makeText(RegisterActivity.this, "Data Already Exists, Please Try With Different Name", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }
}
