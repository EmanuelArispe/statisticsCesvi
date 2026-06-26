package com.statisticsCesvi;

import java.time.LocalDate;

public class PeritacionDto {

    private String siniestro;
    private LocalDate fechainforme;
    private LocalDate fechasiniestro;  // no viene del XLS
    private Boolean cleas;             // no viene del XLS
    private String danio;              // no viene del XLS
    private Boolean asegurado;         // no viene del XLS
    private String tipo;               // no viene del XLS
    private String vehiculo;
    private Integer anio;
    private String patente;
    private String provincia;
    private String localidad;
    private String direccion;
    private Double totalperitadopesos;
    private Double totalreparacionpesos;
    private String dtnormal;
    private Double manodeobrapesos;
    private Double repuestospesos;
    private Double matpinturapesos;
    private Double variospesos;
    private Double chapapesos;
    private Double mecanicapesos;
    private Double pinturapesos;
    private Double elecpesos;
    private Double totalhoras;
    private Double chapahoras;
    private Double mecanicahoras;
    private Double pinturahoras;
    private Double elechoras;
    private String titular;
    private Integer poliza;
    private String taller;
    private String cuit;
    private Boolean ordentrabajo;
    private Boolean ordencompra;

    public String getSiniestro() { return siniestro; }
    public void setSiniestro(String siniestro) { this.siniestro = siniestro; }

    public LocalDate getFechainforme() { return fechainforme; }
    public void setFechainforme(LocalDate fechainforme) { this.fechainforme = fechainforme; }

    public LocalDate getFechasiniestro() { return fechasiniestro; }
    public void setFechasiniestro(LocalDate fechasiniestro) { this.fechasiniestro = fechasiniestro; }

    public Boolean getCleas() { return cleas; }
    public void setCleas(Boolean cleas) { this.cleas = cleas; }

    public String getDanio() { return danio; }
    public void setDanio(String danio) { this.danio = danio; }

    public Boolean getAsegurado() { return asegurado; }
    public void setAsegurado(Boolean asegurado) { this.asegurado = asegurado; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getVehiculo() { return vehiculo; }
    public void setVehiculo(String vehiculo) { this.vehiculo = vehiculo; }

    public Integer getAnio() { return anio; }
    public void setAnio(Integer anio) { this.anio = anio; }

    public String getPatente() { return patente; }
    public void setPatente(String patente) { this.patente = patente; }

    public String getProvincia() { return provincia; }
    public void setProvincia(String provincia) { this.provincia = provincia; }

    public String getLocalidad() { return localidad; }
    public void setLocalidad(String localidad) { this.localidad = localidad; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public Double getTotalperitadopesos() { return totalperitadopesos; }
    public void setTotalperitadopesos(Double totalperitadopesos) { this.totalperitadopesos = totalperitadopesos; }

    public Double getTotalreparacionpesos() { return totalreparacionpesos; }
    public void setTotalreparacionpesos(Double totalreparacionpesos) { this.totalreparacionpesos = totalreparacionpesos; }

    public String getDtnormal() { return dtnormal; }
    public void setDtnormal(String dtnormal) { this.dtnormal = dtnormal; }

    public Double getManodeobrapesos() { return manodeobrapesos; }
    public void setManodeobrapesos(Double manodeobrapesos) { this.manodeobrapesos = manodeobrapesos; }

    public Double getRepuestospesos() { return repuestospesos; }
    public void setRepuestospesos(Double repuestospesos) { this.repuestospesos = repuestospesos; }

    public Double getMatpinturapesos() { return matpinturapesos; }
    public void setMatpinturapesos(Double matpinturapesos) { this.matpinturapesos = matpinturapesos; }

    public Double getVariospesos() { return variospesos; }
    public void setVariospesos(Double variospesos) { this.variospesos = variospesos; }

    public Double getChapapesos() { return chapapesos; }
    public void setChapapesos(Double chapapesos) { this.chapapesos = chapapesos; }

    public Double getMecanicapesos() { return mecanicapesos; }
    public void setMecanicapesos(Double mecanicapesos) { this.mecanicapesos = mecanicapesos; }

    public Double getPinturapesos() { return pinturapesos; }
    public void setPinturapesos(Double pinturapesos) { this.pinturapesos = pinturapesos; }

    public Double getElecpesos() { return elecpesos; }
    public void setElecpesos(Double elecpesos) { this.elecpesos = elecpesos; }

    public Double getTotalhoras() { return totalhoras; }
    public void setTotalhoras(Double totalhoras) { this.totalhoras = totalhoras; }

    public Double getChapahoras() { return chapahoras; }
    public void setChapahoras(Double chapahoras) { this.chapahoras = chapahoras; }

    public Double getMecanicahoras() { return mecanicahoras; }
    public void setMecanicahoras(Double mecanicahoras) { this.mecanicahoras = mecanicahoras; }

    public Double getPinturahoras() { return pinturahoras; }
    public void setPinturahoras(Double pinturahoras) { this.pinturahoras = pinturahoras; }

    public Double getElechoras() { return elechoras; }
    public void setElechoras(Double elechoras) { this.elechoras = elechoras; }

    public String getTitular() { return titular; }
    public void setTitular(String titular) { this.titular = titular; }

    public Integer getPoliza() { return poliza; }
    public void setPoliza(Integer poliza) { this.poliza = poliza; }

    public String getTaller() { return taller; }
    public void setTaller(String taller) { this.taller = taller; }

    public String getCuit() { return cuit; }
    public void setCuit(String cuit) { this.cuit = cuit; }

    public Boolean getOrdentrabajo() { return ordentrabajo; }
    public void setOrdentrabajo(Boolean ordentrabajo) { this.ordentrabajo = ordentrabajo; }

    public Boolean getOrdencompra() { return ordencompra; }
    public void setOrdencompra(Boolean ordencompra) { this.ordencompra = ordencompra; }
}