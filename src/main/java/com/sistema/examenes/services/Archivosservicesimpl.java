package com.sistema.examenes.services;

import com.sistema.examenes.repository.Archivo_repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

@Service
public class Archivosservicesimpl implements Archivoservices {

    private final Path root = Paths.get("archivosguardados");

    @Autowired
    private Archivo_repository ar;

    @Override
    @PostConstruct
    public void init() throws IOException {
        Files.createDirectories(root);
    }

    @Override
    public void guardar(MultipartFile file, String nombreArchivoUnico) {
        try {
            Files.copy(file.getInputStream(), this.root.resolve(nombreArchivoUnico), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("No se puede guardar el archivo", e);
        }
    }

    @Override
    public Resource load(String filename) {
        try {
            Path file=root.resolve(filename);
            Resource resource= new UrlResource(file.toUri());
            if(resource.exists() || resource.isReadable()){
                return resource;
            }else {
                throw new RuntimeException("No se puede leer el archivo");
            }
        }catch (MalformedURLException e) {
            throw new RuntimeException("error"+ e.getMessage());
        }
    }

    @Override
    public Stream<Path> lIstar() {
        try {
            return Files.walk(this.root, 1).filter(path -> !path.equals(this.root))
                    .map(this.root::relativize);
        }catch (RuntimeException | IOException e ){
            throw new RuntimeException("no se puede cargar los archivos");
        }
    }


    @Override
    public String borrar(String filname) {
        try {
            Boolean borrar =Files.deleteIfExists(this.root.resolve(filname));
            return "Borrado";
        }catch (IOException e){
            e.printStackTrace();
            return "error al borrar";
        }

    }

    @Override
    public boolean existsByNombre(String nombreArchivo) {
        return ar.existsByNombre(nombreArchivo);
    }

}