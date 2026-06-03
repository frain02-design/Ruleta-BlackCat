package com.blackcat.repositorio;

import com.blackcat.modelo.ApuestaBase;
import com.blackcat.modelo.ApuestaNegro;
import com.blackcat.modelo.ApuestaPar;
import com.blackcat.modelo.ApuestaRojo;
import com.blackcat.modelo.ApuestaImpar;
import com.blackcat.modelo.Resultado;
import com.blackcat.modelo.Usuario;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class RepositorioArchivo implements IRepositorioResultados {

    private static final String ARCHIVO = "historial.csv";
    private final List<Resultado> cache = new ArrayList<>();
    private boolean cacheCargado = false;

    private void cargarCache() {
        if (cacheCargado) return;
        cache.clear();
        File archivo = new File(ARCHIVO);
        if (!archivo.exists()) {
            cacheCargado = true;
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                Resultado r = parsearLinea(linea);
                if (r != null) {
                    cache.add(r);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer archivo: " + e.getMessage());
        }
        cacheCargado = true;
    }

    private Resultado parsearLinea(String linea) {
        try {
            String[] partes = linea.split(",");
            if (partes.length < 7) return null;

            String nombreUsuario = partes[0];
            String nombreCompleto = partes[1];
            int numero = Integer.parseInt(partes[2]);
            String tipoApuestaNombre = partes[3];
            boolean acierto = Boolean.parseBoolean(partes[4]);
            int monto = Integer.parseInt(partes[5]);
            LocalDateTime fecha = LocalDateTime.parse(partes[6], DateTimeFormatter.ISO_LOCAL_DATE_TIME);

            Usuario usuario = new Usuario(nombreUsuario, "", nombreCompleto);
            ApuestaBase apuesta = crearApuesta(tipoApuestaNombre, monto);

            Resultado resultado = new Resultado(usuario, numero, apuesta, acierto);
            return resultado;
        } catch (Exception e) {
            System.err.println("Error al parsear línea: " + linea);
            return null;
        }
    }

    private ApuestaBase crearApuesta(String tipo, int monto) {
        switch (tipo) {
            case "Rojo": return new ApuestaRojo(monto);
            case "Negro": return new ApuestaNegro(monto);
            case "Par": return new ApuestaPar(monto);
            case "Impar": return new ApuestaImpar(monto);
            default: return new ApuestaRojo(monto);
        }
    }

    private String formatearLinea(Resultado r) {
        return String.join(",",
                r.getUsuario().getNombreUsuario(),
                r.getUsuario().getNombreCompleto(),
                String.valueOf(r.getNumero()),
                r.getApuesta().getNombre(),
                String.valueOf(r.isAcierto()),
                String.valueOf(r.getMonto()),
                r.getFecha().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        );
    }

    private void guardarCacheEnDisco() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO))) {
            for (Resultado r : cache) {
                writer.write(formatearLinea(r));
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al guardar archivo: " + e.getMessage());
        }
    }

    @Override
    public void guardarResultado(Resultado resultado) {
        cargarCache();
        cache.add(resultado);
        guardarCacheEnDisco();
        System.out.println("💾 Resultado guardado en archivo");
    }

    @Override
    public List<Resultado> obtenerResultadosPorUsuario(Usuario usuario) {
        cargarCache();
        List<Resultado> resultados = new ArrayList<>();
        for (Resultado r : cache) {
            if (r.getUsuario().getNombreUsuario().equals(usuario.getNombreUsuario())) {
                resultados.add(r);
            }
        }
        return resultados;
    }

    @Override
    public List<Resultado> obtenerTodosLosResultados() {
        cargarCache();
        return new ArrayList<>(cache);
    }

    @Override
    public void cargarDatosIniciales() {
        cargarCache();
        System.out.println("📁 Repositorio de archivo inicializado. Cargados " + cache.size() + " resultados");
    }

    @Override
    public void guardarTodos(List<Resultado> resultados) {
        cache.clear();
        cache.addAll(resultados);
        guardarCacheEnDisco();
    }
}
