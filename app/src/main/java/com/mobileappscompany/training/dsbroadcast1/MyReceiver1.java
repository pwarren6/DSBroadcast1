package com.mobileappscompany.training.dsbroadcast1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver1 extends BroadcastReceiver {

    public static final String INCOMING = "com.mobileappscompany.broadcaststatic2";

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        MainActivity mAct = ((BroadcastApp)context.getApplicationContext()).mAct;
        mAct.eStatic.setText(intent.getStringExtra("messageStatic"));
        Toast.makeText(context, intent.getExtras().getString("messageStatic"), Toast.LENGTH_SHORT).show();
    }
}
