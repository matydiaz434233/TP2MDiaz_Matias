package com.matisoft.tp2mdiaz_matias;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class MainActivityViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Double> mutableResultado;
    private final double tasaDolarEuro = 0.93 ; // Tasa de conversión de dólar a euro (25-08-2023)
    private final double tasaEuroDolar = 1.08 ; // Tasa de conversión de euro a dólar (25-08-2023)

    // Constructor del ViewModel
    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }
    // Obtener el LiveData mutable para el resultado de la conversión
    public LiveData<Double> getMutableResultado(){
        if(mutableResultado == null){
            mutableResultado = new MutableLiveData<>();
        }
        return mutableResultado;
    }

    // Método para realizar la conversión de moneda
    public void convertir(String dolar, String euro, boolean estado) {
        try {
            Double valorDolar = Double.parseDouble(dolar); // Convertir string a double
            Double valorEuro = Double.parseDouble(euro);   // Convertir string a double

            // Realizar la conversión según el estado del radio button
            if (estado) {
                mutableResultado.setValue(valorDolar * tasaDolarEuro); // Convertir de dólar a euro
            } else {
                mutableResultado.setValue(valorEuro * tasaEuroDolar); // Convertir de euro a dólar
            }
        } catch (NumberFormatException e) {
            mutableResultado.setValue(0.0); // Establecer resultado a 0 en caso de error
            Toast.makeText(context, "Número Inválido", Toast.LENGTH_LONG).show(); // Mostrar mensaje de error en pantalla
        }
    }
}

