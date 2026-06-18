package com.blackcat.repositorio;

import com.blackcat.modelo.Resultado;
import com.blackcat.modelo.Usuario;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RepositorioEnMemoria implements IRepositorioResultados {

    private final List<Resultado> resultados = new ArrayList<>();

    @Override
    public void guardarResultado(Resultado resultado) {
        resultados.add(resultado);
        System.out.println("✅ Resultado guardado en memoria");
    }

    @Override
    public List<Resultado> obtenerResultadosPorUsuario(Usuario usuario) {
        return resultados.stream()
                .filter(r -> r.getUsuario().getNombreUsuario().equals(usuario.getNombreUsuario()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Resultado> obtenerTodosLosResultados() {
        return new ArrayList<>(resultados);
    }

    @Override
    public void cargarDatosIniciales() {

        System.out.println("Repositorio en memoria inicializado");
    }

    @Override
    public void guardarTodos(List<Resultado> resultados) {
        this.resultados.clear();
        this.resultados.addAll(resultados);
    }
}
