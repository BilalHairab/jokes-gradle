package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;


public class MainActivity extends AppCompatActivity{
    private ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = findViewById(R.id.progressBar);
    }

    public void tellJoke(View view) {
        spinner.setVisibility(View.VISIBLE);
        EndpointsAsyncTask jokeTask = new EndpointsAsyncTask();
        jokeTask.execute(new Pair<Context, ProgressBar>(this, spinner));
    }
}
