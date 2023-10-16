package carlos.robert.ejercicio04inmobiliaria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import carlos.robert.ejercicio04inmobiliaria.databinding.ActivityAddInmuebleBinding;
import carlos.robert.ejercicio04inmobiliaria.modelos.Inmueble;

public class AddInmuebleActivity extends AppCompatActivity {

    private ActivityAddInmuebleBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_add_inmueble);
        binding = ActivityAddInmuebleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnCancelarInmueble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
        binding.btnCrearInmueble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Añadir lo que escribe el usuario
                Inmueble inmueble = crearInmueble();

                if (inmueble == null) {
                    Toast.makeText(AddInmuebleActivity.this, "FALTAN DATOS", Toast.LENGTH_SHORT).show();
                } else {
                    //Enviar la información al principal junto con resultado Ok
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("INMUEBLE", inmueble);
                    intent.putExtras(bundle);
                    setResult(RESULT_OK, intent);

                    //Terminar
                    finish();
                }
            }
        });
    }

    private Inmueble crearInmueble() {
        if (binding.txtDireccionInmueble.getText().toString().isEmpty()) {
            return null;
        }
        if (binding.txtCiudadInmueble.getText().toString().isEmpty()) {
            return null;
        }
        if (binding.txtNumeroInmueble.getText().toString().isEmpty()) {
            return null;
        }
        if (binding.txtProvinciaInmueble.getText().toString().isEmpty()) {
            return null;
        }
        if (binding.txtCPInmueble.getText().toString().isEmpty()) {
            return null;
        }
        //if (!binding.ratingBar.callOnClick()) {
        //    return null;}


        Inmueble inmueble = new Inmueble(
                binding.txtDireccionInmueble.getText().toString(),
                binding.txtNumeroInmueble.getText().toString(),
                binding.txtCiudadInmueble.getText().toString(),
                binding.txtProvinciaInmueble.getText().toString(),
                binding.txtCPInmueble.getText().toString(),
                binding.ratingBar.getRating()
        );
        return inmueble;
    }
}