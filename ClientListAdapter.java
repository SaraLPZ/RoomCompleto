package com.example.roomcompleto;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ClientListAdapter extends RecyclerView.Adapter<ClientListAdapter.ViewHolder> {
    private List<Cliente> clientes;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_client, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return clientes.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setClientInfo(clientes.get(position));
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView clientNameTv;
        private TextView clientEmailTv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.clientNameTv = itemView.findViewById(R.id.name_tv);
            this.clientEmailTv = itemView.findViewById(R.id.email_tv);
        }

        public void setClientInfo(Cliente client) {
            clientNameTv.setText(client.getNombre());
            clientEmailTv.setText(client.getEmail());
        }
    }
}
