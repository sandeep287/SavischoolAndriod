package com.savitroday.savischools.helper;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.view.View;

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
static {
    directory = new File(Environment.getExternalStorageDirectory()+File.separator+"Savischool");
    directory.mkdirs();
}
    public DownloadFileFromURL(Context context,String filetype,String fileName) {
        this.context = context;
        this.filetype=filetype;
        this.fileName=fileName;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        MessageOpenViewAdapter.downlode.setVisibility(View.GONE);
        MessageOpenViewAdapter.progressBar.setVisibility(View.VISIBLE);

    }

    /**
     * Downloading file in background thread
     * */
    @Override
    protected String doInBackground(String... f_url) {
        int count;
        try {
            URL url = new URL(f_url[0]);
            URLConnection conection = url.openConnection();
            conection.connect();
            int lenghtOfFile = conection.getContentLength();

             InputStream input = new BufferedInputStream(url.openStream(), 8192);
             OutputStream output = new FileOutputStream("/sdcard/Savischool/"+fileName);
             byte data[] = new byte[2024];

            long total = 0;

            while ((count = input.read(data)) != -1) {
                total += count;
                 publishProgress(""+(int)((total*100)/lenghtOfFile));

                output.write(data, 0, count);;
            }

            // flushing output
            output.flush();

            // closing streams
            output.close();
            input.close();

        } catch (Exception e) {
        e.printStackTrace();
        }

        return null;
    }


//    protected void onProgressUpdate(String... progress) {
//        // setting progress percentage
//       // pDialog.setProgress(Integer.parseInt(progress[0]));
//    }


    @Override
    protected void onPostExecute(String file_url) {

MessageOpenViewAdapter.progressBar.setVisibility(View.GONE);

      //  String filePath = Environment.getExternalStorageDirectory().toString() + "Download/"+fileName;
        String filePath =   directory.getAbsolutePath()+File.separator+fileName;
        File  file=new File(filePath);
        Uri uri = Uri.fromFile(file);
        Intent intent = new Intent(Intent.ACTION_VIEW);

        if (filetype.equals("doc") || filetype.equals(".docx")) {

            intent.setDataAndType(uri, "application/*");
        }
        else if(filetype.equals(".pdf")) {

            intent.setDataAndType(uri, "application/*");
        }
        else if(filetype.equals(".xlsx")|| filetype.equals(".xls")) {

            intent.setDataAndType(uri, "application/*");
        }
        else if(filetype.equals(".txt")) {
            intent.setDataAndType(uri, "text/*");
        }
        else if(filetype.equals(".3gp") || filetype.equals(".mpg") || filetype.equals(".mpeg") || filetype.equals(".mpe") || filetype.equals(".mp4") || filetype.equals(".avi")) {

            intent.setDataAndType(uri, "video/*");
        }
        else
            {
                intent.setDataAndType(uri, "*/*");
            }

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        context.startActivity(intent);

    }

}