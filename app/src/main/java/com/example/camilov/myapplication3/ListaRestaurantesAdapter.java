package com.example.camilov.myapplication3;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.camilov.myapplication3.models.Restaurante;

import java.util.ArrayList;



 class ListaRestauranteAdapter extends RecyclerView.Adapter<ListaRestauranteAdapter.ViewHolder> {
    private ArrayList<Restaurante> dataset;

    private Context context;

    public ListaRestauranteAdapter(Context context) {
        this.context = context;
        dataset = new ArrayList<>();

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_lista_restaurantes_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Restaurante c = dataset.get(position);
        holder.clase.setText(c.getRestaurante());


    }








    @Override
    public int getItemCount() {

        return dataset.size();
    }

    public void adicionarListaRestaurante(ArrayList<Restaurante> listaRestaurante) {
        dataset.addAll(listaRestaurante);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView clase;

        public ViewHolder(View itemView) {
            super(itemView);

            clase = (TextView) itemView.findViewById(R.id.clase);
        }
    }
}
