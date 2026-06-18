package com.blackcat.modelo;

import com.blackcat.repositorio.IRepositorioResultados;
import com.blackcat.repositorio.RepositorioEnMemoria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RuletaTest {

    private Ruleta ruleta;
    private IRepositorioResultados repositorio;

    @BeforeEach
    void setUp() {
        repositorio = new RepositorioEnMemoria();
        ruleta = new Ruleta(repositorio, 1000);
    }

    @Test
    @DisplayName("Caso 1: Constructor rechaza saldo inicial negativo")
    void constructorRechazaSaldoNegativo() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Ruleta(repositorio, -100);
        });
        assertEquals("Saldo inicial inválido", exception.getMessage());
    }

    @Test
    @DisplayName("Caso 2: Depósito válido incrementa el saldo")
    void depositoValidoIncrementaSaldo() {
        ruleta.depositar(500);
        assertEquals(1500, ruleta.getSaldo());
    }

    @Test
    @DisplayName("Caso 3: Apuesta nula es rechazada")
    void apuestaNulaEsRechazada() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ruleta.jugar(null);
        });
        assertEquals("Apuesta requerida", exception.getMessage());
    }

    @Test
    @DisplayName("Caso 4: Apuesta con monto mayor al saldo es rechazada")
    void apuestaMayorAlSaldoEsRechazada() {
        ApuestaRojo apuesta = new ApuestaRojo(2000);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ruleta.jugar(apuesta);
        });
        assertEquals("Saldo insuficiente", exception.getMessage());
    }
}