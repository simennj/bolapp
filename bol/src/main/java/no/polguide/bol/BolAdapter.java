package no.polguide.bol;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class BolAdapter extends RecyclerView.Adapter<BolAdapter.ItemViewHolder> {

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        LinearLayout item;
        TextView navn;
        TextView kategori;
        TextView pris;
        TextView volum;
        TextView alkohol;
        TextView alkoholpris;


        public ItemViewHolder(View itemView) {
            super(itemView);
            item = (LinearLayout) itemView.findViewById(R.id.item);
            navn = (TextView) itemView.findViewById(R.id.navn);
            kategori = (TextView) itemView.findViewById(R.id.kategori);
            pris = (TextView) itemView.findViewById(R.id.pris);
            volum = (TextView) itemView.findViewById(R.id.volum);
            alkohol = (TextView) itemView.findViewById(R.id.alkohol);
            alkoholpris = (TextView) itemView.findViewById(R.id.alkoholpris);
        }
    }

    List<Product> allItems;
    List<Product> items;

    BolAdapter(List<Product> allItems) {
        this.allItems = allItems;
        this.items = this.allItems;
    }

    public void filter(Filter filter) {
        items.clear();
        notifyDataSetChanged();
        for (Product item : allItems) {
            if (filter.accepted(item)){
                items.add(item);
            }
        }
    }

    public void setItems(List<Product> items) {
        this.allItems = items;
        filter(new Filter());
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        ItemViewHolder itemViewHolder = new ItemViewHolder(item);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.navn.setText(items.get(position).navn);
        holder.kategori.setText(items.get(position).kategori.toString());
        holder.pris.setText(String.format("%.2fkr", items.get(position).pris));
        holder.volum.setText(String.format("%.2fcl", items.get(position).volum));
        holder.alkohol.setText(String.format("%.2f%%", items.get(position).alkohol));
        holder.alkoholpris.setText(String.format("%.2fkr/cl", items.get(position).alkoholpris));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
