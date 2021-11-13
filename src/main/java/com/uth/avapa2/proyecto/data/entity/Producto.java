package com.uth.avapa2.proyecto.data.entity;

import com.uth.avapa2.proyecto.data.AbstractEntity;
import com.vaadin.flow.component.datepicker.DatePicker;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class Producto extends AbstractEntity {

  private String codigo;
  private String url_imagen;
  private String descripcion;
  private String estado;
  private LocalDate fecha_ingreso;
  private String marca;
  private String lugar_origen;

  public String getCodigo() {
    return codigo;
  }

  public void setCodigo(String codigo) {
    this.codigo = codigo;
  }

  public String getUrl_imagen() {
    return url_imagen;
  }

  public void setUrl_imagen(String url_imagen) {
    this.url_imagen = url_imagen;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public String getEstado() {
    return estado;
  }

  public void setEstado(String estado) {
    this.estado = estado;
  }

  public LocalDate getFecha_ingreso() {
    return fecha_ingreso;
  }

  public void setFecha_ingreso(LocalDate fecha_ingreso) {
    this.fecha_ingreso = fecha_ingreso;
  }

  public String getMarca() {
    return marca;
  }

  public void setMarca(String marca) {
    this.marca = marca;
  }

  public String getLugar_origen() {
    return lugar_origen;
  }

  public void setLugar_origen(String lugar_origen) {
    this.lugar_origen = lugar_origen;
  }
}
