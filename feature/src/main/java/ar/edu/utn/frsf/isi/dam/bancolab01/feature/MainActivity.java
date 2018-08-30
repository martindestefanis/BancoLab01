package ar.edu.utn.frsf.isi.dam.bancolab01.feature;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import ar.edu.utn.frsf.isi.dam.bancolab01.modelo.Cliente;
import ar.edu.utn.frsf.isi.dam.bancolab01.modelo.PlazoFijo;

public class MainActivity extends AppCompatActivity {

    private PlazoFijo pf;
    private Cliente cliente;
    private EditText edtMonto;
    private Button btnHacerPlazoFijo;
    private SeekBar seekBar;
    private TextView tvDiasSeleccionados;
    int progress = 10;
    private TextView tvInteres;
    private CheckBox chbAceptoTerminos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pf = new PlazoFijo(getResources().getStringArray(R.array.tasa1));
        cliente = new Cliente();

        edtMonto= (EditText) findViewById(R.id.edtMonto);
        String value = edtMonto.getText().toString();
        Double monto = Double.parseDouble(value);
        pf.setMonto(monto);

        btnHacerPlazoFijo = (Button) findViewById(R.id.btnHacerPlazoFijo);
        btnHacerPlazoFijo.setEnabled(false);

        seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setMax(180);
        seekBar.setProgress(progress);

        tvDiasSeleccionados = (TextView) findViewById(R.id.tvDiasSeleccionados);
        tvDiasSeleccionados.setText(progress + " dias de plazo");

        tvInteres = (TextView) findViewById(R.id.tvInteres);

        chbAceptoTerminos = (CheckBox) findViewById(R.id.chbAceptoTerminos);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean fromUser) {
                progress = i;
                tvDiasSeleccionados.setText(progress + " dias de plazo");
                pf.setDias(progress);
                pf.intereses();
                edtMonto= (EditText) findViewById(R.id.edtMonto);
                String value = edtMonto.getText().toString();
                Double monto = Double.parseDouble(value);
                pf.setMonto(monto);
                tvInteres.setText("$"+pf.intereses().toString());
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        chbAceptoTerminos.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(chbAceptoTerminos.isChecked()) {
                    btnHacerPlazoFijo.setEnabled(true);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Es obligatorio aceptar las condiciones",Toast.LENGTH_LONG).show();
                }

            }
        });

    }

}
