package carlos.robert.ejercicio04inmobiliaria;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import carlos.robert.ejercicio04inmobiliaria.databinding.ActivityMainBinding;
import carlos.robert.ejercicio04inmobiliaria.modelos.Inmueble;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ActivityResultLauncher<Intent> launcherInmueble;

    private ArrayList<Inmueble> listaInmuebles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        listaInmuebles = new ArrayList<>();

        inicializarLauncher();

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //lanzar la actividad AddInmueble
                launcherInmueble.launch(new Intent(MainActivity.this, AddInmuebleActivity.class));
            }
        });
    }

    private void inicializarLauncher(){
        launcherInmueble = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode() == RESULT_OK){
                            if(result.getData() != null && result.getData().getExtras() != null){
                                Inmueble inmueble = (Inmueble) result.getData().getExtras().getSerializable("INMUEBLE");
                                listaInmuebles.add(inmueble);
                                mostrarInmuebles();
                            }else{
                                Toast.makeText(MainActivity.this, "No llegaron los datos...", Toast.LENGTH_SHORT).show();
                            }

                        }else{
                            Toast.makeText(MainActivity.this, "ACCIÓN CANCELADA", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }

    private void mostrarInmuebles() {
        //eliminar lo que haya en el Linear Layout
        binding.contentMain.contenedorMain.removeAllViews();

        for (Inmueble i:listaInmuebles) {
            LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);

            View inmuebleView = layoutInflater.inflate(R.layout.inmueble_fila_view, null);
            TextView txtDireccion = inmuebleView.findViewById(R.id.direccionView);
            TextView txtNumero = inmuebleView.findViewById(R.id.numeroView);
            TextView txtCiudad = inmuebleView.findViewById(R.id.ciudadView);
            RatingBar Valoracion = inmuebleView.findViewById(R.id.ratingBarView);

            txtDireccion.setText(i.getDireccion());
            txtNumero.setText(i.getNumero());
            txtCiudad.setText(i.getCiudad());
            Valoracion.setRating((int) i.getValoracion());

            binding.contentMain.contenedorMain.addView(inmuebleView);
        }
    }
}