package com.example.prakashreddy45.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import net.sf.andpdf.pdfviewer.PdfViewerActivity;
public class PdfOrText extends AppCompatActivity {

    private RadioGroup radioSexGroup;
    private RadioButton radioSexButton;
    private Button btnDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_pdf_or_text);
        radioSexGroup=(RadioGroup)findViewById(R.id.radioGroup);
        Bundle bundle = getIntent().getExtras();
        final String message = bundle.getString("message");
        btnDisplay=(Button)findViewById(R.id.button);

        btnDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = radioSexGroup.getCheckedRadioButtonId();
                radioSexButton = (RadioButton) findViewById(selectedId);
               String s= (String) radioSexButton.getText();
                if(s.equals("Read PDF as Text"))
                {
                    Intent intent=new Intent(PdfOrText.this,TextExtraction.class);
                    intent.putExtra("message",message);
                    startActivity(intent);
                }
                else
                {
                    Intent intent = new Intent(PdfOrText.this, PdfViewer.class);
                    intent.putExtra(PdfViewer.EXTRA_PDFFILENAME, message);
                    startActivity(intent);
             }
            }
        });
    }

}
