package com.blackshark.sistemablog.domain.service;

import com.blackshark.sistemablog.persistance.entity.Publicacion;
import com.blackshark.sistemablog.web.dto.PublicacionDTO;

import java.util.List;
import java.util.Optional;

public interface IPublicacionService {

    public PublicacionDTO crearPublicacion(PublicacionDTO publicacionDTO);

    public List<Publicacion> obtenerPublicaciones();

    public Optional<Publicacion> obtenerPublicacionPorId(Long id);


    public Publicacion eliminarPublicacion(Long id);

    public Optional<PublicacionDTO> actualizarPublicacion(Long id, PublicacionDTO publicacionDTO);
}
