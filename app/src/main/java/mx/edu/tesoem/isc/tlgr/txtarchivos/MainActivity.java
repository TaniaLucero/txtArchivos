package mx.edu.tesoem.isc.tlgr.txtarchivos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText txtnombre;
    TextView lblcontenido;

    private final String nomarch="DatosTania.txt";
    private ArrayList<String> TextoCompleto = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtnombre = findViewById(R.id.txtnombre);
        lblcontenido = findViewById(R.id.lblcontenido);

        llamadatos();

    }

    public void recargar(View v)
    {
        ManejoArchivo objmanar = new ManejoArchivo();
        objmanar.agregar(txtnombre.getText().toString(), TextoCompleto);
        TextoCompleto = objmanar.getContenido();
        if (objmanar.grabar(this, TextoCompleto, nomarch))
        {
            Toast.makeText(this, "Se Grabo Correctamente", Toast.LENGTH_LONG).show();
            llamadatos();
        }
        else
        {
            Toast.makeText(this, "No se Grabo la Informacion", Toast.LENGTH_LONG).show();
        }
    }

    public void cargadatos(View v)
    {
        llamadatos();
    }

    public void llamadatos()
    {
        ManejoArchivo informacion = new ManejoArchivo();
        if(informacion.VerificaArch(this, nomarch))
        {
            Toast.makeText(this, "Si Existe el Archivo",Toast.LENGTH_LONG).show();
            if(informacion.leer(this,nomarch))
            {
                TextoCompleto = informacion.getContenido();
                String temp="";
                for(String cadena :TextoCompleto)
                {
                    temp+="\n"+cadena;
                    lblcontenido.setText(temp);
                }
            }
        }
        else
        {
            Toast.makeText(this,"No Existe el Archivo",Toast.LENGTH_LONG).show();
        }
    }
}
