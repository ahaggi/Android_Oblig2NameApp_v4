package com.example.danie.nameapp_v4;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();

/*
*
* getDefaultSharedPreferences() uses a default preference-file name. This default is set per application, so all activities in the same app context can access it easily as in the following example:
*
* SharedPreferences spref = PreferenceManager.getDefaultSharedPreferences(this);
*
* The alternative method - getSharedPreferences(name,mode) requires to indicate a specific preference (file) name and an operation mode (e.g. private, world_readable, etc.)
*
* context.getSharedPreferences(getDefaultSharedPreferencesName(context), Context.MODE_PRIVATE );
*
* */


        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String ownerName = sharedPref.getString("owner_name", "Default");

        if (ownerName.equals("Default")) {
            startActivity(new Intent(this, RegisterOwnerActivity.class));
            Toast.makeText(MainActivity.this, "No owner is set", Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickName(View v) {
        startActivity(new Intent(this, NameActivity.class));
    }

    public void onClickPicture(View v) {
        startActivity(new Intent(this, PictureActivity.class));
    }

    public void onClickLearning(View v) {
        startActivity(new Intent(this, LearningActivity.class));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.owner_info:
                startActivity(new Intent(this, RegisterOwnerActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
