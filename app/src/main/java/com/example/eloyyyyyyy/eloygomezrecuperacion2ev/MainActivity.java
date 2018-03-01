package com.example.eloyyyyyyy.eloygomezrecuperacion2ev;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView tvTitulo, tvLatitud, tvLongitud, tvCodigo;
    EditText etLocalidad;
    Button btnBuscar;

    private static final String url="http://192.168.31.12/Ciudades/city.list.json";
    ArrayList<Localidad> paises=new ArrayList<Localidad>();
    Localidad l=new Localidad();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTitulo=(TextView)findViewById(R.id.tvTitulo);
        tvLatitud=(TextView)findViewById(R.id.tvLatitud);
        tvLongitud=(TextView)findViewById(R.id.tvLongitud);
        tvCodigo=(TextView)findViewById(R.id.tvCodigo);
        etLocalidad=(EditText) findViewById(R.id.etLocalidad);
        btnBuscar=(Button)findViewById(R.id.btnBuscar);

        SharedPreferences prefe=getSharedPreferences("datos", Context.MODE_PRIVATE);
        etLocalidad.setText(prefe.getString("Localidad",""));

        RequestQueue request= Volley.newRequestQueue(this);

        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    JSONArray jsonArray=new JSONArray(response.toString(0));

                    for(int i=0; i<jsonArray.length(); i++){

                        String nombreLocalidad=jsonArray.getJSONObject(i).getString("name");
                        l.setNombre(nombreLocalidad);
                        System.out.println(l.getNombre());

                        String codigoPais=jsonArray.getJSONObject(i).getString("country");
                        l.setCodigoPais(codigoPais);

                        String latitud=jsonArray.getJSONObject(i).getJSONObject("coord").getString("lat");
                        l.setLatitud(latitud);

                        String longitud=jsonArray.getJSONObject(i).getJSONObject("coord").getString("lon");
                        l.setLongitud(longitud);

                        paises.add(l);

                        System.out.println(l.getNombre());

                        btnBuscar.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                for(int i=0; i< paises.size(); i++) {
                                    System.out.println(paises.get(i).getNombre());
                                    System.out.println(l.getNombre());
                                    String texto=etLocalidad.getText().toString();

                                    if(texto.equals(paises.get(i).getNombre())){
                                        tvLatitud.setText("Latitud: "+paises.get(i).getLatitud());
                                        tvLongitud.setText("Longitud: "+paises.get(i).getLongitud());
                                        tvCodigo.setText("Codigo de país: "+paises.get(i).getCodigoPais());

                                        Toast.makeText(getApplicationContext(), "Se ha encontrado en el fichero", Toast.LENGTH_LONG).show();
                                    }
                                    else{
                                        tvLatitud.setText("Latitud: ");
                                        tvLongitud.setText("Longitud: ");
                                        tvCodigo.setText("Codigo de país: ");
                                        Toast.makeText(getApplicationContext(), "No se ha podido encontrar en el fichero", Toast.LENGTH_LONG).show();
                                    }
                                }

                            }
                        });
                    }





                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        request.add(jsonArrayRequest);

    }

    public void mostrarMapa(View v){
        Intent e = new Intent(getApplicationContext(), Maps.class);
        e.putExtra("objeto", (Serializable) l);
        startActivity(e);
    }

    public void guardarLoc(View v){
        SharedPreferences preferencias=getSharedPreferences("datos", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferencias.edit();
        editor.putString("Localidad",etLocalidad.getText().toString());
        editor.commit();
        Toast.makeText(this, "Guardado", Toast.LENGTH_LONG).show();
    }

}
