package com.ejemplo.dto;

import java.util.ArrayList;
import java.util.Collections;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DocumentoDTO {
    private String id;

    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    @JsonProperty("titulo")
    private ArrayList<String> titulo;

    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    @JsonProperty("descripcion")
    private ArrayList<String> descripcion;

    // Constructor vacío
    public DocumentoDTO() {}

    // Constructor con listas
    public DocumentoDTO(String id, ArrayList<String> titulo, ArrayList<String> descripcion) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
    }

    // Constructor de conveniencia que acepta Strings (mantiene compatibilidad)
    public DocumentoDTO(String id, String titulo, String descripcion) {
        this.id = id;
        this.titulo = titulo != null ? new ArrayList<>(Collections.singletonList(titulo)) : null;
        this.descripcion = descripcion != null ? new ArrayList<>(Collections.singletonList(descripcion)) : null;
    }

    // Getters y setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<String> getTitulo() {
        return titulo;
    }

    public void setTitulo(ArrayList<String> titulo) {
        this.titulo = titulo;
    }

    // Compatibilidad: aceptar un String y guardarlo como lista con un elemento
    public void setTitulo(String titulo) {
        this.titulo = titulo != null ? new ArrayList<>(Collections.singletonList(titulo)) : null;
    }

    public ArrayList<String> getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(ArrayList<String> descripcion) {
        this.descripcion = descripcion;
    }

    // Compatibilidad: aceptar un String y guardarlo como lista con un elemento
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion != null ? new ArrayList<>(Collections.singletonList(descripcion)) : null;
    }

    // Helpers: obtener el primer valor como String (útil para código legacy)
    public String getTituloAsString() {
        return (titulo == null || titulo.isEmpty()) ? null : titulo.get(0);
    }

    public String getDescripcionAsString() {
        return (descripcion == null || descripcion.isEmpty()) ? null : descripcion.get(0);
    }

}