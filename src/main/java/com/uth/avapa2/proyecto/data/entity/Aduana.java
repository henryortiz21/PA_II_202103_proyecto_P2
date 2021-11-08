package com.uth.avapa2.proyecto.data.entity;

import javax.persistence.Entity;

import com.uth.avapa2.proyecto.data.AbstractEntity;

@Entity
public class Aduana extends AbstractEntity {

  private String nombre;
  private String ubicacion;
  private String via_acceso;
  private String categoria;
  private String zona;
  private String telefono;

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getUbicacion() {
    return ubicacion;
  }

  public void setUbicacion(String ubicacion) {
    this.ubicacion = ubicacion;
  }

  public String getVia_acceso() {
    return via_acceso;
  }

  public void setVia_acceso(String via_acceso) {
    this.via_acceso = via_acceso;
  }

  public String getCategoria() {
    return categoria;
  }

  public void setCategoria(String categoria) {
    this.categoria = categoria;
  }

  public String getZona() {
    return zona;
  }

  public void setZona(String zona) {
    this.zona = zona;
  }

  public String getTelefono() {
    return telefono;
  }

  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }
}
