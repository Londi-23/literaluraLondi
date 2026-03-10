package com.example.literalura.principal;

import com.example.literalura.model.*;
import com.example.literalura.repository.AutorRepository;
import com.example.literalura.repository.LibroRepository;
import com.example.literalura.service.ConsumoAPI;
import com.example.literalura.service.ConvierteDatos;

import java.util.List;
import java.util.Scanner;

public class Principal {

    private Scanner teclado = new Scanner(System.in);

    private LibroRepository libroRepository;
    private AutorRepository autorRepository;

    private ConsumoAPI consumo = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();

    private final String URL = "https://gutendex.com/books/?search=";

    public Principal(LibroRepository libroRepository,
                     AutorRepository autorRepository) {

        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    public void muestraMenu() {

        int opcion = -1;

        while(opcion != 0){

            System.out.println("""
                    
                    ---- LITERALURA ----
                    
                    1 - Buscar libro por titulo
                    2 - Listar libros
                    3 - Listar autores
                    4 - Autores vivos en un año
                    5 - Cantidad de libros por idioma
                    
                    0 - Salir
                    """);

            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion){

                case 1 -> buscarLibro();
                case 2 -> listarLibros();
                case 3 -> listarAutores();
                case 4 -> autoresVivos();
                case 5 -> estadisticasIdioma();
            }
        }
    }

    private void buscarLibro(){

        System.out.println("Escribe el titulo del libro:");
        var titulo = teclado.nextLine();

        var json = consumo.obtenerDatos(URL + titulo.replace(" ", "+"));
        var datos = conversor.obtenerDatos(json, Datos.class);

        if(datos.resultados().isEmpty()){
            System.out.println("Libro no encontrado");
            return;
        }

        DatosLibro libroAPI = datos.resultados().get(0);
        DatosAutor autorAPI = libroAPI.autores().get(0);

        Autor autor = new Autor(
                autorAPI.nombre(),
                autorAPI.nacimiento(),
                autorAPI.fallecimiento()
        );

        autorRepository.save(autor);

        Libro libro = new Libro(
                libroAPI.titulo(),
                libroAPI.idiomas().get(0),
                libroAPI.descargas(),
                autor
        );

        libroRepository.save(libro);

        System.out.println(libro);
    }

    private void listarLibros(){

        List<Libro> libros = libroRepository.findAll();
        libros.forEach(System.out::println);
    }

    private void listarAutores(){

        List<Autor> autores = autorRepository.findAll();
        autores.forEach(System.out::println);
    }

    private void autoresVivos(){

        System.out.println("Ingrese año:");
        int año = teclado.nextInt();

        List<Autor> autores =
                autorRepository
                        .findByNacimientoLessThanEqualAndFallecimientoGreaterThanEqual(año,año);

        autores.forEach(System.out::println);
    }

    private void estadisticasIdioma(){

        System.out.println("Ingrese idioma (ej: en, es, fr)");
        String idioma = teclado.nextLine();

        List<Libro> libros = libroRepository.findByIdioma(idioma);

        System.out.println("Cantidad de libros en " + idioma + ": " + libros.size());
    }
}