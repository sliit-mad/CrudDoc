package com.example.doc99app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Databasehelper myDb;
    EditText editName,editMid,editEmail,editContact;
    Button btnAddDoc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new Databasehelper(this);

        editName=(EditText)findViewById(R.id.editText_name);
        editMid=(EditText)findViewById(R.id.editText2_Mid);
        editEmail=(EditText)findViewById(R.id.editText3_email);
        editContact=(EditText)findViewById(R.id.editText4_contact);
        btnAddDoc=(Button)findViewById(R.id.button_add);
        addData();

    }

    public void addData(){
        btnAddDoc.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final String Name=editName.getText().toString();
                        final String MId=editMid.getText().toString();
                        final String Email=editEmail.getText().toString();
                        final String Contact=editContact.getText().toString();


                        if(Name.length()==0){
                            editName.requestFocus();
                            editName.setError("Field cannot be empty");
                        }
                        else if(!Name.matches("[a-zA-Z]+")){
                            editName.requestFocus();
                            editName.setError("Enter only alphabetical character");
                        }
                        else if(MId.length()==0){
                            editMid.requestFocus();
                            editMid.setError("Field cannot be empty");
                        }
                        else if(Email.length()==0){
                            editEmail.requestFocus();
                            editEmail.setError("Field cannot be empty");
                        }
                        else if(!Email.matches("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"+
                                "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"+"[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                                +"([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                                +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"+"([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$")){
                            editEmail.requestFocus();
                            editEmail.setError("Please enter valid email address");
                        }
                        else if(Contact.length()==0){
                            editContact.requestFocus();
                            editContact.setError("Field cannot be empty");
                        }
                        else if(Contact.length()!=10){
                            editContact.requestFocus();
                            editContact.setError("Enter Valid Contact Number");
                        }

                        else{
                            boolean isInserted=myDb.insertData(Name,MId,Email,Contact);
                            if(isInserted == true)
                                Toast.makeText(MainActivity.this,"Successfull Inserted",Toast.LENGTH_LONG).show();
                            else
                                Toast.makeText(MainActivity.this,"Data not Inserted",Toast.LENGTH_LONG).show();
                            setContentView(R.layout.activity_doc_admin);
                            docAdmin();
                        }

                    }
                }
        );
    }

    public void docAdmin(){
        Intent i=new Intent(getApplicationContext(),DocAdmin.class);
        startActivity(i);
    }
}
