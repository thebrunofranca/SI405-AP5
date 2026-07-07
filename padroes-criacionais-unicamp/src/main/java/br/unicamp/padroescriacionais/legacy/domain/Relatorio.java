package br.unicamp.padroescriacionais.legacy.domain;

import java.time.LocalDateTime;

public class Relatorio {

    private String titulo;
    private String conteudo;
    private TipoRelatorio tipo;
    private LocalDateTime dataGeracao;

    public Relatorio(String titulo, String conteudo, TipoRelatorio tipo, LocalDateTime dataGeracao) {
        this.titulo = titulo;
        this.conteudo = conteudo;
        this.tipo = tipo;
        this.dataGeracao = dataGeracao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public TipoRelatorio getTipo() {
        return tipo;
    }

    public void setTipo(TipoRelatorio tipo) {
        this.tipo = tipo;
    }

    public LocalDateTime getDataGeracao() {
        return dataGeracao;
    }

    public void setDataGeracao(LocalDateTime dataGeracao) {
        this.dataGeracao = dataGeracao;
    }
}
