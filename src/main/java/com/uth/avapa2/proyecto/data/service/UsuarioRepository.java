package com.uth.avapa2.proyecto.data.service;

import com.uth.avapa2.proyecto.data.entity.Aduana;
import com.uth.avapa2.proyecto.data.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

}