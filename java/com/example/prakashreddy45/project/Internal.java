package com.example.prakashreddy45.project;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Internal extends ListActivity {

    private List<String> item = null;
    private List<String> path = null;
    private String root = "Home";
    private TextView myPath;
    String sdpath="";
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal);
        myPath = (TextView) findViewById(R.id.path);
        createFileExternalStorage();
    }
    private void getDir(String dirPath) {
        myPath.setText("Location: " + dirPath);
        item = new ArrayList<String>();
        path = new ArrayList<String>();
        File f = new File(dirPath);
        File[] files = f.listFiles();
        if (!dirPath.equals(root)) {
            item.add(root);
            path.add(root);
            item.add("Storage");
            path.add(f.getParent());
        }
        for (int i = 0; i < files.length; i++) {
            File file = files[i];
            path.add(file.getPath());
            if (file.isDirectory())
                item.add(file.getName() + "/");
            else
                item.add(file.getName());
        }
        ArrayAdapter<String> fileList = new ArrayAdapter<String>(this,
                R.layout.explorer_row, item);
        setListAdapter(fileList);
    }
    File file;
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        file = new File(path.get(position));
        if (file.isDirectory()) {
            if (file.canRead())
                getDir(path.get(position));
            else {
                new AlertDialog.Builder(this)
                        .setIcon(R.drawable.pdf)
                        .setTitle("[" + file.getName()+ "] folder can't be read!")
                        .setPositiveButton("OK",new DialogInterface.OnClickListener() {
                            @Override public void onClick(DialogInterface dialog,int which) {
                            }}).show();
            }
        } else {
            new AlertDialog.Builder(this)
                    .setIcon(R.drawable.pdf)
                    .setTitle("Select") .setMessage("Select " + file.getName() + " to Speech ?\nand be sure that it has more than 5 pages of data/text")
                    .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                        @Override public void onClick(DialogInterface dialog,int which) {
                            // Toast.makeText(
                            //     MainActivity.this,"" + file.getAbsolutePath()+ " iss selected ",Toast.LENGTH_LONG)
                            //    .show();
                            Intent intent =new Intent(Internal.this,TextExtraction.class);
                            String s=file.getAbsolutePath();
                            intent.putExtra("message",s);
                            startActivity(intent);

                        }
                    })
                    .setNegativeButton("No",new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog,int which) {
                            dialog.dismiss();
                        }
                    }).show();
        }
    }
    private void createFileExternalStorage() {
        MarshMallowPermission marshMallowPermission = new MarshMallowPermission(this);
        if (!marshMallowPermission.checkPermissionForExternalStorage()) {
            marshMallowPermission.requestPermissionForExternalStorage();
            finish();
        }
        File backupFile;
        File appFolder=null;
        // if SDCard available
        if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)){
            appFolder = new File("/storage/emulated/0", "");

            if (!appFolder.exists())
                appFolder.mkdir();
            //   Log.d(TAG,"In External");
        }else {   // if not SDCard available
            ContextWrapper cw = new ContextWrapper(this);
            // appFolder = cw.getDir("/storage/emulated/0/Download", Context.MODE_PRIVATE);

            //   Log.d(TAG,"In internal");
        }
        //create a new file, to save the downloaded file
       /* if(new File("/storage/extSdCard/").exists())
        {
            sdpath="/storage/extSdCard";
            Log.i("Sd Cardext Path",sdpath);
        }
        else if(new File("/storage/sdcard1/").exists())
        {
            sdpath="/storage/sdcard1";
            Log.i("Sd Card1 Path",sdpath);
        }
        else if(new File("/storage/usbcard1/").exists())
        {
            sdpath="/storage/usbcard1";
            Log.i("USB Path",sdpath);
        }
        else if(new File("/storage/sdcard0/").exists())
        {
            sdpath="/storage/sdcard0";
            Log.i("Sd Card0 Path",sdpath);
        }*/
        sdpath="/storage/emulated/0";
        getDir(sdpath);


    }
    public class MarshMallowPermission {
        public static final int EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE = 2;
        Activity activity;

        public MarshMallowPermission(Activity activity) {
            this.activity = activity;
        }


        public boolean checkPermissionForExternalStorage(){
            int result = ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (result == PackageManager.PERMISSION_GRANTED){
                return true;
            } else {
                return false;
            }
        }

        public void requestPermissionForExternalStorage(){
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                //Toast.makeText(activity, "External Storage permission needed. Please allow in App Settings for additional functionality.", Toast.LENGTH_LONG).show();
            } else {
                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE);
            }
        }

    }
}
