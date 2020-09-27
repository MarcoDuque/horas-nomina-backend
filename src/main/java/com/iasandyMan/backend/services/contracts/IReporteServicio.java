package com.iasandyMan.backend.services.contracts;

import java.util.List;

import com.iasandyMan.backend.domain.Reporte;

public interface IReporteServicio {
	
	public List<Reporte> findAll();
	
	public Object findById(String idTecnico, int semana);
	
	public Reporte save(Reporte reporte);

}
