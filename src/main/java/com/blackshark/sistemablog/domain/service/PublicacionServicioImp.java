package com.blackshark.sistemablog.domain.service;

import com.blackshark.sistemablog.persistance.entity.Publicacion;
import com.blackshark.sistemablog.persistance.repository.IPublicacionRepository;
import com.blackshark.sistemablog.util.exception.PublicacionNotFoundException;
import com.blackshark.sistemablog.web.dto.PublicacionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublicacionServicioImp implements IPublicacionService {

    @Autowired
    private final IPublicacionRepository publicacionRepository;


    public PublicacionServicioImp(IPublicacionRepository publicacionRepository) {
        this.publicacionRepository = publicacionRepository;
    }


    @Override
    public PublicacionDTO crearPublicacion(PublicacionDTO publicacionDTO) {
        try {
            if (publicacionDTO.getTitulo().isEmpty() || publicacionDTO.getTitulo().isEmpty()) {
                return null;
            }

            Publicacion publicacion = new Publicacion();

            publicacion.setTitulo(publicacionDTO.getTitulo());
            publicacion.setDescripcion(publicacionDTO.getDescripcion());
            publicacion.setContenido(publicacionDTO.getContenido());

            Publicacion publicacionGuardada = publicacionRepository.save(publicacion);

            PublicacionDTO publicacionDTORespuesta = new PublicacionDTO();

            publicacionDTORespuesta.setId(publicacionGuardada.getId());
            publicacionDTORespuesta.setTitulo(publicacionGuardada.getTitulo());
            publicacionDTORespuesta.setDescripcion(publicacionGuardada.getDescripcion());
            publicacionDTORespuesta.setContenido(publicacionGuardada.getContenido());

            return publicacionDTORespuesta;

        } catch (Exception e) {
            return null;
        }
    }


    @Override
    public List<Publicacion> obtenerPublicaciones() {
        return this.publicacionRepository.findAll();
    }

    @Override
    public Optional<Publicacion> obtenerPublicacionPorId(Long id) {

        if(!this.publicacionRepository.existsById(id)){

            throw new PublicacionNotFoundException(id);

        }
        return this.publicacionRepository.findById(id);
    }

    @Override
    public Publicacion eliminarPublicacion(Long id) {

        try {
            Optional<Publicacion> publicacionOptional = publicacionRepository.findById(id);
            if (publicacionOptional.isPresent()) {
                Publicacion publicacion = publicacionOptional.get();
                publicacionRepository.delete(publicacion);
                return publicacion;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Optional<PublicacionDTO> actualizarPublicacion(Long id, PublicacionDTO publicacionDTO) {
        // Comprobamos si existe la publicación con el id dado
        return publicacionRepository.findById(id)
                // Si existe, actualizamos sus datos y la guardamos
                .map(publicacion -> {
                    publicacion.setTitulo(publicacionDTO.getTitulo());
                    publicacion.setDescripcion(publicacionDTO.getDescripcion());
                    publicacion.setContenido(publicacionDTO.getContenido());
                    Publicacion publicacionGuardada = publicacionRepository.save(publicacion);

                    // Convertimos la entidad a DTO y la retornamos
                    PublicacionDTO publicacionDTORespuesta = new PublicacionDTO();
                    publicacionDTORespuesta.setId(publicacionGuardada.getId());
                    publicacionDTORespuesta.setTitulo(publicacionGuardada.getTitulo());
                    publicacionDTORespuesta.setDescripcion(publicacionGuardada.getDescripcion());
                    publicacionDTORespuesta.setContenido(publicacionGuardada.getContenido());

                    return publicacionDTORespuesta; // No es necesario volver a envolverlo en Optional
                })
                .map(Optional::of) // Envuelves el resultado en un Optional nuevamente
                // Si no existe, lanzamos una excepción personalizada
                .orElseThrow(() -> new PublicacionNotFoundException(id));
    }
}

