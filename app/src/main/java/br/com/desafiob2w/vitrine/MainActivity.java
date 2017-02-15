package br.com.desafiob2w.vitrine;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                getProducts();
            }
        });


    }


    private class ApiOperation extends AsyncTask<Void, Void, JSONObject> {

        @Override
        protected JSONObject doInBackground(Void... params) {

            HttpURLConnection urlConnection = null;
            JSONObject obj = null;

            try{

                URL url = new URL("https://apiadapter.ad5track.com/ads/americanas?api=b2wads&category_id=229187&size=10&term=Celulares%20e%20Smartphones");

                urlConnection = (HttpURLConnection) url.openConnection();

                urlConnection.setConnectTimeout(15000);
                urlConnection.setReadTimeout(15000);

                int statusCode = urlConnection.getResponseCode();
                if (statusCode == HttpURLConnection.HTTP_UNAUTHORIZED) {
                    // handle unauthorized (if service requires user login)
                } else if (statusCode != HttpURLConnection.HTTP_OK) {
                    // handle any other errors, like 404, 500,..
                }

                // create JSON object from content
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());

                obj = new JSONObject(getResponseText(in));

            }catch (Exception e){
                e.printStackTrace();
            }

            return obj;


        }

        protected void onPostExecute(Void result) {

            // might want to change "executed" for the returned string passed
            // into onPostExecute() but that is upto you
        }

        private String getResponseText(InputStream inStream) {
            return new Scanner(inStream).useDelimiter("\\A").next();
        }

    }


    public JSONArray getProducts(){

        HttpURLConnection urlConnection = null;
        JSONArray prodList = null;

        try{

            URL url = new URL("https://apiadapter.ad5track.com/ads/americanas?api=b2wads&category_id=229187&size=10&term=Celulares%20e%20Smartphones");

            urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.setConnectTimeout(15000);
            urlConnection.setReadTimeout(15000);

            int statusCode = urlConnection.getResponseCode();
            if (statusCode == HttpURLConnection.HTTP_UNAUTHORIZED) {
                // handle unauthorized (if service requires user login)
            } else if (statusCode != HttpURLConnection.HTTP_OK) {
                // handle any other errors, like 404, 500,..
            }

            // create JSON object from content
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            prodList = new JSONArray(getResponseText(in));

            in.close();


        }catch (Exception e){
            e.printStackTrace();
        }

        return prodList;

    }


    private static String getResponseText(InputStream inStream) {
        return new Scanner(inStream).useDelimiter("\\A").next();
    }

}
