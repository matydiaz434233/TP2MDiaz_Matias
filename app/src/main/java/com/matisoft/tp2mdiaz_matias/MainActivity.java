package com.matisoft.tp2mdiaz_matias;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;

import com.matisoft.tp2mdiaz_matias.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Inflar el diseño de la actividad utilizando el enlace generado
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Crear una instancia del ViewModel asociada a la actividad
        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainActivityViewModel.class);

        // Configurar el estado inicial de los elementos de la interfaz de usuario
        binding.idDolarAEuro.setChecked(true); // Seleccionar conversión de dólar a euro por defecto
        binding.idDolares.setEnabled(true); // Habilitar el campo de dólares
        binding.idEuros.setEnabled(false); // Deshabilitar el campo de euros
        binding.idEuros.setText("0"); // Establecer el valor inicial de euros en 0

        // Manejar el clic en el radio button "Dólar a Euro"
        binding.idDolarAEuro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.idDolares.setEnabled(true); // Habilitar campo de dólares
                binding.idEuros.setEnabled(false); // Deshabilitar campo de euros
                binding.idEuros.setText("0"); // Reiniciar valor de euros
                binding.idDolares.setText(""); // Limpiar campo de dólares
                binding.idDolares.requestFocus(); // Establecer el foco en el campo de dólares
            }
        });

        // Manejar el click en el radio button "Euro a Dólar"
        binding.idEuroADolar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.idDolares.setEnabled(false); // Deshabilitar campo de dólares
                binding.idEuros.setEnabled(true); // Habilitar campo de euros
                binding.idDolares.setText("0"); // Reiniciar valor de dólares
                binding.idEuros.setText(""); // Limpiar campo de euros
                binding.idEuros.requestFocus(); // Establecer el foco en el campo de euros
            }
        });

        // Observar cambios en el resultado mutable y actualizar la interfaz en consecuencia
        viewModel.getMutableResultado().observe(this, new Observer<Double>() {
            @Override
            public void onChanged(Double mutableResultado) {
                binding.idCambio.setText(String.valueOf(mutableResultado)); // Mostrar el resultado de la conversión
            }
        });

        // Manejar el click en el botón "Convertir"
        binding.btConvertir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String euro = binding.idEuros.getText().toString(); // Obtener el valor de euros
                String dolar = binding.idDolares.getText().toString(); // Obtener el valor de dólares
                boolean radioEstado = binding.idDolarAEuro.isChecked(); // Obtener el estado del radio button
                viewModel.convertir(dolar, euro, radioEstado); // Realizar la conversión a través del ViewModel
            }
        });
    }
}
