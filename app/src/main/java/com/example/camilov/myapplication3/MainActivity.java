package com.example.camilov.myapplication3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.camilov.myapplication3.apiService.apiService;
import com.example.camilov.myapplication3.models.Restaurante;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    //btn_salir
    public void salir(View v) {
        finish();
    }


    private Retrofit retrofit;
    private static final String TAG = "Restaurante";
    private RecyclerView recyclerView;
    private boolean aptoParaCargar;
    private ListaRestauranteAdapter listaRestauranteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        listaRestauranteAdapter = new ListaRestauranteAdapter(this);
        recyclerView.setAdapter(listaRestauranteAdapter);

        recyclerView.setHasFixedSize(true);
        final GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();

                    if (aptoParaCargar) {
                        if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                            Log.i(TAG, " Final.");

                            aptoParaCargar = false;
                            obtenerLista();
                        }
                    }
                }
            }
        });
//pagina
        retrofit = new Retrofit.Builder()
                .baseUrl("https://www.datos.gov.co/resource/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        aptoParaCargar = true;
        obtenerLista();
    }

    public void acercaDe(View view) {
        Intent i = new Intent(this, Main2Activity.class );
        startActivity(i);
    }


    private void obtenerLista() {

        apiService service = retrofit.create(apiService.class);
        Call<ArrayList<Restaurante>> autoRespuestaCall = service.obtenerListaRestaurante();

        autoRespuestaCall.enqueue(new Callback<ArrayList<Restaurante>>() {
            @Override
            public void onResponse(Call<ArrayList<Restaurante>> call, Response<ArrayList<Restaurante>> response) {
                if(response.isSuccessful()){
                    ArrayList lista = response.body();
                    listaRestauranteAdapter.adicionarListaRestaurante(lista);
                }
                else
                {
                    Log.e(TAG, "onResponse: "+response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Restaurante>> call, Throwable t) {
                Log.e(TAG," onFailure: "+t.getMessage());
            }
        });

    }


}

