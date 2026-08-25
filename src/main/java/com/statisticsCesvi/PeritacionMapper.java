package com.statisticsCesvi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PeritacionMapper {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static PeritacionDto map(ReportRowDto row, Boolean asegurado, Boolean cleas, String danio, String tipo) {
        PeritacionDto dto = new PeritacionDto();
        dto.setSiniestro(row.getSiniestro() != null ? row.getSiniestro().replace("/", "") : null);
        String[] fechas = splitFechas(row.getFechaInforme());
        dto.setFechainforme(parseDate(fechas[0]));
        dto.setFechasiniestro(parseDate(fechas[1]));
        dto.setVehiculo(row.getVehiculo());
        dto.setAnio(parseInt(row.getAnio()));
        dto.setPatente(row.getPatente());
        dto.setProvincia(row.getProvincia());
        dto.setLocalidad(row.getLocalidad());
        dto.setDireccion(row.getDireccion());
        dto.setTotalperitadopesos(row.getTotalPeritado());
        dto.setTotalreparacionpesos(row.getTotalReparacion());
        dto.setDtnormal(row.getDtDteDnc());
        dto.setManodeobrapesos(row.getManoDeObra());
        dto.setRepuestospesos(row.getRepuestos());
        dto.setMatpinturapesos(row.getMatPintura());
        dto.setVariospesos(row.getVarios());
        dto.setChapapesos(row.getChapa());
        dto.setMecanicapesos(row.getMecanica());
        dto.setPinturapesos(row.getPintura());
        dto.setElecpesos(row.getElectricidad());
        dto.setTotalhoras(row.getTotalHoras());
        dto.setChapahoras(row.getChapaHoras());
        dto.setMecanicahoras(row.getMecanicaHoras());
        dto.setPinturahoras(row.getPinturaHoras());
        dto.setElechoras(row.getElectricidadHoras());
        dto.setTitular(row.getTitular());
        dto.setPoliza(parseInt(row.getPoliza()));
        dto.setTaller(row.getTaller());
        dto.setCuit(row.getCuitRut());
        dto.setOrdentrabajo(parseBool(row.getOtEnviada()));
        dto.setOrdencompra(parseBool(row.getOcEnviada()));
        dto.setAsegurado(asegurado);
        dto.setCleas(cleas);
        dto.setDanio(danio);
        dto.setTipo(tipo);
        return dto;
    }

    private static String[] splitFechas(String val) {
        if (val == null || val.isBlank()) return new String[]{null, null};
        String[] parts = val.trim().split(" ");
        return new String[]{
            parts.length > 0 ? parts[0] : null,
            parts.length > 1 ? parts[1] : null
        };
    }

    private static LocalDate parseDate(String val) {
        if (val == null || val.isBlank()) return null;
        return LocalDate.parse(val.trim(), DATE_FORMAT);
    }

    private static Integer parseInt(String val) {
        if (val == null || val.isBlank()) return null;
        try {
            return Integer.parseInt(val.trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private static Boolean parseBool(String val) {
        if (val == null) return null;
        return val.trim().equalsIgnoreCase("si");
    }
}
