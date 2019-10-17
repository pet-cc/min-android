package pet.cc.minucurso_android;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import pet.cc.minucurso_android.Adapter.TabAdapter;

public class PrincipalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //recupera a viewPager e o tabLayout
        final ViewPager viewPager = findViewById(R.id.viewPager);
        TabLayout  tabLayout = findViewById(R.id.tabLayout);
        //Cria o tabAdapter
        TabAdapter tabAdapter = new TabAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        //Para que a viewPager e o Adapter conversem entre si, é preciso criar um adapter e passá-lo ao viewPager
        //Seta o tabAdapter ao viewPager (fazendo a vinculação a esses dois)
        viewPager.setAdapter(tabAdapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));



        FloatingActionButton bContatos = findViewById(R.id.bContatos);
        bContatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(PrincipalActivity.this, ContactActivity.class);
                startActivity(it);
            }
        });
    }

    //criação de menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    //ação de clique em um MenuItem
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_configuracoes:
                break;
            case R.id.item_msgsfavoritas:
                break;
        }
        return true;
    }

    //Evento de close do Menu
    @Override
    public void onOptionsMenuClosed(Menu menu) {
        super.onOptionsMenuClosed(menu);
    }

}
