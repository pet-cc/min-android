package pet.cc.minucurso_android.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pet.cc.minucurso_android.Model.Conversa;
import pet.cc.minucurso_android.R;

public class ConversaAdapter extends RecyclerView.Adapter<ConversaAdapter.ViewHolder> {

    private List<Conversa> listaConversas;

    private ConversaOnClickListener clienteOnClickListener;

    public ConversaAdapter(List<Conversa> listaConversas, ConversaOnClickListener clienteOnClickListener) {
        this.listaConversas = listaConversas;
        this.clienteOnClickListener = clienteOnClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlist_conversa, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        Conversa conversa = listaConversas.get(position);

        holder.tvTitulo.setText(conversa.getTitulo());
        holder.tvPrevia.setText(conversa.getPrevia());
        holder.tvHora.setText(conversa.getHora());
        holder.tvQntMsgs.setText(Integer.toString(conversa.getQntMsgss()));

        if(clienteOnClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clienteOnClickListener.onClickConversa(holder.itemView, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return listaConversas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivPerfil;

        TextView tvTitulo;
        TextView tvPrevia;
        TextView tvHora;
        TextView tvQntMsgs;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPerfil = itemView.findViewById(R.id.ivPerfil);
            tvTitulo  = itemView.findViewById(R.id.tvTitulo);
            tvPrevia  = itemView.findViewById(R.id.tvPrevia);
            tvHora    = itemView.findViewById(R.id.tvHora);
            tvQntMsgs = itemView.findViewById(R.id.tvQntMsgs);
        }
    }

    public interface ConversaOnClickListener {
        public void onClickConversa(View view, int position);
    }
}
