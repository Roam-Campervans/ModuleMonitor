package com.example.teslamodulemonitor;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //need an activity that allows adding a pack
    public void goToAddPack(View view) {
        Intent intent = new Intent(this, /*TODO: add class to go to*/);
    }


    //need an activity that shows pack module and cell values


}