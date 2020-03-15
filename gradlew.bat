package rishabhgupta.appdemo.databaseapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.LinkedHashMap;

public class MainActivity extends AppCompatActivity {
    static LinkedHashMap<String,String> map = new LinkedHashMap<String, String>();
    Database myDB;
    public EditText username,password,username_verify,password_verify;
    public Button btn_Signup;
    public Button btn_Login,btn_Verify;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDB = new Database(this);
        username = findViewById(R.id.editText_name);
        password = findViewById(R.id.editText_pwd);
        username_verify = findViewById(R.id.editText_name1);
        password_verify = findViewById(R.id.editText_pwd1);
        btn_Signup = findViewById(R.id.button_signup);
        btn_Login = findViewById(R.id.button_login);
        btn_Verify = findViewById(R.id.button_verify);
        AddData();
        viewAllData();
    }
    public void AddData() {
        btn_Signup.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean isInserted = myDB.insertData(username.getText().toString(),
                                password.getText().toString());
                        if(isInserted) {
                            Toast.makeText(MainActivity.this,"Data Inserted",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(MainActivity.this,"Data not Inserted",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }

    public void viewAllData() {
        btn_Login.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public