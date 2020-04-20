package com.deva.sms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    final private int REQUEST_SEND_SMS = 123;
    final private int REQUEST_RECEIVE_SMS = 321;
    SMSReceiver smsReceiver = new SMSReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.SEND_SMS
            }, REQUEST_SEND_SMS);
        }

        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECEIVE_SMS)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.RECEIVE_SMS
            }, REQUEST_RECEIVE_SMS);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
//        IntentFilter filter = new IntentFilter(Telephony.Sms.Intents.SMS_RECEIVED_ACTION);
//        registerReceiver(smsReceiver, filter);
    }


    @Override
    protected void onStop() {
        super.onStop();
//        unregisterReceiver(smsReceiver);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_SEND_SMS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(MainActivity.this, "Permission Granted : SEND SMS", Toast.LENGTH_SHORT)
                            .show();
                } else {
                    Toast.makeText(MainActivity.this, "Permission Denied : SEND SMS", Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            case REQUEST_RECEIVE_SMS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(MainActivity.this, "Permission Granted : RECEIVE SMS", Toast.LENGTH_SHORT)
                            .show();
                } else {
                    Toast.makeText(MainActivity.this, "Permission Denied : RECEIVE SMS", Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    public void onClick(View v){
        //---the "phone number" of your emulator should be 5554---
        sendSMS("085721257947", "Hello my friends!");

        //Send SMS using intent
//        Intent i = new
//                Intent(android.content.Intent.ACTION_VIEW);
//        i.putExtra("address", "5556; 5558; 5560");
//        i.putExtra("sms_body", "Hello my friends!");
//        i.setType("vnd.android-dir/mms-sms");
//        startActivity(i);
    }

    private void sendSMS(String phoneNumber, String message){
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, null, null);
    }
}
