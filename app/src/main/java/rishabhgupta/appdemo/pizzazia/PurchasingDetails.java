package rishabhgupta.appdemo.pizzazia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedHashMap;
import java.util.Map;

public class PurchasingDetails extends AppCompatActivity {

    private TextView id1;
    private TextView id11;
    private TextView id2;
    private TextView id22;
    private TextView id3;
    private TextView id33;
    private TextView id4;
    private TextView id44;
    private TextView id55;
    private TextView id66;
    private boolean table_flg = false;
    public static LinkedHashMap<String,Integer> oD,map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT > 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }

        setContentView(R.layout.activity_purchasing_details);

        oD = Pizzazia.details(1);
        map = Pizzazia.details(0);
        id1 = findViewById(R.id.id1);
        id11 = findViewById(R.id.id11);
/**
        String tempString;
        int idCount=1,amt,total=0;
        for(Map.Entry<String,Integer> entry : oD.entrySet()) {
            String key = entry.getKey();
            //amt = map.get(key)*entry.getValue();
            //tempString = String.format("%d X %s",entry.getValue(),key);

            id1.setText(key);
            id11.setText(Integer.toString(map.get(key)));
            break;
        }
 */

        id2 = findViewById(R.id.id2);
        id22 = findViewById(R.id.id22);
        id3 = findViewById(R.id.id3);
        id33 = findViewById(R.id.id33);
        id4 = findViewById(R.id.id4);
        id44 = findViewById(R.id.id44);
        id55 = findViewById(R.id.id55);
        id66 = findViewById(R.id.id66);

        String tempString;
        int idCount=1,amt;
        float total=0;
        try {
            for (Map.Entry<String, Integer> entry : oD.entrySet()) {
                String key = entry.getKey();
                amt = map.get(key) * entry.getValue();
                total += amt;
                tempString = String.format("%d X %s", entry.getValue(), key);
                if (idCount == 1) {
                    id1.setText(tempString);
                    id11.setText(Integer.toString(amt));
                } else if (idCount == 2) {
                    id2.setText(tempString);
                    id22.setText(Integer.toString(amt));
                } else if (idCount == 3) {
                    id3.setText(tempString);
                    id33.setText(Integer.toString(amt));
                } else {
                    id4.setText(tempString);
                    id44.setText(Integer.toString(amt));
                }
                idCount++;
            }
            float gst = total*0.01f;
            total+=gst;
            id55.setText(Float.toString(gst));
            id66.setText(Float.toString(total));
        }
        catch(NullPointerException e) {
            e.printStackTrace();
        }

    }

    public void collapseTable(View view) {
        TableLayout table = findViewById(R.id.table);
        Button switchBtn = findViewById(R.id.switchBtn);

        // setColumnCollapsed(int columnIndex, boolean isCollapsed)
        table.setColumnCollapsed(1, table_flg);

        if (table_flg) {
            // Close
            table_flg = false;
            switchBtn.setText("Show Detail");
        } else {
            // Open
            table_flg = true;
            switchBtn.setText("Hide Detail");
        }
    }
    public void placeOrder(View view) {
        Intent in = new Intent(PurchasingDetails.this,Payment.class);
        startActivity(in);
    }
}
