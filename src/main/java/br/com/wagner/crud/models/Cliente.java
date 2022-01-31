package br.com.wagner.crud.models;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Cliente {
    
    @GeneratedValue
    @Id
    private int id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "planoExclusive")
    private boolean planoExclusive;

    @Column(name = "saldo")
    private BigDecimal saldo;

    @Column(unique = true, name = "conta")
    private String conta;   

    @Column(name = "dtNasc")
    private Date dtNasc;

    @Transient
    private String dtNascMask;

    public Cliente() {
    }

    public Cliente(String nome, boolean planoExclusive, BigDecimal saldo, String conta, Date dtNasc, String dtNascMask) {
        super();
        this.nome = nome;
        this.planoExclusive = planoExclusive;
        this.saldo = saldo;
        this.conta = conta;
        this.dtNasc = dtNasc;
        this.dtNascMask = dtNascMask;
    }
    

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isPlanoExclusive() {
        return this.planoExclusive;
    }

    public void setPlanoExclusive(boolean planoExclusive) {
        this.planoExclusive = planoExclusive;
    }

    public BigDecimal getSaldo() {
        return this.saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public String getConta() {
        return this.conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public Date getDtNasc() {
        return this.dtNasc;
    }

    public void setDtNasc(Date dtNasc) {
        this.dtNasc = dtNasc;
    }

    public String getDtNascMask() {
        return this.dtNascMask;
    }

    public void setDtNascMask(String dtNascMask) {
        this.dtNascMask = dtNascMask;
    }
}
