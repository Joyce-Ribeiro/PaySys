package com.example.ps;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import dominio.entidade.Cliente;

public class ClienteAdapter extends RecyclerView.Adapter<ClienteAdapter.ViewHolderCliente> {
    private List<Cliente> dadosCli;


    public ClienteAdapter(List<Cliente> dadosCli ){

        this.dadosCli = dadosCli;
    }
    @Override
    public ClienteAdapter.ViewHolderCliente onCreateViewHolder( ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.linha_cliente, parent, false);
        ViewHolderCliente holderCliente = new ViewHolderCliente(view, parent.getContext());
        return holderCliente;
    }

    @Override
    public void onBindViewHolder( ClienteAdapter.ViewHolderCliente holder, int position) {
        if(dadosCli!= null && dadosCli.size()>0){
            Cliente  cliente = dadosCli.get(position);
            holder.txtNomeCli.setText(cliente.getNome());
            holder.txtNumCli.setText(cliente.getNumero());

        }
    }

    @Override
    public int getItemCount() {
        if (dadosCli != null){
            return dadosCli.size();
        }
        else{
            return 0;
        }
    }
    public class ViewHolderCliente extends RecyclerView.ViewHolder{
        public TextView txtNomeCli;
        public TextView txtNumCli;
        public Button btnViewPag;
        public FloatingActionButton fabEdit;

        public ViewHolderCliente (View itemView, final Context context){
            super(itemView);
            txtNomeCli = (TextView) itemView.findViewById(R.id.txtNomeCli);
            txtNumCli = (TextView) itemView.findViewById(R.id.txtNumCli);
            btnViewPag = (Button) itemView.findViewById(R.id.btnViewPag);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (dadosCli.size()>0) {

                        String numero = txtNumCli.getText().toString();

                        Intent it = new Intent(context, EditCadCli.class);
                        it.putExtra("NUMERO", numero);
                        ((AppCompatActivity)context).startActivity(it);
                    }
                    }

            });

        }




    }
}
