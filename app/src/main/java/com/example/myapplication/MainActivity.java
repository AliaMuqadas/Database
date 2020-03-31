package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper mydb;
    EditText editans,editans1,editans2,editans3;
    Button btnadd,btnview,btnupdate,btndelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb= new DatabaseHelper(this);
        editans=(EditText)findViewById(R.id.editans);
        editans1=(EditText)findViewById(R.id.editans1);
        editans2=(EditText)findViewById(R.id.editans2);
        editans3=(EditText)findViewById(R.id.editans3);
        btnadd=(Button) findViewById(R.id.btnadd);
        btnview=(Button) findViewById(R.id.btnview);
        btnupdate=(Button) findViewById(R.id.btnupdate);
        btndelete=(Button) findViewById(R.id.btndelete);
        ADDdata();
        viewall();
        updatedata();
        Deletesata();
    }
    public void Deletesata(){
        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer deletedrows=mydb.deleteData(editans3.getText().toString());
                if (deletedrows >0)
                    Toast.makeText(MainActivity.this,"Data Deleted",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this,"Data not deleted",Toast.LENGTH_LONG).show();


            }
        });
    }
    public void updatedata(){
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               boolean isupdate=mydb.updateData(editans3.getText().toString(),editans.getText().toString(),editans1.getText().toString(),editans2.getText().toString());
           if (isupdate==true)
               Toast.makeText(MainActivity.this,"Data Updated",Toast.LENGTH_LONG).show();
           else
               Toast.makeText(MainActivity.this,"Data not Updated",Toast.LENGTH_LONG).show();

            }
        });
    }
    public void ADDdata(){
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              boolean isinserted= mydb.insertData(editans.getText().toString(),editans1.getText().toString(),editans2.getText().toString());
           if (isinserted==true)
               Toast.makeText(MainActivity.this,"Data Inserted",Toast.LENGTH_LONG).show();
           else
               Toast.makeText(MainActivity.this,"Data not Inserted",Toast.LENGTH_LONG).show();
            }
        });
    }
    public void viewall(){
        btnview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res=mydb.getAllData();
                if (res.getCount()==0){
                    //show message
                    showmesaage("ERROR","NOTHING FOUND");
                    return;
                }

                StringBuffer buffer=new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("ID: "+res.getString(0)+"\n");
                    buffer.append("NAME: "+res.getString(1)+"\n");
                    buffer.append("SURNAME: "+res.getString(2)+"\n");
                    buffer.append("MARKS: "+res.getString(3)+"\n\n");
                }
                //show all data
                showmesaage("Data ",buffer.toString());

            }
        });
    }
    public  void showmesaage(String title,String message){
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
