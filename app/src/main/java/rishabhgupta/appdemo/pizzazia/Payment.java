package rishabhgupta.appdemo.pizzazia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class Payment extends AppCompatActivity {
    LoginActivity isSkip = new LoginActivity();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(isSkip.verifySkip()) {
            isSkip.skip = false;
            Intent signupIntent = new Intent(Payment.this,Signup.class);
            startActivity(signupIntent);
            setContentView(R.layout.activity_payment);
        }
        else {
            setContentView(R.layout.activity_payment);
        }
    }
}
