package com.example.myapplication;

import android.view.View;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.myapplication.databinding.ActivityTimerBinding;
import com.google.android.material.snackbar.Snackbar;

public class activityTimer extends AppCompatActivity {
    private @NonNull ActivityTimerBinding binding;
    public static activityTimer instance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        binding = ActivityTimerBinding.inflate(getLayoutInflater());
        instance = this;

        Timer timer = new Timer();

        timer.isAlive = true;
        timer.run();

        binding.button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!timer.isAlive)
                {
                    timer.isAlive = true;
                    timer.run();
                }
            }
        });
    }

    public void OnTimerUpdate()
    {
        binding.time.setText(Integer.parseInt(binding.time.getText().toString() )+ 1);
        if (Integer.parseInt(binding.time.getText().toString()) % 5 == 0)
        {

        }
    }
}
class Timer implements Runnable
{
    volatile boolean isAlive=false;
    @Override
    public void run() {
        System.out.println("Thread started");
        while (isAlive)
        {
            try
            {
                Thread.sleep(1000);
                activityTimer.instance.OnTimerUpdate();
            }
             catch (InterruptedException e)
            {
            }
        }
    }
    public void Stop()
    {
        isAlive = false;
    }
}