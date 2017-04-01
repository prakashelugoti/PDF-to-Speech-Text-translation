package com.example.prakashreddy45.project;

import android.app.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;

import java.util.Locale;

public class TranslateSecond extends Activity implements TextToSpeech.OnInitListener {
    ProgressDialog progressDialog,dialogbar;
    private int check_code = 0;
    PastTranslationsDatabase mydb;
    Spinner lang;
    Button say;
    EditText text;
    TextView translatedText;
    String original=null;
    ClientID clientID;
    ClientSecret clientSecret;
    String translated=null;
    String langSelected;
    private TextToSpeech convert;
    private static final String tag = "Main";
    // private MalibuCountDownTimer countDownTimer;
    private long timeElapsed;
    private TextView timeElapsedView;
    String[] secret = new String[2];
    private final long startTime = 10000;
    private final long interval = 1000;
    int previous=100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate_second);

        mydb=new PastTranslationsDatabase(this);



        Toast.makeText(TranslateSecond.this,"Welcome",Toast.LENGTH_SHORT).show();
        clientID=new ClientID();
        clientSecret=new ClientSecret();
        lang=(Spinner)findViewById(R.id.selectLanguage);
        say=(Button)findViewById(R.id.say);
        text=(EditText)findViewById(R.id.text);


        // text.setBackgroundResource(R.drawable.round_edit);
        progressDialog=new ProgressDialog(this);
        dialogbar=new ProgressDialog(this);
        translatedText=(TextView)findViewById(R.id.translatedtext);
        // translatedText.setBackgroundResource(R.drawable.round_edit);
        timeElapsedView=(TextView)findViewById(R.id.timer_text);
        // countDownTimer = new MalibuCountDownTimer(startTime, interval);
        //checkConnection();
        /*dialogbar.setMessage("Connecting to server...");
        dialogbar.setCancelable(false);
        dialogbar.show();*/
        //getkey();
