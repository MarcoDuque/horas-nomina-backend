package com.iasandyMan.backend.services.contracts;

import java.util.List;

import com.iasandyMan.backend.domain.Reporte;
import com.iasandyMan.backend.domain.ReporteFinal;

public interface IReporteServicio {
	
	public List<Reporte> findAll();
	
	public ReporteFinal findById(String idTecnico, int semana);
	
	public Reporte save(Reporte reporte);

}
