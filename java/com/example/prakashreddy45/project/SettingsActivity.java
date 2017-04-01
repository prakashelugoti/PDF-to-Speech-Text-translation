package com.example.prakashreddy45.project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity {
    TextView text;
    SeekBar seek;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        show();
    }

    public  void show()
    {
        text=(TextView)findViewById(R.id.textView);
        seek=(SeekBar)findViewById(R.id.seekBar);
        text.setText("Covered : " + seek.getProgress() + "/" + seek.getMax());
        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                text.setText("Covered : " + seek.getProgress() + "/" + seek.getMax());
                // Toast.makeText(MainActivity.this,"SeekBar in Progress",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //text.setText("Covered : " + seek.getProgress() + "/" + seek.getMax());
                //Toast.makeText(MainActivity.this,"SeekBar in Progress",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //text.setText("Covered : " + seek.getProgress() + "/" + seek.getMax());
                //Toast.makeText(MainActivity.this,"SeekBar in Progress",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