// editText.setBackgroundResource(R.layout.edittext_border);
        Intent check = new Intent();
        check.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA   );
        startActivityForResult(check, check_code);
        say.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s=text.getText().toString();
                original="";
                translatedText.setMovementMethod(new ScrollingMovementMethod());
                translatedText.setText("Output:");
                if(s.equals(""))
                {
                    Toast.makeText(TranslateSecond.this,"Enter some text",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (checkconnection()==0) {
                    Toast.makeText(TranslateSecond.this,"Connection Lost!!! Please try again Later",Toast.LENGTH_LONG).show();
                    return;
                    // finish();
                    //startActivity(new Intent(TranslateSecond.this, WifiData.class));
                }
                else{
                    progressDialog.setMessage("Translating...\nPlease wait");
                    progressDialog.setCancelable(false);
                    progressDialog.show();
                    say.setEnabled(false);

                    //say.setText("Please wait");


                    class bgStuff extends AsyncTask<Void, Void, Void> {
                        @Override

                        protected Void doInBackground(Void... params) {
                            // TODO Auto-generated method stub
                            //checkConnection();
                          // if(secret[1].equals(""))
                                secret[1]="dhe09";
                            //if(secret[0].equals(""))
                                secret[0]="5rxQwsCl4QdbuteH5k8CzMOpVsg36iiYcaggvhXeGQE=";
                            Translate.setClientId(secret[1]); //Change this
                            Translate.setClientSecret(secret[0]);  //Change this
                            //Log.i("calling....................", "speak");
                            original = text.getText().toString();
                            //Log.i("Original Text is.............", original);
                            langSelected = String.valueOf(lang.getSelectedItem());
                           // Log.i("Language selected.........", langSelected);


                            if (text != null && text.length() > 0) {
                                try {
                                    if (langSelected.equalsIgnoreCase("HINDI")) {
                                        convert.setLanguage(new Locale("hi"));
                                        translated = Translate.execute(original, Language.HINDI);
                                        Log.i("translated text................", translated);
                                        say.setText("Translate");
                                        say.setEnabled(true);
                                    }
                                    else if (langSelected.equalsIgnoreCase("SPANISH")) {
                                        convert.setLanguage(new Locale("spa"));
                                        translated = Translate.execute(original, Language.SPANISH);
                                        Log.i("translated text................", translated);
                                        say.setText("Translate");
                                        say.setEnabled(true);
                                    } else if (langSelected.equalsIgnoreCase("FRENCH")) {
                                        convert.setLanguage(Locale.FRENCH);
                                        translated = Translate.execute(original, Language.FRENCH);
                                        Log.i("translated text................", translated);
                                        say.setText("Translate");
                                        say.setEnabled(true);
                                    } else if (langSelected.equalsIgnoreCase("GERMAN")) {
                                        convert.setLanguage(Locale.GERMAN);
                                        translated = Translate.execute(original, Language.GERMAN);
                                        Log.i("translated text................", translated);
                                        say.setText("Translate");
                                        say.setEnabled(true);
                                    } else if (langSelected.equalsIgnoreCase("ITALIAN"))

                                    {
                                        convert.setLanguage(Locale.ITALIAN);
                                        translated = Translate.execute(original, Language.ITALIAN);
                                        Log.i("translated text................", translated);
                                        say.setText("Translate");
                                        say.setEnabled(true);
                                    } else if (langSelected.equalsIgnoreCase("CHINESE"))

                                    {
                                        convert.setLanguage(Locale.CHINESE);
                                        translated = Translate.execute(original, Language.CHINESE_SIMPLIFIED);
                                        Log.i("translated text................", translated);
                                        say.setText("Translate");
                                        say.setEnabled(true);
                                    }
                                    else if (langSelected.equalsIgnoreCase("ARABIC")) {
                                        convert.setLanguage(new Locale("ara"));
                                        translated = Translate.execute(original, Language.ARABIC);
                                        Log.i("translated text................", translated);
                                        say.setText("Translate");
                                        say.setEnabled(true);
                                    }
                                    else if (langSelected.equalsIgnoreCase("PORTUGUESE")) {
                                        convert.setLanguage(new Locale("por"));
                                        translated = Translate.execute(original, Language.PORTUGUESE);
                                        Log.i("translated text................", translated);
                                        say.setText("Translate");
                                        say.setEnabled(true);
                                    }

                                } catch (Exception e)

                                {
                                    say.setEnabled(true);
                                    Log.i("Error in translation.........", e.toString());

                                }

                            }
                            //else
                            //Toast.makeText(TranslateSecond.this,"Enter Something to translate",Toast.LENGTH_SHORT).show();

                            return null;

                        }


                        @Override

                        protected void onPostExecute(Void result) {

                            progressDialog.dismiss();
                            say.setEnabled(true);
                            int randomnumber = (int) (Math.random() * 48);

                            //Preventing the previous random number with current random number match
                            if(previous==randomnumber&&randomnumber==0)
                            {
                                randomnumber=1;
                            }
else if(previous==randomnumber)
{
    randomnumber=randomnumber/2;

}
 previous=randomnumber;

                            // countDownTimer.start();
                            //timerHasStarted = true;
                            //startB.setText("Start");
                            String [] client_ids,client_secrets;


                            client_ids=clientID.client_id;
                            client_secrets=clientSecret.client_secret;
                            String id=client_ids[randomnumber];
                            String sec=client_secrets[randomnumber];

                            translatedText.setText("Output:\n" + translated + "\n" + id);
                            boolean isInserted=mydb.insertData(text.getText().toString(),translated,langSelected);

                            Toast.makeText(TranslateSecond.this, "Saying: " + sec, Toast.LENGTH_LONG).show();

                            convert.speak(translated, TextToSpeech.QUEUE_ADD, null);


                        }

                    }

                    new bgStuff().execute();
                }

            }

        });

    }






    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == check_code) {

            if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {

                // success, create the TTS instance

                convert = new TextToSpeech(this, this);

            }

            else {

                // missing data, install it



            }

        }

    }



    @Override

    public void onInit(int status) {

        if (status == TextToSpeech.SUCCESS) {

            //  Toast.makeText(TranslateSecond.this,"Engine is initialized", Toast.LENGTH_LONG).show();



            int result = convert.setLanguage(Locale.ENGLISH);





        }

        else if (status == TextToSpeech.ERROR) {

            Toast.makeText(TranslateSecond.this,"Error occurred while initializing engine", Toast.LENGTH_LONG).show();

        }

    }

    public void getkey()    
    {
        Ion.with(getApplicationContext())
                .load("https://prakashelugoti.wordpress.com/2016/08/15/first-blog-post/")
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {
                        // textView.setMovementMethod(new ScrollingMovementMethod());

                        char c;
                        int x = 0;
                        for (int i = 0; i < result.length(); i++) {
                            c = result.charAt(i);
                            if (c == '$') {
                                x = i;
                                break;
                            }
                        }
                        int y = 0;
                        String f = "";
                        for (int i = x + 1; i < result.length(); i++) {
                            c = result.charAt(i);
                            if (c == '$') {
                                y = i;
                                break;
                            }
                            //f=f+c;

                        }
//String s=String.valueOf(x)+"  "+String.valueOf(y);
                        //textView.setText(s);
                        String[] toppings = new String[20000];
                        int d = 0;
                        toppings[0] = "";
                        for (int i = x + 1; i < y; i++) {
                            c = result.charAt(i);
                            if (c == '*') {
                                d++;
                                toppings[d] = "";
                                continue;
                            }
                            toppings[d] = toppings[d] + c;
                        }
                        int randomnumber = (int) (Math.random() * d);


                        d = 0;
                        secret[0] = "";
                        secret[1] = "";

                        for (int i = 0; i < toppings[randomnumber].length(); i++) {
                            c = toppings[randomnumber].charAt(i);
                            if (c == '#') {
                                d++;
                                continue;
                            }
                            secret[d] = secret[d] + c;

                        }
                        dialogbar.dismiss();
                        //  Toast.makeText(TranslateSecond.this,"Success!!!",Toast.LENGTH_SHORT).show();
                        //String z=String.valueOf(randomnumber);
                        //textView.setText(z);
                        //progressDialog.dismiss();
                        //textView.setText("User name:\n" + secret[1] + "\n\nSecret id:\n" + secret[0]);
                    }
                });
    }
    @Override
    public  void onBackPressed()
    {
        //do nothing
        startActivity(new Intent(TranslateSecond.this,FanMenuButtons1Activity.class));
    }
    public int checkconnection()
    {
        ConnectivityManager cn=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nf=cn.getActiveNetworkInfo();
        if(nf != null && nf.isConnected()==true )
        {
            //Toast.makeText(this, "Network Available", Toast.LENGTH_LONG).show();
            return 1;
            //tvstatus.setText("Network Available");
        }
        else
        {
            // Toast.makeText(this, "Network Not Available", Toast.LENGTH_LONG).show();
            return 0;
            //tvstatus.setText("Network Not Available");
        }
    }



}

