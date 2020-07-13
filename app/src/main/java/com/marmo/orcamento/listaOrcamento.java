package com.marmo.orcamento;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.marmo.orcamento.modelo.Orcamento;

import java.util.List;

public class listaOrcamento extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_orcamento);
        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);
        carregarLista();
    }

    private void carregarLista(){

        List<Orcamento> orcamentoLista  =  Orcamento.find(Orcamento.class,"id>0");

        ListaOrcamentoAdapter adapter = new ListaOrcamentoAdapter(this, orcamentoLista);
        listView.setAdapter(adapter);


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Orcamento orcamento = (Orcamento) parent.getItemAtPosition(position);
        Intent intent = new Intent(this,consultaOrcamento.class);
        intent.putExtra("ID_ORCAMENTO", orcamento.getId());
        startActivity(intent);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        final Orcamento orcamento = (Orcamento) parent.getItemAtPosition(position);


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Deseja remover este Registro?").setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //aqui efetua a remoção
                Orcamento.delete(orcamento);
                carregarLista();
            }
        }).setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();

        return true;
    }
}