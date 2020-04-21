package rishabhgupta.appdemo.pizzazia;

import androidx.appcompat.app.AppCompatActivity;

//import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
//import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import java.util.LinkedHashMap;
//import java.util.Map;
import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
    Database myDB;
    private EditText username;
    private EditText password;
    public static boolean skip=true;
    //ProgressDialog pD;
    public static LinkedHashMap<String,String> map = new LinkedHashMap<>();

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        skip=true;
        setContentView(R.layout.activity_login);

        if (Build.VERSION.SDK_INT > 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        myDB = new Database(this);
        username = findViewById(R.id.editText_username);
        password = findViewById(R.id.editText_password);
    }

    public void Login(View view) {
        map = myDB.verifyData();
        String usernameValue = username.getText().toString();
        String passwordValue = password.getText().toString();
        if(usernameValue.equals("") || passwordValue.equals("")) {
            Toast.makeText(LoginActivity.this,"Fill all the Blanks",Toast.LENGTH_SHORT).show();
        }
        else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if(map.containsKey(usernameValue) && Objects.equals(map.get(usernameValue), passwordValue)) {
                Toast.makeText(LoginActivity.this,"Successfully Login!!",Toast.LENGTH_SHORT).show();
                Intent intentLogin = new Intent(LoginActivity.this,Pizzazia.class);
                startActivity(intentLogin);
                skip=false;
            }
            else {
                Toast.makeText(LoginActivity.this,"Invalid Username or Password",Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void SignUp(View view) {
        skip=false;
        Intent intentSignup=new Intent(LoginActivity.this , Signup.class);
        startActivity(intentSignup);
        finish();
        /*
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                pD=new ProgressDialog(LoginActivity.this);
                pD.setMessage("Please Wait....");
                pD.show();
                Intent intentSignup=new Intent(LoginActivity.this , Signup.class);
                startActivity(intentSignup);
                finish();
            }
        },1000); */
    }

    public void Skip(View view) {
        Intent intentSkip = new Intent(LoginActivity.this,Pizzazia.class);
        startActivity(intentSkip);
        finish();
/*
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                pD=new ProgressDialog(LoginActivity.this);
                pD.setMessage("Please Wait...");
                pD.show();
                Intent intentSkip = new Intent(LoginActivity.this,Pizzazia.class);
                startActivity(intentSkip);
                finish();
            }
        },2000);*/
    }

    public boolean verifySkip() {
        return skip;
    }
}
