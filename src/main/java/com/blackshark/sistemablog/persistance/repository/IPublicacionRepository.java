package com.blackshark.sistemablog.persistance.repository;

import com.blackshark.sistemablog.persistance.entity.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPublicacionRepository extends JpaRepository<Publicacion, Long> {

}
