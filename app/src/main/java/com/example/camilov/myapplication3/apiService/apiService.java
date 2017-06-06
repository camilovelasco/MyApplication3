package com.example.camilov.myapplication3.apiService;

import com.example.camilov.myapplication3.models.Restaurante;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Camilo V on 5/06/2017.
 */

public interface apiService
{
    @GET("jkwf-2qh6.json")
    Call<ArrayList<Restaurante>> obtenerListaRestaurante();
}
