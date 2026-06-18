package com.blackcat.modelo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EstadisticasTest {

    private List<Resultado> historial;
    private Usuario usuario;

    @BeforeEach
    void setUp() {
        usuario = new Usuario("test", "123", "Test User");
        historial = new ArrayList<>();

        // Historial: Acierto, Fallo, Acierto, Fallo, Acierto
        // Racha máxima = 1 (no hay aciertos consecutivos)
        historial.add(new Resultado(usuario, 7, new ApuestaRojo(100), true));
        historial.add(new Resultado(usuario, 15, new ApuestaRojo(100), false));
        historial.add(new Resultado(usuario, 3, new ApuestaRojo(100), true));
        historial.add(new Resultado(usuario, 0, new ApuestaRojo(100), false));
        historial.add(new Resultado(usuario, 36, new ApuestaPar(100), true));
    }

    @Test
    @DisplayName("Caso 5: Estadísticas calculan correctamente racha máxima y tipo más jugado")
    void estadisticasCalculanCorrectamente() {
        Estadisticas estadisticas = new Estadisticas(historial);

        assertEquals(5, estadisticas.getTotalJugadas());
        assertEquals(3, estadisticas.getVictorias());
        assertEquals(60.0, estadisticas.getPorcentajeVictorias(), 0.01);
        assertEquals(1, estadisticas.getRachaMaxima());
        assertEquals("Rojo", estadisticas.getTipoMasJugado());
    }

    @Test
    @DisplayName("Estadísticas con historial vacío retornan valores por defecto")
    void estadisticasConHistorialVacio() {
        Estadisticas estadisticas = new Estadisticas(new ArrayList<>());

        assertEquals(0, estadisticas.getTotalJugadas());
        assertEquals(0, estadisticas.getVictorias());
        assertEquals(0.0, estadisticas.getPorcentajeVictorias(), 0.01);
        assertEquals(0, estadisticas.getRachaMaxima());
        assertEquals("N/A", estadisticas.getTipoMasJugado());
    }
}