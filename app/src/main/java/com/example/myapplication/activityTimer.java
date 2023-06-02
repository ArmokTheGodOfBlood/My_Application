package com.example.myapplication;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.myapplication.databinding.ActivityTimerBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.concurrent.TimeUnit;

import static com.example.myapplication.activityTimer.instance;

public class activityTimer extends AppCompatActivity {
    private @NonNull ActivityTimerBinding binding;
    public static activityTimer instance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        binding = ActivityTimerBinding.inflate(getLayoutInflater());
        instance = this;
    }
    TimerTask task;
    public void startClick(View view) {
        task = new TimerTask();
        task.execute();
    }
    public void stopClick(View view) {
        task.cancel(true);
    }

    public void OnTimerUpdate()
    {
        String time = binding.time.getText().toString();
        int timeAsInt = Integer.parseInt(time) + 1;
        time = String.valueOf(timeAsInt);
        binding.time.setText(time);
        if (timeAsInt % 5 == 0)
        {

        }
    }
}
class TimerTask extends AsyncTask<Void, Integer, Void> {
    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        instance.OnTimerUpdate();

    }
    @Override
    protected Void doInBackground(Void... voids) {
        try{
            int counter = 0;
            while (true)
            {
                TimeUnit.SECONDS.sleep(1);
                publishProgress(++counter);
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        return null;
    }
}
