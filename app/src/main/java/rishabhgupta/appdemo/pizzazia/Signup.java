package rishabhgupta.appdemo.pizzazia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;


public class Signup extends AppCompatActivity {
    Database myDB;

    public EditText username;
    public EditText name;
    public EditText email;
    public EditText password;
    public EditText confirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myDB = new Database(this);
        setContentView(R.layout.activity_signup);

        if (Build.VERSION.SDK_INT > 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        name = findViewById(R.id.editTextName);
        email = findViewById(R.id.editTextEmail);
        username = findViewById(R.id.editTextUsername);
        password = findViewById(R.id.editTextPassword);
        confirmPassword = findViewById(R.id.editTextConfirmPassword);
    }

    public void dataInsert(View view) {
        String emails = email.getText().toString();
        String names = name.getText().toString();
        String usernameValue = username.getText().toString();
        String passwordValue = password.getText().toString();
        String confirmPasswordValue = confirmPassword.getText().toString();
        if(emails.equals("")||names.equals("")||usernameValue.equals("") || passwordValue.equals("") || confirmPasswordValue.equals("")) {
            Toast.makeText(Signup.this,"Fill all the Blanks",Toast.LENGTH_SHORT).show();
        }
        else if(!passwordValue.equals(confirmPasswordValue)) {
            password.setText("");
            confirmPassword.setText("");
            Toast.makeText(Signup.this,"Enter same password in both fields",Toast.LENGTH_SHORT).show();
        }
        else if(!emails.endsWith("@gmail.com") && !emails.endsWith("@yahoo.com") && !emails.endsWith("@outlook.com")) {
            Toast.makeText(Signup.this,"Enter valid E-mail id",Toast.LENGTH_SHORT).show();
        }
        else {
            LoginActivity.skip=false;
            myDB.insertData(usernameValue, passwordValue);
            Intent intentPizzazia = new Intent(Signup.this,Pizzazia.class);
            startActivity(intentPizzazia);
            finish();
        }
    }
}
