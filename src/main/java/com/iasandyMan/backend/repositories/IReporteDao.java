package com.iasandyMan.backend.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.iasandyMan.backend.domain.Reporte;

@Repository
public interface IReporteDao extends CrudRepository<Reporte, String> {
	
	@Query("SELECT R FROM Reporte R WHERE R.idTecnico=:idTecnico and (R.fechaInicio BETWEEN :minIni and :maxIni OR R.fechaFin BETWEEN :minIni and :maxIni)")
	List<Reporte> findAllByIdTecnico(
			@Param("idTecnico") String idTecnico,
			@Param("minIni") Date minIni,
			@Param("maxIni") Date maxIni
	);
}
