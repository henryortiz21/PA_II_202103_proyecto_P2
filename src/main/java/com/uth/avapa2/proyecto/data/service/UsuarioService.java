package com.uth.avapa2.proyecto.data.service;

import com.uth.avapa2.proyecto.data.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.artur.helpers.CrudService;

@Service
public class UsuarioService extends CrudService<Usuario, Integer> {

    private UsuarioRepository repository;

    public UsuarioService(@Autowired UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    protected UsuarioRepository getRepository() {
        return repository;
    }

}
