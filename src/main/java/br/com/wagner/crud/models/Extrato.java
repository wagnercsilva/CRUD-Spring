package br.com.wagner.crud.models;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Extrato {

    @GeneratedValue
    @Id
    private int id;
    
    @Column(name = "conta")
    private String conta;

    @Column(name = "data")
    private Date data;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "valor")
    private BigDecimal valor;

    @Column(name = "valorTarifa")
    private BigDecimal valorTarifa;

    public Extrato(){

    }

    public Extrato(String conta, Date data, String tipo, BigDecimal valor, BigDecimal valorTarifa){
        super();
        this.conta = conta;
        this.data = data;
        this.tipo = tipo;
        this.valor = valor;
        this.valorTarifa = valorTarifa;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getConta() {
        return this.conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public Date getData() {
        return this.data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getTipo() {
        return this.tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getValor() {
        return this.valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal getValorTarifa() {
        return this.valorTarifa;
    }

    public void setValorTarifa(BigDecimal valorTarifa) {
        this.valorTarifa = valorTarifa;
    }

}
