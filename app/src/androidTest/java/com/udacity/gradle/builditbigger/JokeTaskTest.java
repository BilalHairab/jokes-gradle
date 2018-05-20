package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.text.TextUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNull;

@RunWith(AndroidJUnit4.class)
public class JokeTaskTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);
    private Context context;
    private CountDownLatch signal;
    private Exception exception;
    private String result;
    @Before
    public void init() {
        context = mActivityTestRule.getActivity();
        signal = new CountDownLatch(1);
    }

    @After
    public void finish(){
        signal.countDown();
    }

    @Test
    public void testJokeASyncTask() throws InterruptedException {
        EndpointsAsyncTask task = new EndpointsAsyncTask();
        task.setListener(new EndpointsAsyncTask.GetJokeTaskListener() {
            @Override
            public void onComplete(String joke, Exception e) {
                exception = e;
                result = joke;
                signal.countDown();
            }
        });
        task.execute(context);
        signal.await();
        assertNull(exception);
        assertFalse(TextUtils.isEmpty(result));
    }
}
