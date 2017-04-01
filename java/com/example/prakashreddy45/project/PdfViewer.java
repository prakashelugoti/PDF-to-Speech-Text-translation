package com.example.prakashreddy45.project;

import android.widget.Toast;

import net.sf.andpdf.pdfviewer.PdfViewerActivity;


public class PdfViewer extends PdfViewerActivity
{
int var=0;
    public int getPreviousPageImageResource()
    {
        return R.drawable.ic_custom_ring_menu_channel4;
    }

    public int getNextPageImageResource()
    {
        return R.drawable.ic_custom_ring_menu_channel7;
    }

    public int getZoomInImageResource()
    {
        
        return R.drawable.ic_custom_ring_menu_channel1;
    }

    public int getZoomOutImageResource()
    {
        return R.drawable.ic_custom_ring_menu_home;
    }

    public int getPdfPasswordLayoutResource()
    {
        return 0;
    }

    public int getPdfPageNumberResource() {
        return 0;
    }

    @Override
    public int getPdfPasswordEditField() {
        return 0;
    }

    @Override
    public int getPdfPasswordOkButton() {
        return 0;
    }

    @Override
    public int getPdfPasswordExitButton() {
        return 0;
    }

    @Override
    public int getPdfPageNumberEditField() {
        return 0;
    }


// @Override
    // public int getNextPageImageResource() {
    // return 0;
    // }

//
    // @Override
    // public int getPdfPageNumberEditField() {
    // return 0;
    // }

    //
    // @Override
    // public int getPdfPageNumberResource() {
    // return 0;
    // }

//
    // @Override
    // public int getPdfPasswordEditField() {
    // return 0;
    // }

//
    // @Override
    // public int getPdfPasswordExitButton() {
    // // TODO Auto-generated method stub
    // return 0;
    // }
    //
    // @Override
    // public int getPdfPasswordLayoutResource() {
    // // TODO Auto-generated method stub
    // return 0;
    // }
    //
    // @Override
    // public int getPdfPasswordOkButton() {
    // // TODO Auto-generated method stub
    // return 0;
    // }

}

