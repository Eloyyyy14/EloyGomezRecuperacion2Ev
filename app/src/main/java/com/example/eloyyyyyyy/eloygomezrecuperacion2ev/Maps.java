package com.example.eloyyyyyyy.eloygomezrecuperacion2ev;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Maps extends FragmentActivity implements OnMapReadyCallback {

    GoogleMap mapa;
    SupportMapFragment fragmentMapa;
    Localidad loc;
    double latitud;
    double longitud;
    LatLng posicion;
    MarkerOptions marcador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        fragmentMapa = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.fragMapa);
        fragmentMapa.getMapAsync(this);

        loc = (Localidad) getIntent().getExtras().getSerializable("objeto");
    }


    public void onMapReady(GoogleMap googleMap) {
        mapa = googleMap;
        mapa.setMapType(googleMap.MAP_TYPE_NORMAL);
        mapa.getUiSettings().setZoomControlsEnabled(true);


        latitud = Double.parseDouble(loc.getLatitud());
        longitud = Double.parseDouble(loc.getLongitud());


        posicion = new LatLng(latitud, longitud);
        mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(posicion, 2));

        String info = "Nombre: " + loc.getNombre() + " Codigo: " + loc.getCodigoPais();

        marcador = new MarkerOptions().title(loc.getNombre()).position(posicion).snippet(info);

        mapa.addMarker(marcador);
    }
}
