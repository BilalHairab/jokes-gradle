package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.util.Pair;
import android.view.View;
import android.widget.ProgressBar;

import com.bilal.jokedisplay.JokeActivity;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

import static com.bilal.jokedisplay.JokeActivity.JOKE;

class EndpointsAsyncTask extends AsyncTask<Pair<Context, ProgressBar>, Void, String> {
    private static MyApi myApiService = null;
    private Context context;
    private GetJokeTaskListener listener;
    private ProgressBar progressBar;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(Pair<Context, ProgressBar>... params) {
        if (myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }

        context = params[0].first;
        progressBar = params[0].second;
        String result = "";
        try {
            result = myApiService.getJoke().execute().getData();
            if (listener != null)
                listener.onComplete(result, null);
            return result;
        } catch (IOException e) {
            if (listener != null)
                listener.onComplete(result, e);
            return result;
        }
    }

    public void setListener(GetJokeTaskListener listener) {
        this.listener = listener;
    }

    @Override
    protected void onPostExecute(String result) {
        if (progressBar != null)
            progressBar.setVisibility(View.INVISIBLE);
        if (!result.contentEquals("")) {
            Intent intent = new Intent(context, JokeActivity.class);
            intent.putExtra(JOKE, result);
            context.startActivity(intent);
        }
    }

    public interface GetJokeTaskListener {
        void onComplete(String joke, Exception e);
    }
}