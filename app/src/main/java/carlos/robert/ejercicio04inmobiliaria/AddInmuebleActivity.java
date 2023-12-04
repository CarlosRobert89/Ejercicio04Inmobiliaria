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

        binding.btnCancelarInmuebleAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
        binding.btnCrearInmuebleAdd.setOnClickListener(new View.OnClickListener() {
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
        if (binding.txtDireccionInmuebleAdd.getText().toString().isEmpty()) {
            return null;
        }
        if (binding.txtCiudadInmuebleAdd.getText().toString().isEmpty()) {
            return null;
        }
        if (binding.txtNumeroInmuebleAdd.getText().toString().isEmpty()) {
            return null;
        }
        if (binding.txtProvinciaInmuebleAdd.getText().toString().isEmpty()) {
            return null;
        }
        if (binding.txtCPInmuebleAdd.getText().toString().isEmpty()) {
            return null;
        }
        //if (!binding.ratingBar.callOnClick()) {
        //    return null;}


        Inmueble inmueble = new Inmueble(
                binding.txtDireccionInmuebleAdd.getText().toString(),
                binding.txtNumeroInmuebleAdd.getText().toString(),
                binding.txtCiudadInmuebleAdd.getText().toString(),
                binding.txtProvinciaInmuebleAdd.getText().toString(),
                binding.txtCPInmuebleAdd.getText().toString(),
                binding.ratingBarAdd.getRating()
        );
        return inmueble;
    }
}