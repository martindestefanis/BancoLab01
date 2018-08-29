package ar.edu.utn.frsf.isi.dam.bancolab01.feature;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

import ar.edu.utn.frsf.isi.dam.bancolab01.modelo.Cliente;
import ar.edu.utn.frsf.isi.dam.bancolab01.modelo.PlazoFijo;

public class MainActivity extends AppCompatActivity {
    private PlazoFijo pf;
    private Cliente cliente;
    private EditText edtMonto;
    private Button btnHacerPlazoFijo;
    private SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Resources res = getResources();
        String[] plazo = res.getStringArray(R.array.tasa1);
        pf = new PlazoFijo(plazo);

        cliente = new Cliente();
        btnHacerPlazoFijo = (Button) findViewById(R.id.btnHacerPlazoFijo);
        edtMonto= (EditText) findViewById(R.id.edtMonto);
        btnHacerPlazoFijo.setEnabled(false);
        seekBar = (SeekBar) findViewById(R.id.seekBar);

    }

}
