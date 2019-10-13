package pet.cc.minucurso_android.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import pet.cc.minucurso_android.Adapter.ConversaAdapter;
import pet.cc.minucurso_android.Model.Conversa;
import pet.cc.minucurso_android.R;

public class ConversasFragment extends Fragment {

    public ConversasFragment() {
        // Empty Constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_conversa, container, false);

        List<Conversa> listaConversas = new ArrayList<>();
        listaConversas.add(new Conversa("Daniel Seitenfus", "Oi, tudo bem?", "16:45", 5));
        listaConversas.add(new Conversa("Igor Prado", "Oiiii", "22:15", 11));

        ConversaAdapter adapter = new ConversaAdapter(listaConversas, new ConversaAdapter.ConversaOnClickListener() {
            @Override
            public void onClickConversa(View view, int position) {
                Toast.makeText(container.getContext(), "Conversa " + position, Toast.LENGTH_SHORT).show();
            }
        });

        LinearLayoutManager llm = new LinearLayoutManager(getContext().getApplicationContext());

        RecyclerView rvConversas = view.findViewById(R.id.rvConversas);
        rvConversas.setItemAnimator(new DefaultItemAnimator());
        rvConversas.setLayoutManager(llm);
        rvConversas.setAdapter(adapter);

        return view;
    }
}
