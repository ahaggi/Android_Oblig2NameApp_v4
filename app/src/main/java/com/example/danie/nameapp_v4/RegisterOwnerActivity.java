package com.example.danie.nameapp_v4;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class RegisterOwnerActivity extends AppCompatActivity {

    SharedPreferences.OnSharedPreferenceChangeListener listener = (prefs, key) -> {
        TextView tv = findViewById(R.id.textView8);
        tv.setText(prefs.getString("owner_name", "Default"));
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_owner);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPref.registerOnSharedPreferenceChangeListener(listener);

        TextView tv = findViewById(R.id.textView8);

        setValues();
        setPicture();

    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPref.unregisterOnSharedPreferenceChangeListener(listener);
    }

    public void setValues() {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String ownerName = sharedPref.getString("owner_name", "Default");

        TextView tv = findViewById(R.id.textView8);

        if (ownerName.equals("Default")) {
            tv.setText("No owner name is set");
        } else {
            tv.setText(ownerName);
        }
    }

    private void setPicture()  { // throws RuntimeException,InterruptedException

        /**

         new Thread(new Runnable() {
            public void run() {
                ImageView iv = findViewById(R.id.imageView2);

                FileInputStream fis = null;
                try {
                    fis = openFileInput("ownerPicture");
                    Log.v("***************", ""+Thread.currentThread().getName());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                final Bitmap bilde = BitmapFactory.decodeStream(fis);

                iv.postDelayed(new Runnable() {
                    public void run() {
                        iv.setImageBitmap(bilde);
                    }
                }, 5000);
            }
        }).start();
         */

        InitTask _initTask = new InitTask();
        _initTask.execute();
    }

    public void onClickDone(View v) {
        finish();
    }



    protected class InitTask extends AsyncTask<Void, Integer, FileInputStream> {
        ImageView iv = findViewById(R.id.imageView2);
        FileInputStream fis = null;

        @Override
        protected FileInputStream doInBackground(Void... params) {

            try {
                fis = openFileInput("ownerPicture");
                Thread.sleep(3000);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return fis;
        }

        // -- called from the publish progress
        // -- notice that the datatype of the second param gets passed to this method
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            Log.i("makemachine", "onProgressUpdate(): " + String.valueOf(values[0]));

        }

        // -- called if the cancel button is pressed
        @Override
        protected void onCancelled() {
            super.onCancelled();
            Log.i("makemachine", "onCancelled()");

        }

        // -- called as soon as doInBackground method completes
        // -- notice that the third param gets passed to this method
        @Override
        protected void onPostExecute(FileInputStream fis) {
            super.onPostExecute(fis);
            iv.setImageBitmap(BitmapFactory.decodeStream(fis));

            Log.i("makemachine", "onPostExecute()");

        }
    }
}
