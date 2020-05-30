package com.jiffy.foodie;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import de.hdodenhof.circleimageview.CircleImageView;

public class User_Dashboard extends AppCompatActivity {
    FirebaseAuth mUserAuth;
    CircleImageView circleImageView;
    NavigationView navigationView;
    ImageButton navImage;
    DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__dashboard);
        mUserAuth = FirebaseAuth.getInstance();
        navigationView = findViewById(R.id.navigation_view);
        Toast.makeText(User_Dashboard.this,mUserAuth.getUid(),Toast.LENGTH_SHORT).show();
        circleImageView = findViewById(R.id.profile_image);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        navImage = findViewById(R.id.navImg);
//        navImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                drawer.openDrawer(Gravity.LEFT);
//            }
//        });


    }
    public void openCDrawer(View v){
        drawer.openDrawer(Gravity.LEFT);
    }
}
