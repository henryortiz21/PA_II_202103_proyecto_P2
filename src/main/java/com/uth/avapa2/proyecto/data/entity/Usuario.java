package com.uth.avapa2.proyecto.data.entity;

import com.uth.avapa2.proyecto.data.AbstractEntity;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class Usuario extends AbstractEntity {
  private String nombre;
  private String apellido;
  private String telefono;
  private String email;
  private String pass;

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getApellido() {
    return apellido;
  }

  public void setApellido(String apellido) {
    this.apellido = apellido;
  }

  public String getTelefono() {
    return telefono;
  }

  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPass() {
    return pass;
  }

  public void setPass(String pass) {
    this.pass = pass;
  }
}
