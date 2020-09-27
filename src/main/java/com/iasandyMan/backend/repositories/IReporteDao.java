package com.iasandyMan.backend.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.iasandyMan.backend.domain.Reporte;

@Repository
public interface IReporteDao extends CrudRepository<Reporte, String> {
	
	List<Reporte> findAllByIdTecnicoAndFechaInicioBetweenOrFechaFinBetween(String idTecnico, Date minIni, Date maxIni, Date minFin, Date maxFin);
}
