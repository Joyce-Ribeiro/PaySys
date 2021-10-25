package com.example.ps;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import dominio.entidade.Cliente;
import dominio.entidade.Pagamento;

public class PagamentoAdapter extends RecyclerView.Adapter<PagamentoAdapter.ViewHolderPagamento> {
    private List<Pagamento> dadosPag;


    public PagamentoAdapter(List<Pagamento> dadosPag ){

        this.dadosPag = dadosPag;
    }
    @Override
    public PagamentoAdapter.ViewHolderPagamento onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.linha_pagamento, parent, false);
        ViewHolderPagamento holderPagamento = new ViewHolderPagamento(view, parent.getContext());
        return holderPagamento;
    }

    @Override
    public void onBindViewHolder(PagamentoAdapter.ViewHolderPagamento holder, int position) {
        if(dadosPag!= null && dadosPag.size()>0){
            Pagamento  pagamento = dadosPag.get(position);
            holder.txtData.setText(pagamento.getData());
            int val = (int) pagamento.getValor();
            holder.txtValor.setText(Integer.toString(val));
            holder.txtFreq.setText(Integer.toString(pagamento.getFrequencia()));
            holder.txtStatus.setText(Boolean.toString(pagamento.isStatus()));

        }
    }

    @Override
    public int getItemCount() {
        if (dadosPag != null){
            return dadosPag.size();
        }
        else{
            return 0;
        }
    }
    public class ViewHolderPagamento extends RecyclerView.ViewHolder{
        public TextView txtData;
        public TextView txtValor;
        public TextView txtFreq;
        public TextView txtStatus;
        public Button btnViewPag;
        public FloatingActionButton fabEdit;

        public ViewHolderPagamento (View itemView, final Context context){
            super(itemView);
            txtData = (TextView) itemView.findViewById(R.id.txtData);
            txtValor = (TextView) itemView.findViewById(R.id.txtValor);
            txtFreq = (TextView) itemView.findViewById(R.id.txtFreq);
            txtStatus = (TextView) itemView.findViewById(R.id.txtStatus);
            btnViewPag = (Button) itemView.findViewById(R.id.btnViewPag);


        }




    }
}
