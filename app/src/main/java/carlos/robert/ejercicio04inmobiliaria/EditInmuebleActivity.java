package carlos.robert.ejercicio04inmobiliaria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import carlos.robert.ejercicio04inmobiliaria.databinding.ActivityEditInmuebleBinding;
import carlos.robert.ejercicio04inmobiliaria.modelos.Inmueble;

public class EditInmuebleActivity extends AppCompatActivity {
    private ActivityEditInmuebleBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_inmueble);
        binding = ActivityEditInmuebleBinding.inflate(getLayoutInflater());

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Inmueble inmueble = (Inmueble) bundle.getSerializable("INMUEBLE");

        rellenarInformacion(inmueble);
        
        binding.btnEditarInmuebleEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //crear un inmueble
                Inmueble inm = crearInmueble();
                
                //enviar inmueble al principal
                if(inm != null){
                    Intent intentVolver = new Intent();
                    Bundle bundleVolver = new Bundle();
                    bundleVolver.putSerializable("INMUEBLE", inm);
                    intentVolver.putExtras(bundleVolver);
                    finish();
                }else{
                    Toast.makeText(EditInmuebleActivity.this, "FALTAN DATOS", Toast.LENGTH_SHORT).show();
                }
            }
        });
        binding.btnEliminarInmuebleEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_OK);
                finish();
            }
        });
    }

    private void rellenarInformacion(Inmueble inmueble) {
        binding.txtDireccionInmuebleEdit.setText(inmueble.getDireccion());
        binding.txtNumeroInmuebleEdit.setText(inmueble.getDireccion());
        binding.txtCiudadInmuebleEdit.setText(inmueble.getDireccion());
        binding.txtProvinciaInmuebleEdit.setText(inmueble.getDireccion());
        binding.txtCPInmuebleEdit.setText(inmueble.getDireccion());
        binding.ratingBarEdit.setRating(inmueble.getValoracion());
    }
    private Inmueble crearInmueble() {
        if (binding.txtDireccionInmuebleEdit.getText().toString().isEmpty()) {
            return null;
        }
        if (binding.txtCiudadInmuebleEdit.getText().toString().isEmpty()) {
            return null;
        }
        if (binding.txtNumeroInmuebleEdit.getText().toString().isEmpty()) {
            return null;
        }
        if (binding.txtProvinciaInmuebleEdit.getText().toString().isEmpty()) {
            return null;
        }
        if (binding.txtCPInmuebleEdit.getText().toString().isEmpty()) {
            return null;
        }
        //if (!binding.ratingBar.callOnClick()) {
        //    return null;}


        Inmueble inmueble = new Inmueble(
                binding.txtDireccionInmuebleEdit.getText().toString(),
                binding.txtNumeroInmuebleEdit.getText().toString(),
                binding.txtCiudadInmuebleEdit.getText().toString(),
                binding.txtProvinciaInmuebleEdit.getText().toString(),
                binding.txtCPInmuebleEdit.getText().toString(),
                binding.ratingBarEdit.getRating()
        );
        return inmueble;
    }
}