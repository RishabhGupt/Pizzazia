package rishabhgupta.appdemo.pizzazia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import java.util.LinkedHashMap;
import java.util.Map;

public class Pizzazia extends AppCompatActivity {

    public int countMarg = 0;
    private static final int amtMarg = 150;
    private static final int amtCrudo = 125;
    private static final int amtVeggie = 250;
    private static final int amtLoaded = 255;
    public static int countCrudo = 0;
    public static int countVeggie = 0;
    public static int countLoaded = 0;
    public static int total = 0;
    private TextView set_txt;
    private TextView set_Crudo;
    private TextView set_Veggie;
    private TextView set_Loaded;
    private TextView pay;
    Button btnPayment;
    public static LinkedHashMap<String,Integer> map;
    public static LinkedHashMap<String,Integer> orderDetails ;

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         map = new LinkedHashMap<>();
        orderDetails = new LinkedHashMap<>();
        total=0;countLoaded = 0;countVeggie = 0;countCrudo = 0;
        map.put("Margherita",150);
        map.put("Crudo",125);
        map.put("Cheese Veggie",250);
        map.put("Veg OverLoaded",255);
        setContentView(R.layout.activity_pizzazia);

        if (Build.VERSION.SDK_INT > 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }


        //Intent receive = getIntent();
        set_txt = findViewById(R.id.textView13);
        set_Crudo = findViewById(R.id.textView10);
        set_Veggie = findViewById(R.id.textView11);
        set_Loaded = findViewById(R.id.textView12);
        pay = findViewById(R.id.cartValue);
        btnPayment = findViewById(R.id.buttonPayment);
        payments();
    }


    /*
        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.

            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            if (id == R.id.action_settings) {
                return true;
            }

            return super.onOptionsItemSelected(item);
        }

    */
    public static String Format(String s) {
        return String.format("%s",s);
    }
    public void setAdd_marg(View view) {

        set_txt.setText(Format(Integer.toString(++countMarg)));
        total+=amtMarg;
        orderDetails.put("Margherita",countMarg);
        setTotal(total);
    }

    public void setDel_marg(View view) {


        if (countMarg == 0) {
            Toast toast = Toast.makeText(this, R.string.toast_message, Toast.LENGTH_SHORT);
            toast.show();
        }
        else {
            set_txt.setText(Format(Integer.toString(--countMarg)));
            total-=amtMarg;
            orderDetails.put("Margherita",countMarg);
            if(countMarg==0)
                orderDetails.remove("Margherita");
            setTotal(total);
        }
        //Toast.makeText(this,R.string.totals + total,Toast.LENGTH_SHORT).show();

    }

    public void setAdd_crudo(View view) {
        total+=amtCrudo;
        set_Crudo.setText(Format(Integer.toString(++countCrudo)));
        orderDetails.put("Crudo",countCrudo);
        setTotal(total);
    }

    public void setDel_crudo(View view) {
        if (countCrudo == 0) {
            Toast toast = Toast.makeText(this, R.string.toast_message, Toast.LENGTH_SHORT);
            toast.show();
            orderDetails.remove("Crudo");
        } else {
            total -= amtCrudo;
            set_Crudo.setText(Format(Integer.toString(--countCrudo)));
            orderDetails.put("Crudo",countCrudo);
            if(countCrudo==0)
                orderDetails.remove("Crudo");
            setTotal(total);
        }
    }
    public void setAdd_veggie(View view) {
        total+=amtVeggie;
        set_Veggie.setText(Format(Integer.toString(++countVeggie)));
        orderDetails.put("Cheese Veggie",countVeggie);
        setTotal(total);
    }

    public void setDel_veggie(View view) {
        if (countVeggie == 0) {
            Toast toast = Toast.makeText(this, R.string.toast_message, Toast.LENGTH_SHORT);
            toast.show();
        } else {
            total -= amtVeggie;
            set_Veggie.setText(Format(Integer.toString(--countVeggie)));
            orderDetails.put("Cheese Veggie",countVeggie);
            if(countVeggie==0)
                orderDetails.remove("Cheese Veggie");
            setTotal(total);
        }
    }
    public void setAdd_loaded(View view) {
        total+=amtLoaded;
        set_Loaded.setText(Format(Integer.toString(++countLoaded)));
        orderDetails.put("Veg OverLoaded",countLoaded);
        setTotal(total);
    }

    public void setDel_loaded(View view) {
        if (countLoaded == 0) {
            Toast toast = Toast.makeText(this, R.string.toast_message, Toast.LENGTH_SHORT);
            toast.show();
        } else {
            total -= amtLoaded;
            set_Loaded.setText(String.format("%s",Integer.toString(--countLoaded)));
            orderDetails.put("Veg OverLoaded",countLoaded);
            if(countLoaded==0)
                orderDetails.remove("Veg OverLoaded");
            setTotal(total);
        }
    }
    public void setTotal(int total) {
        /*if (total == 0) {

            cart.setVisibility(View.INVISIBLE);
        }
        else {
            map.put("Total",total);
            cart.setVisibility(View.VISIBLE);*/
        pay.setVisibility(View.VISIBLE);
        pay.setText(String.format("Total : %s",Integer.toString(total)));
        //}
    }

   public void payments() {
        btnPayment.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (total == 0) {
                            Toast toast = Toast.makeText(Pizzazia.this, R.string.toast_message, Toast.LENGTH_SHORT);
                            toast.show();
                        }
                        else {
                            Intent in = new Intent(Pizzazia.this,PurchasingDetails.class);
                            startActivity(in);
                        }
                    }
                }
        );

    }
    public static LinkedHashMap<String,Integer> details(int x) {
        if(x==1)return orderDetails;
        return map;
    }


}


