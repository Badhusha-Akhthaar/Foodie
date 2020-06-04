package com.jiffy.foodie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;

public class EditandPost extends AppCompatActivity {
    ImageView postImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editand_post);

        Intent postIntent = getIntent();
        String imagePath = postIntent.getStringExtra("IMAGE_URI");
        Uri imageUri = Uri.parse(imagePath);
        postImage = findViewById(R.id.postImage);

        postImage.setImageURI(imageUri);

    }
}
