package com.sistema.examenes.services;

import org.springframework.http.ResponseEntity;

import java.io.Serializable;
import java.util.List;

public interface GenericService <T, ID extends Serializable> {

    public List<T> findByAll();

    public T save(T entity);

    public T findById(ID id);

    public ResponseEntity<?> delete(ID id);

}
