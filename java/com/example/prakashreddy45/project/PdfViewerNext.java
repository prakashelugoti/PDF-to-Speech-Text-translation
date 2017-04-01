package com.example.prakashreddy45.project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class PdfViewerNext extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_viewer_next);
        PdfViewer pdfViewer=new PdfViewer();
        int x=pdfViewer.var;
        Toast.makeText(PdfViewerNext.this,x,Toast.LENGTH_SHORT).show();
    }
}
