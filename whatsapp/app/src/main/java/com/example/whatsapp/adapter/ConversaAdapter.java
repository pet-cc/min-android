package com.example.whatsapp.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.whatsapp.R;
import com.example.whatsapp.model.Conversa;

import java.util.List;

public class ConversaAdapter extends RecyclerView.Adapter<ConversaAdapter.MyViewHolder> {
    List<Conversa> listaConversas;
    private ConversaOnClickListener clienteOnClickListener;

    public ConversaAdapter(List<Conversa> listaClientes, ConversaOnClickListener clienteOnClickListener) {
        this.listaConversas = listaClientes;
        this.clienteOnClickListener = clienteOnClickListener;
    }

    @Override
    public ConversaAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_conversa, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ConversaAdapter.MyViewHolder holder, final int position) {
        Conversa conversa = listaConversas.get(position);
        holder.tvTitulo.setText(conversa.getTitulo());
        holder.tvPrevia.setText(conversa.getPrevia());
        holder.tvHora.setText(conversa.getHora());
        holder.tvQntMsgs.setText(conversa.getQntMsgs()+"");
        // clique no item do cliente
        if (clienteOnClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clienteOnClickListener.onClickConversa(holder.itemView,position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return listaConversas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPerfil;
        TextView tvTitulo, tvPrevia, tvHora, tvQntMsgs;
        public MyViewHolder(View itemView) {
            super(itemView);
            tvTitulo = itemView.findViewById(R.id.tvTitulo);
            tvPrevia= itemView.findViewById(R.id.tvPrevia);
            tvHora= itemView.findViewById(R.id.tvHora);
            ivPerfil = itemView.findViewById(R.id.ivPerfil);
            tvQntMsgs = itemView.findViewById(R.id.tvQntMsgs);
        }
    }

    public interface ConversaOnClickListener {
        public void onClickConversa(View view, int position);
    }

}


