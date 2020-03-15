package rishabhgupta.appdemo.pizzazia;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                progressDialog=new ProgressDialog(MainActivity.this);
                progressDialog.setMessage("Please Wait....");
                progressDialog.show();
                Intent intent=new Intent(MainActivity.this , LoginActivity.class);
                startActivity(intent);
                finish();
            }
        },2000);

    }
}
