package ro.pub.cs.systems.eim.practicaltest01var05;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    static int theScore = 120;
    private IntentFilter intentFilter = new IntentFilter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var05_main);

        intentFilter.addAction("actiune");

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey("score")) {
                theScore = savedInstanceState.getInt("score");
            } else {
                theScore = 0;
            }
        } else {
            theScore = 0;
        }

        final Button btnPlay = findViewById(R.id.button);

        final CheckBox cb1 = findViewById(R.id.checkBox);
        final CheckBox cb2 = findViewById(R.id.checkBox2);
        final CheckBox cb3 = findViewById(R.id.checkBox3);

        final EditText et1 = findViewById(R.id.editText3);
        final EditText et2 = findViewById(R.id.editText4);
        final EditText et3 = findViewById(R.id.editText5);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Random rn = new Random();

                int numChecked = 0;

                if(!cb1.isChecked()){
                    int randomNum = rn.nextInt(4 + 1);

                    if(randomNum == 0){
                        et1.setText("*");
                    } else {

                        et1.setText(String.valueOf(randomNum));
                    }
                } else {
                    ++numChecked;
                }
                if(!cb2.isChecked()){

                    int randomNum = rn.nextInt(4 + 1);

                    if(randomNum == 0){
                        et2.setText("*");
                    } else {

                        et2.setText(String.valueOf(randomNum));
                    }
                } else {
                    ++numChecked;
                }
                if(!cb3.isChecked()){

                    int randomNum = rn.nextInt(4 + 1);

                    if(randomNum == 0){
                        et3.setText("*");
                    } else {
                        et3.setText(String.valueOf(randomNum));
                    }
                } else {
                    ++numChecked;
                }

                Toast toast=Toast.makeText(getApplicationContext(),"Numbers are : " + et1.getText() + " " + et2.getText() + " " + et3.getText(),Toast.LENGTH_SHORT);
                toast.show();

                String field1 = et1.getText().toString();
                String field2 = et2.getText().toString();
                String field3 = et3.getText().toString();

                Intent intent = new Intent(getApplicationContext(), PracticalTest01Var05SecondaryActivity.class);

                intent.putExtra("field1", field1);
                intent.putExtra("field2", field2);
                intent.putExtra("field3", field3);
                intent.putExtra("numChecked", numChecked);

                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 1) {
            theScore += resultCode;
            Toast.makeText(this, "Score: " + theScore, Toast.LENGTH_SHORT).show();
        }

        if (theScore > 100) {
            Intent intent2 = new Intent(getApplicationContext(), PracticalTest01Var05Service.class);
            intent2.putExtra("score", theScore);
            getApplicationContext().startService(intent2);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("score", theScore);
        super.onSaveInstanceState(savedInstanceState);
    }

    private MessageBroadcastReceiver messageBroadcastReceiver = new MessageBroadcastReceiver();
    private class MessageBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("[VICTORY]", intent.getStringExtra("score"));
            Toast.makeText(getApplicationContext(), "Score: " + intent.getStringExtra("score"), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(messageBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        unregisterReceiver(messageBroadcastReceiver);
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        Intent intent = new Intent(this, PracticalTest01Var05Service.class);
        stopService(intent);
        super.onDestroy();
    }

}
