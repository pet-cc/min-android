package pet.cc.minucurso_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText etCelular = findViewById(R.id.etCelular);

        Button bEntrar = findViewById(R.id.bEntrar);
        bEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strCelular = etCelular.getText().toString();
                if(!strCelular.isEmpty()) {
                    Intent it = new Intent(getApplicationContext(), PrincipalActivity.class);
                    startActivity(it);
                }
            }
        });
    }
}
