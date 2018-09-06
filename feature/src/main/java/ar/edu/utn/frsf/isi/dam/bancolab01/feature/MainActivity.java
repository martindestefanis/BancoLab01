package ar.edu.utn.frsf.isi.dam.bancolab01.feature;

import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
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
    private EditText edtMail;
    private EditText edtCuit;
    private EditText edtMonto;
    private Button btnHacerPlazoFijo;
    private SeekBar seekBar;
    private TextView tvDiasSeleccionados;
    private TextView tvInteres;
    private CheckBox chbAceptoTerminos;
    private TextView tvMensajes;
    int progress = 10;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pf = new PlazoFijo(getResources().getStringArray(R.array.tasa1));
        cliente = new Cliente();
        edtMail = (EditText) findViewById(R.id.edtMail);
        cliente.setMail(edtMail.toString());
        edtCuit = (EditText) findViewById(R.id.edtCuit);
        cliente.setCuil(edtCuit.toString());
        edtMonto = (EditText) findViewById(R.id.edtMonto);
        edtMonto.addTextChangedListener(new TextWatcher(){

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                if(edtMonto.getText().toString().isEmpty()){
                    pf.setMonto(0.0);
                }
                else {
                    String value = edtMonto.getText().toString();
                    Double monto = Double.parseDouble(value);
                    pf.setMonto(monto);
                    pf.setDias(progress);
                    tvInteres.setText("$" + pf.intereses().toString());
                }
            }
        });

        btnHacerPlazoFijo = (Button) findViewById(R.id.btnHacerPlazoFijo);
        btnHacerPlazoFijo.setEnabled(false);

        seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setMax(180);
        //seekBar.setMin(10);
        seekBar.setProgress(progress);

        tvDiasSeleccionados = (TextView) findViewById(R.id.tvDiasSeleccionados);
        tvDiasSeleccionados.setText(progress + " dias de plazo");

        tvInteres = (TextView) findViewById(R.id.tvInteres);

        chbAceptoTerminos = (CheckBox) findViewById(R.id.chbAceptoTerminos);

        tvMensajes = (TextView) findViewById(R.id.tvMensajes);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean fromUser) {
                progress = i;
                tvDiasSeleccionados.setText(progress + " dias de plazo");
                pf.setDias(progress);
                edtMonto= (EditText) findViewById(R.id.edtMonto);
                String value = edtMonto.getText().toString();
                Double monto = Double.parseDouble(value);
                pf.setMonto(monto);
                tvInteres.setText("$" + pf.intereses().toString());
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
                    Toast.makeText(getApplicationContext(),"Es obligatorio aceptar los términos y condiciones",Toast.LENGTH_LONG).show();
                    btnHacerPlazoFijo.setEnabled(false);
                }
            }
        });

        btnHacerPlazoFijo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if(cliente.getMail().trim().isEmpty() || cliente.getCuit().isEmpty() || pf.getMonto()<=0.0 || pf.getDias()<=10)
                {
                    Toast.makeText(getApplicationContext(),"Revise los datos ingresados", Toast.LENGTH_LONG).show();
                    tvMensajes.setText("Los datos ingresados son incorrectos o no son válidos");
                    tvMensajes.setTextColor(Color.RED);
                }
            else {
                tvMensajes.setText("El plazo fijo se realizó correctamente \n" +
                                    "Datos del plazo fijo:\n" +
                                    "Dias: " + pf.getDias() + "\n" +
                                    "Monto: " + pf.getMonto() + "\n"+
                                    "Intereses:" + pf.intereses() + "\n");
                tvMensajes.setTextColor(Color.BLUE);
                }
            }
        });
        }
    }