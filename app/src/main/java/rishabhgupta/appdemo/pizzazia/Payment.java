package rishabhgupta.appdemo.pizzazia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;

public class Payment extends AppCompatActivity {
    LoginActivity isSkip = new LoginActivity();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(isSkip.verifySkip()) {
            Intent signupIntent = new Intent(Payment.this,Signup.class);
            startActivity(signupIntent);
            finish();
        }
        else {

            if (Build.VERSION.SDK_INT > 16) {
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                        WindowManager.LayoutParams.FLAG_FULLSCREEN);
            }
            setContentView(R.layout.activity_payment);
        }
    }
}
