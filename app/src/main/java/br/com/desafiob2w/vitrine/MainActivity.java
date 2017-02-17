package br.com.desafiob2w.vitrine;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.desafiob2w.vitrine.util.ProductListAdapter;

public class MainActivity extends AppCompatActivity {

    ListView prodList;
    List jsonProdList = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prodList = (ListView) findViewById(R.id.productList);

        new ApiOperation().execute();

    }


    private class ApiOperation extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {

            HttpURLConnection urlConnection = null;

            try{

                URL url = new URL("https://apiadapter.ad5track.com/ads/americanas?api=b2wads&category_id=229187&size=10&term=Celulares%20e%20Smartphones");

                urlConnection = (HttpURLConnection) url.openConnection();

                urlConnection.setConnectTimeout(15000);
                urlConnection.setReadTimeout(15000);

                InputStream in = new BufferedInputStream(urlConnection.getInputStream());

                JSONArray jsonProdArray = new JSONArray(getResponseText(in));

                if (jsonProdArray != null) {

                    for (int i=0;i<jsonProdArray.length();i++){

                        JSONObject obj = jsonProdArray.getJSONObject(i);

                        try {

                            URL imgUrl = new URL(obj.getString("img"));
                            Bitmap bmp = BitmapFactory.decodeStream(imgUrl.openConnection().getInputStream());
                            obj.put("imgobj",bmp);

                        }catch (MalformedURLException e){
                            e.printStackTrace();
                        }catch (Exception ex){
                            ex.printStackTrace();
                        }

                        jsonProdList.add(obj);

                    }

                }

                in.close();

            }catch (Exception e){
                e.printStackTrace();
            }

            return null;
        }

        protected void onPostExecute(Void result) {

            ProductListAdapter adapter = new ProductListAdapter(MainActivity.this, jsonProdList);
            prodList.setAdapter(adapter);

        }

        private String getResponseText(InputStream inStream) {
            return new Scanner(inStream).useDelimiter("\\A").next();
        }

    }
}
