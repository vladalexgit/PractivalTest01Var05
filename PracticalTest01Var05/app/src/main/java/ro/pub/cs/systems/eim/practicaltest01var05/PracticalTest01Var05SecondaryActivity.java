package ro.pub.cs.systems.eim.practicaltest01var05;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class PracticalTest01Var05SecondaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var05_secondary);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();

        String field1 = intent.getStringExtra("field1");
        String field2 = intent.getStringExtra("field2");
        String field3 = intent.getStringExtra("field3");
        int numChecked = intent.getIntExtra("numChecked", 0);

        TextView tv = findViewById(R.id.textView);

        boolean isWin = false;

        if((field1.equals("*") || field2.equals(field1) || field2.equals("*")) && (field3.equals(field2) || field3.equals("*") || field2.equals("*"))){
            isWin = true;
        }

        int score;

        if(numChecked == 0){
            score = 100;
        }
        else if (numChecked == 2){
            score = 50;
        } else {
            score = 0;
        }

        if(isWin){
            tv.setText("Gained: " + String.valueOf(score));
            setResult(score);
        } else {
            tv.setText("FAILED");
            setResult(0);
        }
    }
}
