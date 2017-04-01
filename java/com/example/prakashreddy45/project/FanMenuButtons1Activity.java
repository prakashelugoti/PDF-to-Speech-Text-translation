package com.example.prakashreddy45.project;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;


public class FanMenuButtons1Activity extends Activity {


DatabaseHelper mydb;
    int backButtonCount=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fan_menu_buttons1);

        mydb=new DatabaseHelper(this);

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.myFAB);
        final FanMenuButtons sub = (FanMenuButtons) findViewById(R.id.myFABSubmenu);
        if (sub != null) {
            sub.setOnFanButtonClickListener(new FanMenuButtons.OnFanClickListener() {
                @Override
                public void onFanButtonClicked(int index) {

                    //sub.setButtonSelected(index);
                    //createShortcutIcon();

                    if(index==5)
                    {
                        // Toast.makeText(FanMenuButtons1Activity.this, "Please wait", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(FanMenuButtons1Activity.this,Internal.class);
                        startActivity(intent);
                    }
                    else if(index==4)
                    {
                       Intent intent=new Intent(FanMenuButtons1Activity.this,WifiData.class);
                        startActivity(intent);

                    }

                    else if(index==3)
                    {
                        //create the send intent
                        Intent shareIntent =
                                new Intent(android.content.Intent.ACTION_SEND);

//set the type
                        shareIntent.setType("text/plain");

//add a subject
                        shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,
                                "Hey download this Cool App");

//build the body of the message to be shared
                        String shareMessage = "Hey dude! Download this awesome app.\nClick here to download\n***Playstore Link***";

//add the message
                        shareIntent.putExtra(android.content.Intent.EXTRA_TEXT,
                                shareMessage);

//start the chooser for sharing
                        startActivity(Intent.createChooser(shareIntent,
                                "Share this cool App Via"));
                    }
                    else if(index==2)
                    {

                        Cursor res=mydb.getdata();
                        if(res.getCount()==0)
                        {
                            //show message
                            //Toast.makeText(MainActivity.this,"Insert some data",Toast.LENGTH_SHORT).show();
                            showmessage("PDF Lists","No data!\nPlease read some files ");
                            return ;
                        }
                        StringBuffer stringBuffer=new StringBuffer();
                        while(res.moveToNext())
                        {
                            stringBuffer.append("File Number: "+res.getString(0)+"\n");
                            stringBuffer.append("File Name: "+res.getString(1)+"\n\n");

                            //show data
                        }
                        showmessage("PDF Lists",stringBuffer.toString());

                    }
                    else if(index==1)
                    {
                       startActivity(new Intent(FanMenuButtons1Activity.this,AboutUs.class));

                    }
                    else if(index==0)
                    {
                        // Toast.makeText(FanMenuButtons1Activity.this, "Under Construction!!!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(FanMenuButtons1Activity.this,FBLike.class));

                    }
                }
            });

            if (fab != null) {
                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sub.toggleShow();
                    }
                });
            }
        }


    }
    public void showmessage(String title ,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    @Override
    public void onBackPressed()
    {
        if(backButtonCount >= 1)
        {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this, "Press  again to exit!!!", Toast.LENGTH_SHORT).show();
            backButtonCount++;
        }
    }

}
