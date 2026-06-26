package com.statisticsCesvi;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ExcelReader {

    private static final String[] EXPECTED_HEADERS = {
        "Perito", "Siniestro", "Fecha Inf./Siniestro", "Vehiculo", "Año",
        "Patente", "Provincia", "Loalidad", "Dirección", "Total Peritado ($)",
        "Total Reparación ($)", "DT/DTE/DNC", "Mano de Obra", "Repuestos ($)",
        "Mat. Pint.($)", "Varios($)", "Chapa($)", "Mec.($)", "Pint.($)",
        "Elec.($)", "Total(hs)", "Chapa(hs)", "Mec.(hs)", "Pint.(hs)",
        "Elec.(hs)", "Titular", "Poliza", "Taller", "CUIT/RUT",
        "OT Enviada", "OC Enviada"
    };

    public List<ReportRowDto> read(String downloadDir, long downloadStartedAt)
            throws IOException, InterruptedException {
        File file = waitForDownload(downloadDir, downloadStartedAt);
        return parseHtml(file);
    }

    private File waitForDownload(String dir, long since) throws InterruptedException {
        File folder = new File(dir);
        long deadline = System.currentTimeMillis() + 30_000;
        while (System.currentTimeMillis() < deadline) {
            File[] candidates = folder.listFiles(f ->
                f.getName().endsWith(".xls")
                && f.lastModified() >= since
                && !new File(f.getAbsolutePath() + ".part").exists()
            );
            if (candidates != null && candidates.length > 0) {
                Arrays.sort(candidates, Comparator.comparingLong(File::lastModified).reversed());
                return candidates[0];
            }
            // filesystem poll — no Selenium WebDriverWait equivalent for file system
            Thread.sleep(500);
        }
        throw new RuntimeException("XLS download timed out in: " + dir);
    }

    private List<ReportRowDto> parseHtml(File file) throws IOException {
        Document doc = Jsoup.parse(file, null);
        Elements rows = doc.select("tr");
        if (rows.isEmpty()) {
            throw new IllegalStateException("No rows found in: " + file.getName());
        }
        validateHeaders(rows.first());
        List<ReportRowDto> result = new ArrayList<>();
        for (int i = 1; i < rows.size(); i++) {
            Elements cells = rows.get(i).select("td");
            if (cells.size() < EXPECTED_HEADERS.length) continue;
            result.add(mapRow(cells));
        }
        return result;
    }

    private void validateHeaders(Element headerRow) {
        Elements cells = headerRow.select("td, th");
        for (int i = 0; i < EXPECTED_HEADERS.length; i++) {
            String actual = i < cells.size() ? cells.get(i).text().trim() : "";
            if (!EXPECTED_HEADERS[i].equals(actual)) {
                throw new IllegalStateException(
                    "Header mismatch at col " + i
                    + ": expected '" + EXPECTED_HEADERS[i] + "' got '" + actual + "'"
                );
            }
        }
    }

    private ReportRowDto mapRow(Elements cells) {
        ReportRowDto dto = new ReportRowDto();
        dto.setPerito(text(cells, 0));
        dto.setSiniestro(text(cells, 1));
        dto.setFechaInforme(text(cells, 2));
        dto.setVehiculo(text(cells, 3));
        dto.setAnio(text(cells, 4));
        dto.setPatente(text(cells, 5));
        dto.setProvincia(text(cells, 6));
        dto.setLocalidad(text(cells, 7));
        dto.setDireccion(text(cells, 8));
        dto.setTotalPeritado(number(cells, 9));
        dto.setTotalReparacion(number(cells, 10));
        dto.setDtDteDnc(text(cells, 11));
        dto.setManoDeObra(number(cells, 12));
        dto.setRepuestos(number(cells, 13));
        dto.setMatPintura(number(cells, 14));
        dto.setVarios(number(cells, 15));
        dto.setChapa(number(cells, 16));
        dto.setMecanica(number(cells, 17));
        dto.setPintura(number(cells, 18));
        dto.setElectricidad(number(cells, 19));
        dto.setTotalHoras(number(cells, 20));
        dto.setChapaHoras(number(cells, 21));
        dto.setMecanicaHoras(number(cells, 22));
        dto.setPinturaHoras(number(cells, 23));
        dto.setElectricidadHoras(number(cells, 24));
        dto.setTitular(text(cells, 25));
        dto.setPoliza(text(cells, 26));
        dto.setTaller(text(cells, 27));
        dto.setCuitRut(text(cells, 28));
        dto.setOtEnviada(text(cells, 29));
        dto.setOcEnviada(text(cells, 30));
        return dto;
    }

    private String text(Elements cells, int col) {
        String val = cells.get(col).text().trim();
        return val.isEmpty() ? null : val;
    }

    private Double number(Elements cells, int col) {
        String val = text(cells, col);
        if (val == null) return null;
        // formato argentino: 1.234,56 → separar miles con punto, decimal con coma
        int lastDot   = val.lastIndexOf('.');
        int lastComma = val.lastIndexOf(',');
        if (lastComma > lastDot) {
            val = val.replace(".", "").replace(",", ".");
        } else {
            val = val.replace(",", "");
        }
        try {
            return Double.parseDouble(val);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}