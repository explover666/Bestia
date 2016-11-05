package com.example.max.bestia.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.max.bestia.Database.DatabaseHelper;
import com.example.max.bestia.Fragments.Posters;
import com.example.max.bestia.R;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    ImageView poster_1, poster_2,iv_Poster1, iv_Poster2, ligt, stick;
    private Toolbar toolbar;
    private DatabaseHelper dbHelper;
    int poster1=1;
    int poster2=2;
    int poster3=3;
    int poster4=4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        poster_1 = (ImageView) findViewById(R.id.poster_1);
        poster_2 = (ImageView) findViewById(R.id.poster_2);
        iv_Poster1= (ImageView) findViewById(R.id.iv_Poster1);
        iv_Poster2= (ImageView) findViewById(R.id.iv_Poster2);
        ligt= (ImageView) findViewById(R.id.light);
        stick= (ImageView) findViewById(R.id.stick);

        poster_1.setOnClickListener(this);
        poster_2.setOnClickListener(this);

        Animation scale = AnimationUtils.loadAnimation(this, R.anim.scale);
        ligt.startAnimation(scale);

        Animation rotation2 = AnimationUtils.loadAnimation(this, R.anim.rotate);
        stick.startAnimation(rotation2);



        dbHelper = new DatabaseHelper(this);
        iv_Poster1.setImageBitmap(dbHelper.PutImage(poster3));
        iv_Poster2.setImageBitmap(dbHelper.PutImage(poster4));
        poster_1.setImageBitmap(dbHelper.PutImage(poster1));
        poster_2.setImageBitmap(dbHelper.PutImage(poster2));

    }


    @Override

    public void onClick(View v) {
        Intent intent = new Intent(this, Posters.class);
        switch (v.getId()) {
            case R.id.poster_1:
                intent.putExtra("poster", 0);
                startActivity(intent);
                break;
            case R.id.poster_2:
                intent.putExtra("poster", 1);
                startActivity(intent);
                break;
        }
    }
}
