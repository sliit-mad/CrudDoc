package com.example.doc99app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DocAdmin extends AppCompatActivity {
    Databasehelper myDb;
    EditText docId,Phone;
    Button btnviewAll,btnUpdate,btnDelete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_admin);

        myDb=new Databasehelper(this);
        docId=(EditText)findViewById(R.id.editText2_dId);
        Phone=(EditText)findViewById(R.id.editText_phone);
        btnviewAll=(Button)findViewById(R.id.button_view);
        btnUpdate=(Button)findViewById(R.id.button_update);
        btnDelete=(Button)findViewById(R.id.button2_delete);
        viewAll();
        UpdateDoc();
        deleteDoc();
    }

    public void viewAll(){
        btnviewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Cursor res=myDb.getAllData();
                        if(res.getCount() == 0){
                            //show message
                            showMessage("Error","Data not Found");
                            return;
                        }
                        StringBuffer buffer=new StringBuffer();
                        while (res.moveToNext()){
                            buffer.append("ID :"+ res.getString(0)+"\n");
                            buffer.append("Name : Dr."+res.getString(1)+"\n");
                            buffer.append("Medical Id :"+ res.getString(2)+"\n");
                            buffer.append("Assist Email :"+ res.getString(3)+"\n");
                            buffer.append("Assist Contact :"+ res.getString(4)+"\n\n");
                        }
                        //show message
                        showMessage("Data",buffer.toString());
                    }
                }
        );
    }

    public void deleteDoc(){
        btnDelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        setContentView(R.layout.activity_delete_doc_profile);
                        dltDocProf();
                    }
                }
        );
    }

    public void dltDocProf(){
        Intent i=new Intent(getApplicationContext(),DeleteDocProfile.class);
        startActivity(i);
    }

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    public void UpdateDoc(){
        btnUpdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean isUpdate=myDb.updateData(docId.getText().toString(),
                                Phone.getText().toString());
                        if(isUpdate == true)
                            Toast.makeText(DocAdmin.this,"Doc Profile is Updated",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(DocAdmin.this,"Doc Profile is not Update",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
}
