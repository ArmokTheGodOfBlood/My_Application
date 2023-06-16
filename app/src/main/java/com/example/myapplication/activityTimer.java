package com.example.myapplication;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.myapplication.databinding.ActivityTimerBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.concurrent.TimeUnit;

import static com.example.myapplication.activityTimer.instance;

public class activityTimer extends AppCompatActivity {

    int[] Images = new int[] {R.drawable._0, R.drawable._5, R.drawable._10 ,R.drawable._15 ,R.drawable._20, R.drawable._25, R.drawable._30, R.drawable._35,R.drawable._40, R.drawable._45,R.drawable._50, R.drawable._55};
    int ptr = 0;
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
        //task = new TimerTask(binding.timer);
        //task.execute();

        Timer task = new Timer();
        task.timer = binding.timer;
        task.run();
    }
    public void stopClick(View view) {
        task.cancel(true);
    }

    public void OnTimerUpdate(int time)
    {
        binding.time.setText(String.valueOf(time));
        if (time % 5 == 0)
        {
            ptr++;
            binding.timer.setImageResource(Images[ptr]);

        }
    }
}


class Timer implements Runnable
{
    public ImageView timer;
    int counter = 0;
    @Override
    public void run() {
        while(true)
        {try {
            TimeUnit.SECONDS.sleep(1);
            counter++;
        } catch (InterruptedException e) {
            //throw new RuntimeException(e);
        }
        instance.OnTimerUpdate(counter);
        timer.setImageResource(R.drawable._20);
        }
    }
}
class TimerTask extends AsyncTask<Void, Integer, Void> {
    public ImageView timer;
    public TimerTask(ImageView timer)
    {
        this.timer = timer;
    }
    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        instance.OnTimerUpdate(values[0]);
        timer.setImageResource(R.drawable._20);
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
