package com.marwa.stocksimple.db;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.marwa.stocksimple.MainActivity;
import com.marwa.stocksimple.R;

import java.util.List;


public class StockAdapter extends ArrayAdapter<Stock> {

    public static final int GALLERY_INTENT_CALLED = 1;
    public static final int GALLERY_KITKAT_INTENT_CALLED = 2;
    private final MainActivity activity;

    public StockAdapter(MainActivity context, List<Stock> stocks) {
        super(context, 0, stocks);
        this.activity = context;
        if (Build.VERSION.SDK_INT < 19) {
            Intent intent = new Intent();
            intent.setType("*/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);

        } else {
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("*/*");
        }

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        StockViewHolder viewHolder = (StockViewHolder) convertView.getTag();
        if (viewHolder == null) {
            viewHolder = new StockViewHolder();
            viewHolder.nomProduit = (TextView) (TextView) convertView.findViewById(R.id.nomProduit);
            viewHolder.quantite = (TextView) convertView.findViewById(R.id.quantite);
            viewHolder.prix = (TextView) convertView.findViewById(R.id.prix);
            viewHolder.sale = (ImageView) convertView.findViewById(R.id.sale);
            viewHolder.image = (ImageView) convertView.findViewById(R.id.image_view);
            convertView.setTag(viewHolder);
        }

        final Stock stock = getItem(position);

        viewHolder.nomProduit.setText(stock.getNomProduit());
        viewHolder.quantite.setText(stock.getQuantite()+"");
        viewHolder.prix.setText(stock.getPrix());
        viewHolder.image.setImageURI(Uri.parse(stock.getImage()));


        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.clickOnViewItem(stock.getId());
            }
        });

        viewHolder.sale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.clickOnSale(stock.getId(),stock.getQuantite());
            }
        });

        return convertView;
    }

    private class StockViewHolder {
        public TextView nomProduit;
        public TextView quantite;
        public TextView prix;
        public ImageView sale;
        public ImageView image;
    }
}