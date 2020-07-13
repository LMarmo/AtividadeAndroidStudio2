package com.marmo.orcamento.modelo;

import com.orm.SugarRecord;

import java.util.Date;

public class Orcamento extends SugarRecord {

    public Orcamento(String chave, String cliente, String cpfcnpj, String descricao, Double valorOrcamento, String telefone, String imagem, Date dataOrcamento) {
        this.chave = chave;
        this.cliente = cliente;
        this.cpfcnpj = cpfcnpj;
        this.descricao = descricao;
        this.valorOrcamento = valorOrcamento;
        this.telefone = telefone;
        this.imagem = imagem;
        this.dataOrcamento = dataOrcamento;
    }

    private String chave;
    private String cliente;
    private String cpfcnpj;
    private String descricao;
    private Double valorOrcamento;
    private String telefone;
    private String imagem;
    private Date dataOrcamento=new Date();

    public Orcamento() {

    }

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getCpfcnpj() {
        return cpfcnpj;
    }

    public void setCpfcnpj(String cpfcnpj) {
        this.cpfcnpj = cpfcnpj;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValorOrcamento() {
        return valorOrcamento;
    }

    public void setValorOrcamento(Double valorOrcamento) {
        this.valorOrcamento = valorOrcamento;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public Date getDataOrcamento() {
        return dataOrcamento;
    }

    public void setDataOrcamento(Date dataOrcamento) {
        this.dataOrcamento = dataOrcamento;
    }
}
