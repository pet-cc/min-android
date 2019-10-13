package pet.cc.minucurso_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import pet.cc.minucurso_android.Adapter.ContactAdapter;
import pet.cc.minucurso_android.Model.Contact;

public class ContactActivity extends AppCompatActivity {

    private ActionBar actionBar;

    private Toolbar toolbar;
    private Toolbar toolbarAlternative;
    private boolean isDefaultToolbar;

    private SearchView svContacts;

    private Menu menu;

    private ContactAdapter adapter;

    private List<Contact> contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        contacts = getRandomContacts(new Random().nextInt(35) + 15);

        isDefaultToolbar = true;
        toolbar = findViewById(R.id.toolbar_contact);
        toolbar.setTitle("Contatos");
        toolbar.setSubtitle(contacts.size() + " contatos");

        toolbarAlternative = findViewById(R.id.toolbar_alternative);
        toolbarAlternative.setTitle("");
        toolbarAlternative.setSubtitle("");

        setSupportActionBar(toolbar);

        actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        svContacts = findViewById(R.id.searchview_contacts);
        svContacts.setVisibility(View.GONE);
        svContacts.setBackgroundColor(Color.TRANSPARENT);
        svContacts.setImeOptions(EditorInfo.IME_ACTION_DONE);
        svContacts.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                setSearchViewVisible(false);
                adapter.getFilter().filter("");

                return false;
            }
        });
        svContacts.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        RecyclerView rvContacts = findViewById(R.id.recycler_view_contacts);
        // Se tiver certeza que seu RecyclerView não será alterado,
        // você pode adicionar o código abaixo para melhorar o desempenho
        rvContacts.setHasFixedSize(true);
        // rvContacts.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rvContacts.setLayoutManager(llm);

        adapter = new ContactAdapter(this, contacts);
        rvContacts.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(isDefaultToolbar ? R.menu.menu_contact : R.menu.menu_contact_alternative, menu);

        this.menu = menu;

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemID = item.getItemId();
        switch (itemID) {
            case android.R.id.home:
                if(getSupportActionBar().getTitle() == toolbar.getTitle()) {
                    if(svContacts.getVisibility() == View.VISIBLE) {
                        setSearchViewVisible(false);
                        adapter.getFilter().filter("");
                    }
                    else {
                        finish();
                    }
                }
                else {
                    adapter.diselectAllContacts();
                    setDefaultToolbar(true);
                }
                break;
            case R.id.menu_contact_search:
                setSearchViewVisible(true);
                break;
        }


        return super.onOptionsItemSelected(item);
    }

    private void setSearchViewVisible(boolean isVisible) {
        svContacts.setVisibility(isVisible ? View.VISIBLE : View.GONE);
        if(isVisible) {
            svContacts.setIconified(false);
        }

        menu.findItem(R.id.menu_contact_search).setVisible(!isVisible);
        menu.findItem(R.id.menu_contact_invite_friend).setVisible(!isVisible);
        menu.findItem(R.id.menu_contact_contacts).setVisible(!isVisible);
        menu.findItem(R.id.menu_contact_reload).setVisible(!isVisible);
        menu.findItem(R.id.menu_contact_help).setVisible(!isVisible);

        if(!isVisible) {
            // Hidden keyboard
            InputMethodManager imm = (InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE);
            View v = this.getCurrentFocus();
            if(imm != null && v != null) {
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        }
    }

    private String getRandomName() {
        String[] names = {"Helena", "Miguel", "Alice", "Arthur", "Laura", "Heitor", "Manuela", "Bernardo",
                "Valentina", "Davi", "Sophia", "Théo", "Isabella", "Lorenzo", "Heloísa", "Gabriel"};

        return names[new Random().nextInt(names.length)];
    }

    private List<Contact> getRandomContacts(int numberOfContacts) {
        List<Contact> contacts = new ArrayList<>();

        for(int iContact = 0; iContact < numberOfContacts; iContact++) {
            String name = getRandomName();

            contacts.add(new Contact(name));
        }

        return contacts;
    }

    public void setDefaultToolbar(boolean isDefaultToolbar) {
        if(this.isDefaultToolbar != isDefaultToolbar) {
            this.isDefaultToolbar = isDefaultToolbar;
            attToolbar();
        }
    }

    private void attToolbar() {
        AlphaAnimation animFadeIn = new AlphaAnimation(1f, 0f);
        animFadeIn.setDuration(200);

        AlphaAnimation animFadeOut = new AlphaAnimation(0.5f, 1f);
        animFadeOut.setDuration(200);

        toolbar.setVisibility(isDefaultToolbar  ? View.VISIBLE : View.GONE);
        toolbar.startAnimation(isDefaultToolbar ? animFadeOut  : animFadeIn);

        toolbarAlternative.setVisibility(isDefaultToolbar  ? View.GONE  : View.VISIBLE);
        toolbarAlternative.startAnimation(isDefaultToolbar ? animFadeIn : animFadeOut);

        setSupportActionBar(isDefaultToolbar ? toolbar : toolbarAlternative);

        actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
}
