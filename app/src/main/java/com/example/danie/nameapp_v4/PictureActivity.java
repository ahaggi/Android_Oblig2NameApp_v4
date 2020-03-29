package com.example.danie.nameapp_v4;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class PictureActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);

        addPictures();
    }

    private void addPictures() {
        LinearLayout layout = (LinearLayout) findViewById(R.id.linearLayout3);
        final ArrayList<String> names = ((GlobalClass) this.getApplication()).getNames();

        for (int i = 0; i < names.size(); i++) {
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(new LinearLayout.LayoutParams(600, 600));
            imageView.setId(i);

            Uri uri = ((GlobalClass) this.getApplication()).getUri(names.get(i));

            imageView.setImageURI(uri);

            final int finalI = i;
            imageView.setOnClickListener(view -> {
                Intent intent = new Intent(PictureActivity.this, ShowPersonActivity.class);
                intent.putExtra("name", names.get(finalI));
                startActivity(intent);
            });

            layout.addView(imageView);
        }
    }
}
