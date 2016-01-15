package no.polguide.bol;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import com.appyvet.rangebar.RangeBar;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    ProductGenerator generator;
    RecyclerView itemsView;
    BolAdapter itemsAdapter;
    LinearLayoutManager itemsLayoutManager;
    DrawerLayout Drawer;
    ActionBarDrawerToggle mDrawerToggle;
    List<Product> allItems = new ArrayList<>();
    Filter filter = new Filter();
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        itemsView = (RecyclerView) findViewById(R.id.Items);

        allItems.add(new Product(1, "navn", "beskrivelse", Kategori.alkoholfritt, "produsent", "land", 9.99, 100, 99.99, 0.01, "url"));

        itemsAdapter = new BolAdapter(allItems);
        generator = new ProductGenerator();
        try {
            generator.getPage(new Callback<List<Product>>() {
                @Override
                public void onResponse(Response<List<Product>> response) {
                    allItems = response.body();
                    System.out.println("Got response");
                    itemsAdapter.setItems(allItems);
                    itemsAdapter.notifyDataSetChanged();
                    System.out.println(itemsAdapter.getItemCount());
                }

                @Override
                public void onFailure(Throwable t) {
                    System.out.println("NOOOOOOOOOO");
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        itemsView.setAdapter(itemsAdapter);

        itemsLayoutManager = new LinearLayoutManager(this);

        itemsView.setLayoutManager(itemsLayoutManager);


        Drawer = (DrawerLayout) findViewById(R.id.DrawerLayout);
        mDrawerToggle = new ActionBarDrawerToggle(this,Drawer,toolbar,R.string.openDrawer,R.string.closeDrawer){

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

        };
        Drawer.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        ((TextView) findViewById(R.id.varenavnFilter)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter.navn = s.toString().toLowerCase();
                itemsAdapter.filter(filter);
            }
        });
        ((RangeBar) findViewById(R.id.prisFilter)).setOnRangeBarChangeListener(new RangeBar.OnRangeBarChangeListener() {
            @Override
            public void onRangeChangeListener(RangeBar rangeBar, int min, int max, String s_min, String s_max) {
                filter.pris_min = min * 10;
                filter.pris_max = max * 10;
                itemsAdapter.filter(filter);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.refresh) {
            itemsAdapter.filter(filter);
            return true;
        } else if (id == R.id.filter) {
            Drawer.openDrawer(Gravity.RIGHT);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
