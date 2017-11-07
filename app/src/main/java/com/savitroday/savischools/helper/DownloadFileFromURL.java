package com.savitroday.savischools.helper;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.savitroday.savischools.R;
import com.savitroday.savischools.adapter.MessageOpenViewAdapter;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by owner on 10/27/2017.
 */

public class DownloadFileFromURL extends AsyncTask<String, String, String> {
    
    
    Context context;
    
    String filetype;
    String fileName;
    static File directory;
    NotificationManager manager;
    NotificationCompat.Builder builder;
    int len=100;
    int counter=0;
    
    static {
        directory = new File(Environment.getExternalStorageDirectory() + File.separator + "Savischool");

        directory.mkdirs();

    }
    
    public DownloadFileFromURL(Context context, String filetype, String fileName) {
   //     mNotificationHelper = new NotificationHelper(context);
        this.context = context;
        this.filetype = filetype;
        this.fileName = fileName;
      manager =(NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
         builder=new NotificationCompat.Builder(context);
    }
    
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        MessageOpenViewAdapter.downlode.setVisibility(View.GONE);
        MessageOpenViewAdapter.progressBar.setVisibility(View.VISIBLE);
       builder.setContentTitle("Downloading ...").setContentText("Download in progress").setSmallIcon(R.drawable.profile_img);

        builder.setProgress(0, 0, true);
     manager.notify(1,builder.build());
       builder.setAutoCancel(true);
    }
    
    /**
     * Downloading file in background thread
     */
    @Override
    protected String doInBackground(String... f_url)
    {
len=f_url.length;
        int count;
        try {
            URL url = new URL(f_url[0]);

            URLConnection conection = url.openConnection();

            conection.connect();
            int lenghtOfFile = conection.getContentLength();
            File file=new File(Environment.getExternalStorageDirectory()
                    .getPath()+ File.separator + "Savischool/" + fileName);
            if (!file.exists()) {
                InputStream input = new BufferedInputStream(url.openStream(), 8192);
                OutputStream output = new FileOutputStream(Environment.getExternalStorageDirectory()
                        .getPath() + File.separator + "Savischool/" + fileName);
                byte data[] = new byte[2024];

                long total = 0;

                while ((count = input.read(data)) != -1) {
                    total += count;
                    publishProgress("" + (int) ((total * 100) / lenghtOfFile));

                    output.write(data, 0, count);
                    ;
                }

                // flushing output
                output.flush();

                // closing streams
                output.close();
                input.close();
            }
            else {


            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        return null;
    }





    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);

    len=values.length;
        if (counter >= len - 1) {
            builder.setContentTitle("Done.");
            builder.setContentText("Download complete")
                    // Removes the progress bar
                    .setProgress(0, 0, false);
            manager.notify(1,builder.build());
        } else {
            int per = (int) (((counter + 1) / len) * 100f);
            Log.i("Counter", "Counter : " + counter + ", per : " + per);
            builder.setContentText("Downloaded (" + per + "/100");
            builder.setProgress(100, per, false);
            // Displays the progress bar for the first time.
            manager.notify(1, builder.build());
        }
        counter++;
    }

    @Override
    protected void onPostExecute(String file_url) {

        Toast.makeText(context,"downlode Complete...",Toast.LENGTH_LONG).show();
        builder.setContentTitle("Done.");
        builder.setContentText("Download complete")
                // Removes the progress bar
                .setProgress(0, 0, false);
        manager.notify(1,builder.build());
        MessageOpenViewAdapter.progressBar.setVisibility(View.GONE);
//
//        //  String filePath = Environment.getExternalStorageDirectory().toString() + "Download/"+fileName;
//        String filePath = directory.getAbsolutePath() + File.separator + fileName;
//        File file = new File(filePath);
////        Uri uri = Uri.fromFile(file);
//        Uri uri = FileProvider.getUriForFile(
//                context,
//                context.getApplicationContext()
//                        .getPackageName() + ".provider", file);
//        Intent intent = new Intent(Intent.ACTION_VIEW);
//
//        if (filetype.equals("doc") || filetype.equals(".docx")) {
//
//            intent.setDataAndType(uri, "application/*");
//        } else if (filetype.equals(".pdf")) {
//
//            intent.setDataAndType(uri, "application/*");
//        } else if (filetype.equals(".xlsx") || filetype.equals(".xls")) {
//
//            intent.setDataAndType(uri, "application/*");
//        } else if (filetype.equals(".txt")) {
//            intent.setDataAndType(uri, "text/*");
//        } else if (filetype.equals(".3gp") || filetype.equals(".mpg") || filetype.equals(".mpeg") || filetype.equals(".mpe") || filetype.equals(".mp4") || filetype.equals(".avi")) {
//
//            intent.setDataAndType(uri, "video/*");
//        } else {
//            intent.setDataAndType(uri, "*/*");
//        }
//
//
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//        context.startActivity(intent);
//

    }
    
}