package com.marmo.orcamento;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.marmo.orcamento.modelo.Orcamento;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class ListaOrcamentoAdapter extends BaseAdapter {


    Context context;
    List<Orcamento> orcamento;

    public ListaOrcamentoAdapter(Context context, List<Orcamento> orcamento) {
        this.context = context;
        this.orcamento = orcamento;
    }

    @Override
    public int getCount() {
        return orcamento.size();
    }

    @Override
    public Object getItem(int position) {
        return orcamento.get(position);
    }

    @Override
    public long getItemId(int position) {
        return orcamento.get(position).getId();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View viewLinha = LayoutInflater.from(context).inflate(R.layout.linha_orcamento,parent, false);
        Orcamento orcamentolista = orcamento.get(position);
        TextView textView = viewLinha.findViewById(R.id.textViewCliente);
        textView.setText(orcamentolista.getCliente());

        TextView textView2 = viewLinha.findViewById(R.id.textViewId);
        textView2.setText(orcamentolista.getId().toString());


        TextView textView3 = viewLinha.findViewById(R.id.textViewData);
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        textView3.setText(format.format(orcamentolista.getDataOrcamento()));


        TextView textView4 = viewLinha.findViewById(R.id.textViewValor);
        textView4.setText(orcamentolista.getValorOrcamento().toString());





        return viewLinha;
    }
}
