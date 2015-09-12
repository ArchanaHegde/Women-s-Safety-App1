package com.example.intern.firstpage;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;

import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;


public  class MainActivity extends AppCompatActivity  {

    private File imageFile;
    LocationManager locationManager;
    boolean gps_status = false, agps_status = false;


    Button button;
    SharedPreferences sharedPreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Phone = "phoneKey";
    public static final String Message = "messageKey";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


     //   if (gps_status)
       /* {
         locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        }

       if (agps_status)
            {
               locationManager.requestLocationUpdates(
                     LocationManager.NETWORK_PROVIDER, 0, 0, this);
        } */

        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
    }


        @Override
        public boolean onCreateOptionsMenu (Menu menu){
            // Inflate the menu items for use in the action bar
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_main, menu);
            return super.onCreateOptionsMenu(menu);
        }

        @Override
        public boolean onOptionsItemSelected (MenuItem item){
            // Handle presses on the action bar items
            switch (item.getItemId()) {

                case R.id.action_settings:
                    //  Toast.makeText(this, "nestoria", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(this, settingactivity.class);
                    startActivity(intent);
                    return true;
                default:
                    return super.onOptionsItemSelected(item);
            }
        }

    public void sendMessage(View view) {
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(sharedPreferences.getString(Phone, "9036325688"), null, sharedPreferences.getString(Message, "default msg"), null, null);
        Toast.makeText(getApplicationContext(), "SMS sent.", Toast.LENGTH_LONG).show();

        //button = (Button) findViewById(R.id.btn);

    }
    public void process(View view){
        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        imageFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),"test.jpg");



        Uri tempuri= Uri.fromFile(imageFile);

        intent.putExtra(MediaStore.EXTRA_OUTPUT,tempuri);
        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
        startActivityForResult(intent, 0);


    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0){
            switch (resultCode){
                case AppCompatActivity.RESULT_OK:
                    if(imageFile.exists())
                    {
                        Toast.makeText(this, "The file was saved at "+imageFile.getAbsolutePath(),Toast.LENGTH_LONG).show();
                    }

                    else
                    {
                        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case AppCompatActivity.RESULT_CANCELED:

                    break;
                default:
                    break;
            }
        }
    }

}



