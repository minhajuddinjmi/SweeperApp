package com.example.sweeperapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //Variable animation
    Animation topAnimation, bottomAnimation;
    TextView logo,slogan;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        //Animation
        topAnimation = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnimation = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        //Get image and logo
        image = findViewById(R.id.imageView);
        logo = findViewById(R.id.logo);
        slogan = findViewById(R.id.slogan);

        //Set Animation
        image.setAnimation(topAnimation);
        logo.setAnimation(bottomAnimation);
        slogan.setAnimation(bottomAnimation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, SignIn.class);

                Pair[] pairs = new Pair[2];
                pairs[0] = new Pair<View, String>(image, "logo_image");
                pairs[1] = new Pair<View, String>(logo, "logo_text");

                ActivityOptions options = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                     options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, pairs);
                }
                assert options != null;
                startActivity(intent, options.toBundle());
                finish();
            }
        }, 3000);
    }
}