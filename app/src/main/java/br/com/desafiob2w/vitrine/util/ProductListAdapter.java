package br.com.desafiob2w.vitrine.util;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import org.json.JSONException;
import org.json.JSONObject;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.List;
import br.com.desafiob2w.vitrine.R;

/**
 * Created by vanilson on 17/02/17.
 */

public class ProductListAdapter extends ArrayAdapter {


    private final Activity context;

    List prods;

    public ProductListAdapter(Activity context, List prodList){

        super(context, R.layout.prod_list_adap_view, (List) prodList);

        this.context = context;
        this.prods = prodList;

    }

    @Override
    public View getView(int position, View view, ViewGroup parent){



        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.prod_list_adap_view, null, true);

        try {

            JSONObject prod = (JSONObject) prods.get(position);

            TextView accname = (TextView) rowView.findViewById(R.id.tv_prd_name);
            accname.setText(prod.getString("name"));

            TextView accvalue = (TextView) rowView.findViewById(R.id.tv_prd_value);
            accvalue.setText(NumberFormat.getCurrencyInstance().format(prod.getLong("price")));

            ImageView imageView = (ImageView) rowView.findViewById(R.id.iv_prd_img);
            imageView.setImageBitmap((Bitmap) prod.get("imgobj"));

            try {

                URL url = new URL(prod.getString("img"));
                Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());

            }catch (MalformedURLException e){

                e.printStackTrace();

            }catch (Exception ex){

                ex.printStackTrace();

            }


        } catch (JSONException e) {

            e.printStackTrace();

        }

        return rowView;
    }

}
