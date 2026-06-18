package com.blackcat.modelo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Estadisticas {

    private final List<Resultado> historial;

    public Estadisticas(List<Resultado> historial) {
        this.historial = historial;
    }

    public int getTotalJugadas() {
        return historial.size();
    }

    public int getVictorias() {
        int victorias = 0;
        for (Resultado r : historial) {
            if (r.isAcierto()) {
                victorias++;
            }
        }
        return victorias;
    }

    public double getPorcentajeVictorias() {
        int total = getTotalJugadas();
        if (total == 0) return 0.0;
        return (double) getVictorias() / total * 100;
    }

    public int getRachaMaxima() {
        int rachaActual = 0;
        int rachaMax = 0;
        for (Resultado r : historial) {
            if (r.isAcierto()) {
                rachaActual++;
                if (rachaActual > rachaMax) {
                    rachaMax = rachaActual;
                }
            } else {
                rachaActual = 0;
            }
        }
        return rachaMax;
    }

    public String getTipoMasJugado() {
        if (historial.isEmpty()) {
            return "N/A";
        }

        Map<String, Integer> conteo = new HashMap<>();
        for (Resultado r : historial) {
            String nombre = r.getApuesta().getNombre();
            conteo.put(nombre, conteo.getOrDefault(nombre, 0) + 1);
        }

        String tipoMasJugado = "";
        int maxConteo = 0;
        for (Map.Entry<String, Integer> entry : conteo.entrySet()) {
            if (entry.getValue() > maxConteo) {
                maxConteo = entry.getValue();
                tipoMasJugado = entry.getKey();
            }
        }
        return tipoMasJugado;
    }

    public String getResumen() {
        StringBuilder sb = new StringBuilder();
        sb.append("ESTADÍSTICAS DEL JUGADOR\n");
        sb.append("================================\n");
        sb.append("Total jugadas: ").append(getTotalJugadas()).append("\n");
        sb.append("Victorias: ").append(getVictorias()).append("\n");
        sb.append("Porcentaje de victorias: ").append(String.format("%.2f", getPorcentajeVictorias())).append("%\n");
        sb.append("Racha máxima de aciertos: ").append(getRachaMaxima()).append("\n");
        sb.append("Tipo de apuesta más jugado: ").append(getTipoMasJugado()).append("\n");
        return sb.toString();
    }
}