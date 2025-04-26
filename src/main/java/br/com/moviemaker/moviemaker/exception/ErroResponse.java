package br.com.moviemaker.moviemaker.exception;

import java.time.LocalDateTime;

public class ErroResponse {

    private String mensagem;
    private String detalhes;
    private LocalDateTime timestamp;

    // Construtores
    public ErroResponse(String mensagem, String detalhes) {
        this.mensagem = mensagem;
        this.detalhes = detalhes;
        this.timestamp = LocalDateTime.now();
    }

    // Getters e Setters
    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
