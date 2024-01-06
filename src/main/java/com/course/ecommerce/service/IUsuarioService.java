package com.course.ecommerce.service;

import java.util.Optional;

import com.course.ecommerce.model.Usuario;

public interface IUsuarioService {

	Optional<Usuario> findById(Integer id);
}
