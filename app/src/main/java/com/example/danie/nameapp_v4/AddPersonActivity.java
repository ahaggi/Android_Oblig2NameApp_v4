package com.example.danie.nameapp_v4;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class AddPersonActivity extends AppCompatActivity {

    private static final int SELECT_PICTURE = 1;
    private Uri selectedImageUri;

    public void setSelectedImageUri(Uri u){
        selectedImageUri = u;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);


    }

    public void onClickAddPicture(View v) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
        startActivityForResult(intent, SELECT_PICTURE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                selectedImageUri = data.getData();
                ImageView iv = (ImageView) findViewById(R.id.imageViewAddPerson);
                iv.setImageURI(selectedImageUri);

            }
        }
    }

    public void onClickSave(View v) {
        EditText et = (EditText) findViewById(R.id.editText2);

        if (!et.getText().toString().equals("") && selectedImageUri != null) {
            ((GlobalClass) this.getApplication()).addNameUri(et.getText().toString(), selectedImageUri);

            Toast.makeText(AddPersonActivity.this, "Saved", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, NameActivity.class));
            finish();
        } else {
            Toast.makeText(AddPersonActivity.this, "Missing name or image", Toast.LENGTH_SHORT).show();
        }

    }

    public void onClickCancel(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("Cancel");
        builder.setMessage("Are you sure?");
        builder.setPositiveButton("Confirm",
                (dialog, which) -> finish());
        builder.setNegativeButton(android.R.string.cancel, (dialog, which) -> Toast.makeText(AddPersonActivity.this, "Canceled the cancel", Toast.LENGTH_SHORT).show());

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
