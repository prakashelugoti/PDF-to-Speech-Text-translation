package com.example.prakashreddy45.project;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.ScrollingMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.LogRecord;

public class Speech extends ActionBarActivity {
    ImageButton previous, next, play, pause;
    String[] names = new String[10];
    int index = 0;
    public int splash_time = 3000;
    int timer = 0;
    TextToSpeech t1;
    TextView textView;
    int x=0,y=110;
    int timeleft=3;
    Timer _t = new Timer();
    private Handler handler = new Handler();
    private Runnable runnable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_to_speech);
        previous = (ImageButton) findViewById(R.id.previous);
        next = (ImageButton) findViewById(R.id.next);
        play = (ImageButton) findViewById(R.id.imageButton2);
        String file_text = file_read();
        final Animation animation = new AlphaAnimation(1, 0); // Change alpha from fully visible to invisible
        animation.setDuration(10); // duration  milliseconds
        animation.setInterpolator(new LinearInterpolator()); // do not alter animation rate
        animation.setRepeatCount(1); // Repeat animation infinitely
        animation.setRepeatMode(Animation.REVERSE); // Reverse animation at the end so the button will fade back in


        String temp = "";
        for (int j = 0; j < 10; j++) {
            for (int i = j * 550; i < (j + 1) * 550; i++) {
                char c = ' ';
                c = file_text.charAt(i);
                //  String s= String.valueOf(c);
                temp = temp + c;
            }
            names[j] = temp;
            temp = "";
        }
        textView = (TextView) findViewById(R.id.textView);
        textView.setText(names[0]);

        runnable = new Runnable() {
            public void run() {
                //
                // Do the stuff
                //
                if(x<=440) {
                    Spannable WordtoSpan = new SpannableString(names[index]);
                    WordtoSpan.setSpan(new ForegroundColorSpan(Color.BLUE), x, y, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    x = x + 110;
                    y = y + 110;
                    textView.setMovementMethod(new ScrollingMovementMethod());
                    textView.setText(WordtoSpan);
                }
                else
                    handler.removeCallbacks(runnable);
                handler.postDelayed(this, 7000);
            }
        };
        runnable.run();
        //We schedule the timer task to run after 1000 ms and continue to run every 1000 ms.
        // timer.schedule(mt, 2000, 2000);
        // thread(names[0]);
        t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.US);
                    t1.speak(names[0], TextToSpeech.QUEUE_FLUSH, null);
                }
            }
        });
        //textView.setText(names[0]);

        long g = file_text.length();
        // Toast.makeText(MainActivity.this, (int) g,Toast.LENGTH_LONG).show();

        // delay(0,100);
        //Thread.interrupted();

        // Thread.interrupted();

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                previous.startAnimation(animation);
                handler.removeCallbacks(runnable);
                t1.shutdown();
                if (index != 0) {
                    index--;
                    textView.setText(names[index]);
                }
                //  t1.stop();
                play.setImageResource(R.drawable.play);

            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next.startAnimation(animation);
                handler.removeCallbacks(runnable);
                t1.shutdown();
                if (index != 9) {
                    index++;
                    textView.setText(names[index]);
                }
                // t1.stop();
                play.setImageResource(R.drawable.play);
            }
        });
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // thread(names[index]);
                t1.shutdown();
                x=0;y=110;
                runnable.run();
                t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if (status != TextToSpeech.ERROR) {
                            t1.setLanguage(Locale.US);
                            t1.speak(names[index], TextToSpeech.QUEUE_FLUSH, null);
                            play.setImageResource(R.drawable.pause);
                            //thread(names[index]);
                        }
                    }
                });
                //  thread(names[index]);
            }
        });
    }

    public String file_read() {
        File sdcard = Environment.getExternalStorageDirectory();

//Get the text file
        File file = new File(sdcard, "extracted.txt");

//Read text from file
        StringBuilder text = new StringBuilder();

        try {
            BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader(file));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            String line;
            try {
                while ((line = br.readLine()) != null) {
                    text.append(line);
                    text.append('\n');
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            br.close();
        } catch (IOException e) {
            Toast.makeText(Speech.this, "Error", Toast.LENGTH_SHORT).show();
        }
        String string = text.toString();


        return string;
    }




    class MyTimer extends TimerTask {
        public void run() {
            //This runs in a background thread.
            //We cannot call the UI from this thread, so we must call the main UI thread and pass a runnable

            runOnUiThread(new Runnable() {

                public void run() {
                    Spannable WordtoSpan = new SpannableString(names[0]);
                    WordtoSpan.setSpan(new ForegroundColorSpan(Color.BLUE), x, y, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    x=x+110;
                    y=y+110;
                    textView.setText(WordtoSpan);
                }
            });
        }


    }
}
