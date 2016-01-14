package no.polguide.bol;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    ProductGenerator generator;

    private Toolbar toolbar;

    RecyclerView itemsView;
    BolAdapter itemsAdapter;
    LinearLayoutManager itemsLayoutManager;
    DrawerLayout Drawer;

    ActionBarDrawerToggle mDrawerToggle;

    List<Product> allItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        itemsView = (RecyclerView) findViewById(R.id.Items);

        allItems.add(new Product(1, "navn", "beskrivelse", "kategori", "produsent", "land", 99.99, 33, 4.7, 12.34, "url"));

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

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
