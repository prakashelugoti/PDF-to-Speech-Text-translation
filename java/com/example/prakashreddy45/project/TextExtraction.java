package com.example.prakashreddy45.project;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.LogRecord;

public class TextExtraction extends ActionBarActivity {
    ImageButton previous, next, play, pause;
    String[] names = new String[10];
    int index = 0;
    TextToSpeech t1;
    TextView textView;
    int x=0,y=110;
    TextView tv;
    public static String PREFACE = "";
    Button bt;
    private Handler handler = new Handler();

    int pagenumber=2;
    ProgressDialog progressDialog;
    DatabaseHelper mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_extraction);

        mydb=new DatabaseHelper(this);
        previous = (ImageButton) findViewById(R.id.previous);
        next = (ImageButton) findViewById(R.id.next);
        play = (ImageButton) findViewById(R.id.imageButton2);
        final Animation animation = new AlphaAnimation(1, 0); // Change alpha from fully visible to invisible
        animation.setDuration(10); // duration  milliseconds
        animation.setInterpolator(new LinearInterpolator()); // do not alter animation rate
        animation.setRepeatCount(1); // Repeat animation infinitely
        animation.setRepeatMode(Animation.REVERSE); // Reverse animation at the end so the button will fade back in

        tv = (TextView) findViewById(R.id.text);
        Bundle bundle = getIntent().getExtras();
        String message = bundle.getString("message");

        boolean isInserted=mydb.insertData(message,"PDF","100");

        PREFACE = message;

        try {
            names[0]=parsePdf(PREFACE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        textView = (TextView) findViewById(R.id.textView);
        textView.setMovementMethod(new ScrollingMovementMethod());
        textView.setText(names[0]);


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



        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                previous.startAnimation(animation);
               // handler.removeCallbacks(runnable);
                t1.shutdown();
               // handler.removeCallbacks(runnable);
                try {
                    pagenumber--;
                    names[0]= parsePdf(PREFACE);
                    textView.setText("");
                    textView.setMovementMethod(new ScrollingMovementMethod());
                    textView.setText(names[0]);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //  t1.stop();
                play.setImageResource(R.drawable.play);

            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                next.startAnimation(animation);
                //handler.removeCallbacks(runnable);
                //handler.removeCallbacks(runnable);
                t1.shutdown();
                try {
                    pagenumber++;
                    names[0]= parsePdf(PREFACE);
                    textView.setText("");
                    textView.setMovementMethod(new ScrollingMovementMethod());
                    textView.setText(names[0]);
                } catch (IOException e) {
                    e.printStackTrace();
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

                t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if (status != TextToSpeech.ERROR) {
                            t1.setLanguage(Locale.US);
                            x=0;y=110;
                            play.setImageResource(R.drawable.pause);
    t1.speak(names[0], TextToSpeech.QUEUE_FLUSH, null);

//runnable.run();

                            //runnable.run();
                            //Toast.makeText(TextExtraction.this,"Hello",Toast.LENGTH_LONG).show();
                            //thread(names[index]);
                        }
                    }
                });
                //  thread(names[index]);
            }
        });
    }

    public String parsePdf(String pdf) throws IOException {
        PdfReader reader = new PdfReader(pdf);
        PdfReaderContentParser parser = new PdfReaderContentParser(reader);
        // PrintWriter out = new PrintWriter(new FileOutputStream(txt));
        TextExtractionStrategy strategy;

        //check whether the documnent has greater than or equal to 5 pages
        strategy = parser.processContent(pagenumber, new SimpleTextExtractionStrategy());
        String s="";
        s=strategy.getResultantText();
        return s;
        //out.println(strategy.getResultantText());

        //  out.flush();
        //  Toast.makeText(TextExtraction.this,"Success",Toast.LENGTH_LONG).show();
//String s=out.toString();

//handler.removeCallbacks(runnable);
//Set the text
        //deleteTextFile("/sdcard/extracted.txt");
        // myTTS.speak(text.toString(), TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    public  void onBackPressed()
    {
        t1.shutdown();
        startActivity(new Intent(TextExtraction.this,FanMenuButtons1Activity.class));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        if(item.getItemId()==R.id.action_jump_to)
        {
           // Toast.makeText(getApplicationContext(),"Jump",Toast.LENGTH_SHORT).show();
            get_alert();
        }
        return super.onOptionsItemSelected(item);
    }
    public void get_alert()
    {
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        final EditText editText=new EditText(TextExtraction.this);
        alert.setMessage("Page number:");
        alert.setTitle("Enter Page number!");
        alert.setView(editText);
        alert.setPositiveButton("Go", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String x=editText.getText().toString();
                jump_to_page(x);

            }
        });
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
alert.show();
    }
    public void jump_to_page(String s)
    {
        int x= Integer.parseInt(s.toString());
        t1.shutdown();
       // next.startAnimation(animation);


        // handler.removeCallbacks(runnable);
        //handler.removeCallbacks(runnable);
        t1.shutdown();
        try {
            pagenumber=pagenumber+x;
            names[0]= parsePdf(PREFACE);
            textView.setText("");
            textView.setMovementMethod(new ScrollingMovementMethod());
            textView.setText(names[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // t1.stop();
        play.setImageResource(R.drawable.play);
    }
    public void alert_settings()
    {
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        //final EditText editText=new EditText(TextExtraction.this);
        final SeekBar seekBar=new SeekBar(TextExtraction.this);
        alert.setMessage("Volume");
        alert.setTitle("Settings");
        alert.setMessage("Pitch");
        alert.setMessage("Speed");
        alert.setView(seekBar);
        alert.setPositiveButton("Go", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //String x=editText.getText().toString();
                //jump_to_page(x);
Toast.makeText(getApplicationContext(),seekBar.getProgress(),Toast.LENGTH_SHORT).show();
            }
        });
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alert.show();
    }
}