package com.bilal.jokedisplay;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {

    public static final String JOKE = "joke";
    TextView tvJoke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);
        tvJoke = findViewById(R.id.tv_joke);
        if (getIntent().getExtras().containsKey(JOKE))
            tvJoke.setText(getIntent().getExtras().getString(JOKE));
    }
}
