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
import android.view.*;
import android.widget.CheckBox;
import android.widget.TextView;
import com.appyvet.rangebar.RangeBar;
import com.bignerdranch.android.multiselector.MultiSelector;
import com.bignerdranch.android.multiselector.SwappingHolder;
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
    private MultiSelector kategoriselector = new MultiSelector();
    private Kategori[] kategorier = new Kategori[9];

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
        mDrawerToggle = new ActionBarDrawerToggle(this, Drawer, toolbar, R.string.openDrawer, R.string.closeDrawer) {

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
        ((TextView) findViewById(R.id.landFilter)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter.land = s.toString().toLowerCase();
                itemsAdapter.filter(filter);
            }
        });
        ((TextView) findViewById(R.id.varenavnFilter)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter.produsent = s.toString().toLowerCase();
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
        ((RangeBar) findViewById(R.id.volumFilter)).setOnRangeBarChangeListener(new RangeBar.OnRangeBarChangeListener() {
            @Override
            public void onRangeChangeListener(RangeBar rangeBar, int min, int max, String s_min, String s_max) {
                filter.volum_min = min * 5;
                filter.volum_max = max * 5;
                itemsAdapter.filter(filter);
            }
        });
        ((RangeBar) findViewById(R.id.alkoholFilter)).setOnRangeBarChangeListener(new RangeBar.OnRangeBarChangeListener() {
            @Override
            public void onRangeChangeListener(RangeBar rangeBar, int min, int max, String s_min, String s_max) {
                filter.alkohol_min = min;
                filter.alkohol_max = max;
                itemsAdapter.filter(filter);
            }
        });
        ((RangeBar) findViewById(R.id.alkoholprisFilter)).setOnRangeBarChangeListener(new RangeBar.OnRangeBarChangeListener() {
            @Override
            public void onRangeChangeListener(RangeBar rangeBar, int min, int max, String s_min, String s_max) {
                filter.alkoholpris_min = min * 1;
                filter.alkoholpris_max = max * 1;
                itemsAdapter.filter(filter);
            }
        });

        kategorier[0] = Kategori.alkoholfritt;
        kategorier[1] = Kategori.ol;
        kategorier[2] = Kategori.aperitif_dessert;
        kategorier[3] = Kategori.cider_och_blanddrycker;
        kategorier[4] = Kategori.mousserande_viner;
        kategorier[5] = Kategori.roda_viner;
        kategorier[6] = Kategori.roseviner;
        kategorier[7] = Kategori.sprit;
        kategorier[8] = Kategori.vita_viner;

        KategoriAdapter kategoriAdapter = new KategoriAdapter();
        RecyclerView kategoriView = (RecyclerView) findViewById(R.id.kategori);
        kategoriView.setAdapter(kategoriAdapter);
        kategoriView.setLayoutManager(new LinearLayoutManager(this));
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

    private class KategoriHolder extends SwappingHolder
            implements View.OnClickListener {
        private final CheckBox mSolvedCheckBox;
        private Kategori kategori;

        public KategoriHolder(View itemView, Kategori kategori) {
            super(itemView, kategoriselector);
            kategoriselector.setSelectable(true);
            mSolvedCheckBox = (CheckBox) itemView.findViewById(R.id.solvedCheckBox);
            itemView.setOnClickListener(this);
        }

        public void bindKategori(Kategori kategori) {
            this.kategori = kategori;
            mSolvedCheckBox.setText(kategori.toString());
        }

        @Override
        public void onClick(View v) {
            if (mSolvedCheckBox.isChecked()) {
                filter.kategorier.remove(this.kategori);
            } else {
                filter.kategorier.add(this.kategori);
            }
            if (kategoriselector.tapSelection(this)) {
                System.out.println("add");
            } else {
                System.out.println("remove");

            }
            mSolvedCheckBox.setChecked(filter.kategorier.contains(this.kategori));
            itemsAdapter.filter(filter);
        }
    }

    private class KategoriAdapter extends RecyclerView.Adapter<KategoriHolder> {

        @Override
        public KategoriHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new KategoriHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.kategori, parent, false), Kategori.alkoholfritt);
        }

        @Override
        public void onBindViewHolder(KategoriHolder holder, int position) {
            Kategori kategori = kategorier[position];
            holder.bindKategori(kategori);
        }

        @Override
        public int getItemCount() {
            return 9;
        }
    }
}
