package com.mobileappscompany.training.dsbroadcast1;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    BroadcastReceiver dynaTxtReceiver;
    Button sBroadcast, dBroadcast;
    EditText eStatic, eDynamic;

    public static final String STATIC = "com.mobileappscompany.staticbroadcast";
    public static final String DYNAMIC = "com.mobileappscompany.dynamicbroadcast";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sBroadcast = (Button)findViewById(R.id.buttonStatic);
        dBroadcast = (Button)findViewById(R.id.buttonDynamic);
        eStatic = (EditText)findViewById(R.id.editStatic);
        eDynamic = (EditText)findViewById(R.id.editDynamic);

        dBroadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendDynamic = new Intent(DYNAMIC);
                sendDynamic.putExtra("messageDynamic", eDynamic.getText().toString());
                sendDynamic.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
                sendBroadcast(sendDynamic);
            }
        });
        sBroadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendStatic = new Intent(STATIC);
                sendStatic.putExtra("messageStatic", eStatic.getText().toString());
                sendStatic.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
                sendBroadcast(sendStatic);
            }
        });

        Bundle obtained = getIntent().getExtras();
        if(obtained != null){
            eStatic.setText(obtained.getString("messageStatic2"));
        }

        dynaTxtReceiver = new BroadcastReceiver(){
            @Override
            public void onReceive(Context context, Intent intent) {
                Toast.makeText(context, intent.getStringExtra("messageDynamic"), Toast.LENGTH_SHORT).show();
                eDynamic.setText(intent.getStringExtra("messageDynamic2"));
                eDynamic.setText(intent.getStringExtra("messageDynamic"));
                eStatic.setText(intent.getStringExtra("messageStatic2"));
                eStatic.setText(intent.getStringExtra("messageStatic"));
            }
        };

    }
    @Override
    protected void onRestart() {
        //registerReceiver(mR1, new IntentFilter(DYNAMIC));
        super.onRestart();
        //unregisterReceiver(mR1);

    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(dynaTxtReceiver, new IntentFilter(DYNAMIC));
    }

    @Override
    protected void onPause() {
        unregisterReceiver(dynaTxtReceiver);
        super.onPause();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        //unregisterReceiver(mR1);
//        dynaTxtReceiver = new BroadcastReceiver(){
//            @Override
//            public void onReceive(Context context, Intent intent) {
//                Toast.makeText(context, intent.getStringExtra("messageDynamic"), Toast.LENGTH_SHORT).show();
//                eDynamic.setText(intent.getStringExtra("messageDynamic2"));
//                eDynamic.setText(intent.getStringExtra("messageDynamic"));
//                eStatic.setText(intent.getStringExtra("messageStatic2"));
//                eStatic.setText(intent.getStringExtra("messageStatic"));
//            }
//        };
        super.onStop();

    }
}
