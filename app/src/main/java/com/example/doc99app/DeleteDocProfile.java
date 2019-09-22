package com.example.doc99app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DeleteDocProfile extends AppCompatActivity {
    Databasehelper myDb;
    EditText DId;
    Button dltProf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_doc_profile);

        myDb = new Databasehelper(this);
        DId =(EditText)findViewById(R.id.editText_id);
        dltProf =(Button)findViewById(R.id.button_dlt);
        deleteDoctor();
    }

    public void deleteDoctor(){
        dltProf.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Integer deleteRows=myDb.deleteData(DId.getText().toString());
                        if(deleteRows > 0)
                            Toast.makeText(DeleteDocProfile.this,"Doc Profile is Deleted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(DeleteDocProfile.this,"Doc profile is not Deleted",Toast.LENGTH_LONG).show();

                        setContentView(R.layout.activity_doc_admin);
                        docAdmin();
                    }
                }
        );
    }

    public void docAdmin(){
        Intent i=new Intent(getApplicationContext(),DocAdmin.class);
        startActivity(i);
    }
}
