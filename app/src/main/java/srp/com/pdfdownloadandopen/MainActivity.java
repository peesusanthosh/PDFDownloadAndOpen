package srp.com.pdfdownloadandopen;

import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    Button pdfdownload;
    PDFView pdfView;
    private static final int PERMISSION_REQUEST_CODE = 1;

    //Change this url as per your requirement
    String URL="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pdfdownload = (Button) findViewById(R.id.pdfdownload);
        pdfView = (PDFView) findViewById(R.id.pdfView);

        pdfdownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DownloadTask(MainActivity.this,URL);
            }
        });
        new RetrievePDFStream().execute( URL);

    }

    class RetrievePDFStream extends AsyncTask<String, Void, InputStream> {

        @Override
        protected InputStream doInBackground(String... strings) {
            InputStream inputStream = null;
            try {
                java.net.URL url = new URL(strings[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                Log.d("demo",urlConnection.getResponseCode()+"");
                if (urlConnection.getResponseCode() == 200) {
                    inputStream = new BufferedInputStream((urlConnection.getInputStream()));
                }

            } catch (Exception e) {
                e.printStackTrace();
                Log.d("demo",e.toString());
            }
            return inputStream;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }


        @Override
        protected void onPostExecute(InputStream inputStream) {
            pdfView.fromStream(inputStream).load();

        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.e("value", "Permission Granted, Now you can use local drive .");
                } else {
                    Log.e("value", "Permission Denied, You cannot use local drive .");
                }
                break;
        }
    }
}
