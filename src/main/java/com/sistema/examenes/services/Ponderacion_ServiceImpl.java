package com.sistema.examenes.services;

import com.sistema.examenes.entity.Ponderacion;
import com.sistema.examenes.projection.PonderacionProjection;
import com.sistema.examenes.repository.Ponderacion_repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class Ponderacion_ServiceImpl extends GenericServiceImpl<Ponderacion, Long> implements Ponderacion_Service {
    @Autowired
    private Ponderacion_repository repository;

    @Override
    public CrudRepository<Ponderacion, Long> getDao() {
        return repository;
    }

    @Override
    public List<Ponderacion> listar() {
        return repository.listarPonderacion();
    }

    @Override
    public List<Ponderacion> listarPorFecha(String fecha) {
        return repository.listarPorFecha(fecha);
    }

    @Override
    public List<PonderacionProjection> idmax(Long idModelo) {
        // Define un objeto Pageable con el límite de 1 resultado
        Pageable pageable = PageRequest.of(0, 1, Sort.by("contador").descending());

        // Llama al método del repositorio pasando el id del modelo y el objeto Pageable
        List<PonderacionProjection> result = repository.idmax(idModelo, pageable);

        // Retorna el resultado obtenido
        return result;
    }
    @Override
    public void eliminarPonderacion(Long contador,String fecha) {
        repository.eliminarPonderacion(contador, fecha);
    }

 /*   @Override
    public List<Ponderacion> listarPonderacionPorModelo(Long id_modelo) {
        return repository.listarPonderacionPorModelo(id_modelo);
    }
*/
    @Override
    public List<PonderacionProjection> listarPonderacionModelo(Long id_modelo) {
        return repository.listarPonderacionModelo(id_modelo);
    }

    @Override
    public List<PonderacionProjection> listarPonderacionPorFecha(String fecha,Long contador) {
        return repository.listarPonderacionPorFecha(fecha, contador);
    }
    @Override
    public Ponderacion save(Ponderacion entity) {
        return repository.save(entity);
    }
}
