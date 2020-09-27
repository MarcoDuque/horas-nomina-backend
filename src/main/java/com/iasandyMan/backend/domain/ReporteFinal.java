package com.iasandyMan.backend.domain;

public class ReporteFinal {

	private int horasNormales;
	private int horasNocturnas;
	private int horasDominicales;
	private int horasNormalesExtras;
	private int horasNocturnasExtras;
	private int horasDominicalesExtras;
	
	
	public ReporteFinal() {
		this.horasNormales = 0;
		this.horasNocturnas = 0;
		this.horasDominicales = 0;
		this.horasNormalesExtras = 0;
		this.horasNocturnasExtras = 0;
		this.horasDominicalesExtras = 0;
	}

	public int getHorasNormales() {
		return horasNormales;
	}

	public void setHorasNormales(int horasNormales) {
		this.horasNormales = horasNormales;
	}

	public int getHorasNocturnas() {
		return horasNocturnas;
	}

	public void setHorasNocturnas(int horasNocturnas) {
		this.horasNocturnas = horasNocturnas;
	}

	public int getHorasDominicales() {
		return horasDominicales;
	}

	public void setHorasDominicales(int horasDominicales) {
		this.horasDominicales = horasDominicales;
	}

	public int getHorasNormalesExtras() {
		return horasNormalesExtras;
	}

	public void setHorasNormalesExtras(int horasNormalesExtras) {
		this.horasNormalesExtras = horasNormalesExtras;
	}

	public int getHorasNocturnasExtras() {
		return horasNocturnasExtras;
	}

	public void setHorasNocturnasExtras(int horasNocturnasExtras) {
		this.horasNocturnasExtras = horasNocturnasExtras;
	}

	public int getHorasDominicalesExtras() {
		return horasDominicalesExtras;
	}

	public void setHorasDominicalesExtras(int horasDominicalesExtras) {
		this.horasDominicalesExtras = horasDominicalesExtras;
	}

}
