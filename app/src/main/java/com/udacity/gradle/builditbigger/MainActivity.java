package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;


public class MainActivity extends AppCompatActivity implements EndpointsAsyncTask.GetJokeTaskListener {
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
        jokeTask.setListener(this);
        jokeTask.execute(this);
    }


    @Override
    public void onComplete(String joke, Exception e) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                spinner.setVisibility(View.INVISIBLE);
            }
        });

    }
}
