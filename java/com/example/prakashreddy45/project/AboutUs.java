package com.example.prakashreddy45.project;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class AboutUs extends Activity {
TextView textView;
    String about_us;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        about_us="Mentor:\nProf. Aditya Trivedi\n\nGroup Members:\nE.Sai Prakash Reddy\nP.Satish\nD.Viswanath\n\nInstitute:\nABV-IIITM,Gwalior";
        textView=(TextView)findViewById(R.id.textView);
        textView.setText(about_us);
    }
}
