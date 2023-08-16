package com.blackshark.sistemablog.util.exception;

public class PublicacionNotFoundException extends RuntimeException {

    private Long id;

    public PublicacionNotFoundException(Long id) {
        super("No se ha encontrado la publicación con el id " + id);
        this.id = id;
    }

    public String getMessage() {
        return super.getMessage();
    }

    public Long getId() {
        return id;
    }

}
