package com.marwa.stocksimple;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.marwa.stocksimple.db.AppDatabase;
import com.marwa.stocksimple.db.Stock;
import com.marwa.stocksimple.db.StockAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final static String LOG_TAG = MainActivity.class.getCanonicalName();
    private AppDatabase DB;
    StockAdapter stockAdapter;
    private List<Stock> dataList;
    ListView listView;

    int lastVisibleItem = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                startActivity(intent);
            }
        });

        listView = (ListView) findViewById(R.id.list_view);
        View emptyView = findViewById(R.id.empty_view);
        listView.setEmptyView(emptyView);


        DB = AppDatabase.getDatabase(this.getApplication());
        dataList = DB.stockDao().getAll();

        stockAdapter = new StockAdapter(MainActivity.this, dataList);

        listView.setAdapter(stockAdapter);
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == 0) return;
                final int currentFirstVisibleItem = view.getFirstVisiblePosition();
                if (currentFirstVisibleItem > lastVisibleItem) {
                    fab.show();
                } else if (currentFirstVisibleItem < lastVisibleItem) {
                    fab.hide();
                }
                lastVisibleItem = currentFirstVisibleItem;
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        stockAdapter.clear();
        stockAdapter.addAll(DB.stockDao().getAll());

        stockAdapter.notifyDataSetChanged();
    }

    public void clickOnViewItem(long id) {
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra("itemId", id);
        startActivity(intent);
    }

    public void clickOnSale(long id, int quantity) {

        if (quantity > 0) {
            Stock stock = DB.stockDao().getById(id);
            int position = dataList.indexOf(stock);

            stock.setQuantite(quantity - 1);
            DB.stockDao().update(stock);
            stockAdapter.remove(stock);
            stockAdapter.insert(stock,position);
            stockAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_dummy_data:
                addDummyData();
                stockAdapter.clear();
                stockAdapter.addAll(DB.stockDao().getAll());

                stockAdapter.notifyDataSetChanged();
        }
        return super.onOptionsItemSelected(item);
    }

    private void addDummyData() {
        Stock Avril = new Stock(
                "Savon Avril",
                "15 DH",
                45,
                "Avril",
                "+212 0615402135",
                "avril@mail.com",
                "android.resource://com.marwa.stocksimple/drawable/savonavril");
        DB.stockDao().insert(Avril);

        Stock savonmojito = new Stock(
                "Savon Mojito",
                "20 DH",
                24,
                "MOJITO INDUSTRY",
                "+212 0667447105",
                "mojito@sweet.com",
                "android.resource://com.marwa.stocksimple/drawable/savonmojito");
        DB.stockDao().insert(savonmojito);

        Stock LOREAL = new Stock(
                "Loreal",
                "35 DH",
                74,
                "LOREAL PARIS",
                "+212 06304050",
                "loreal@gmail.com",
                "android.resource://com.marwa.stocksimple/drawable/loreal");
        DB.stockDao().insert(LOREAL);

        Stock Palmolive = new Stock(
                "Savon Palmolive",
                "12 DH",
                44,
                "Palmolive",
                "+212 30502040",
                "palmolive@gmail.com",
                "android.resource://com.marwa.stocksimple/drawable/savonpalmolive");
        DB.stockDao().insert(Palmolive);

        Stock Ultra = new Stock(
                "Ultra Doux",
                "35 DH",
                34,
                "GARNIER",
                "+212 06300300",
                "ultraDoux@gmail.com",
                "android.resource://com.marwa.stocksimple/drawable/ultradoux");
        DB.stockDao().insert(Ultra);

        Stock Colgate = new Stock(
                "Colgate",
                "20 DH",
                26,
                "Colgate industry",
                "+212 05060708",
                "colgate@gmail.com",
                "Colgate.resource://com.marwa.stocksimple/drawable/dentifricecolgate");
        DB.stockDao().insert(Colgate);

        Stock Sano = new Stock(
                "Sano Gyl",
                "17 DH",
                54,
                "Sanogyl Sanitaire",
                "+212 30 60 50 20",
                "sanogyl@gmail.com",
                "android.resource://com.marwa.stocksimple/drawable/dentifricesanogyl");
        DB.stockDao().insert(Sano);

        Stock Signal = new Stock(
                "Signal",
                "10 DH",
                12,
                "Signal",
                "+212 05060909",
                "signal@gmail.com",
                "android.resource://com.marwa.stocksimple/drawable/dentifrisesignal");
        DB.stockDao().insert(Signal);

        Stock Dove = new Stock(
                "Dove",
                "25 DH",
                62,
                "Dove shampoo",
                "+212 03040560",
                "dove@gmail.com",
                "android.resource://com.marwa.stocksimple/drawable/dove");
        DB.stockDao().insert(Dove);

        Stock Elseve = new Stock(
                "Elseve",
                "34 DH",
                22,
                "Elseve Maroc",
                "+212 300 400 500",
                "elseve@gmail.com",
                "android.resource://com.marwa.stocksimple/drawable/elseve");
        DB.stockDao().insert(Elseve);
    }
}
