package com.blackcat.repositorio;

import com.blackcat.modelo.Resultado;
import com.blackcat.modelo.Usuario;
import java.util.List;

public interface IRepositorioResultados {

    void guardarResultado(Resultado resultado);

    List<Resultado> obtenerResultadosPorUsuario(Usuario usuario);

    List<Resultado> obtenerTodosLosResultados();

    void cargarDatosIniciales();

    void guardarTodos(List<Resultado> resultados);
}
