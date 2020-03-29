package com.example.danie.nameapp_v4;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.provider.MediaStore;

import java.io.FileOutputStream;
import java.io.IOException;

import static android.app.Activity.RESULT_OK;

/**
 * Created by daniel on 30.01.18.
 */

public class SimpleUserPrefsFragment extends PreferenceFragment {

    private static final int SELECT_PICTURE = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.userprefs);

        Preference preferences = findPreference("owner_picture");
        preferences.setOnPreferenceClickListener (preference -> {
            Intent intent = new Intent();
            intent.setType("image/*"); //setData : fjerner eventuelle data og spesifisere hvilke data den returnerte intenten vil ha,,, returnert intent blir behandlet i onActivityResult()
            intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
            return true;
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) { //SELECT_PICTURE er for å skille mellom flere startActivityForResult
                Uri selectedImage = imageReturnedIntent.getData();
                Context c = getActivity().getApplicationContext();
                try {
                    FileOutputStream fos = c.openFileOutput("ownerPicture", c.MODE_PRIVATE); //lager et bilde med navn "ownerPicture"
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(c.getContentResolver(), selectedImage);

                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
