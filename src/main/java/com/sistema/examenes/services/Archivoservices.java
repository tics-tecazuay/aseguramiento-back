package com.sistema.examenes.services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Stream;

public interface Archivoservices {
    void init() throws IOException;
  public void  guardar(MultipartFile file);
   public Resource load(String filename );
    public Stream<Path> lIstar();
    public String borrar(String filname);



}
