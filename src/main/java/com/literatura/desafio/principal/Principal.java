package com.literatura.desafio.principal;


import com.literatura.desafio.model.Autor;
import com.literatura.desafio.DTO.Datos;
import com.literatura.desafio.model.Libro;

import com.literatura.desafio.repository.AutorRepository;
import com.literatura.desafio.repository.LibroRepository;
import com.literatura.desafio.service.ConsumoAPI;
import com.literatura.desafio.service.ConvierteDatos;

import java.util.*;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private static final String URL_BASE = "https://gutendex.com/books/?search=";
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();

    private LibroRepository libroRepository;
    private AutorRepository autorRepository;

    public Principal(LibroRepository libroRepository, AutorRepository autorRepository)  {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    public Principal() {
    }

    public void mostrarMenu() {
        var opcion = -1;

        while (opcion != 0) {
            var menu = """
                    
                    --- Elija una opción ---
                    
                    1.- Buscar libro por título
                    2.- Listar libros registrados
                    3.- Listar autores registrados
                    4.- Listar autores vivos en un determinado año
                    5.- Listar libros por idioma
                    0.- Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1 -> buscarLibroPorTitulo();
                case 2 -> buscarLibrosRegistrados();
                case 3 -> listarAutoresRegistrados();
                case 4 -> listarAutoresConVida();
                case 5 -> listarLibrosPorIdioma();
                case 0 -> System.out.println("Cierre del programa...\n");
                default -> System.out.println("Opción incorrecta...");
            }
        }
    }

    private void buscarLibroPorTitulo() {
        Datos datos = obtenerDatosLibro();

        if (datos != null) {
            Libro libro = new Libro(datos.resultados().get(0));

            if (libroRepository.findByTituloContainingIgnoreCase(libro.getTitulo()).isPresent()) {
                System.out.println("\nEste libro ya está registrado en la BD ");
            } else {
                libro = libroRepository.save(libro);
                System.out.println("Información del libro");
                System.out.println("------------------");
                System.out.println("Titulo: " + libro.getTitulo());
                System.out.println("Autor: " + libro.getAutores().getNombre());
                System.out.println("Idioma: " + libro.getIdiomas().get(0));
                System.out.println("Numero de descargas: " + libro.getDescargas());
                System.out.println("\n Registro ésitoso del libro " + libro.getTitulo() + " en la BD ");
            }
        }
    }

    private Datos obtenerDatosLibro() {
        System.out.println("\n Ingrese el nombre del libro que desea buscar ");
        var tituloLibro = teclado.nextLine();
        tituloLibro = tituloLibro.replace(" ", "+");
        var json = consumoAPI.obtenerDatos(URL_BASE + tituloLibro);
        Datos datos = conversor.obtenerDatos(json, Datos.class);

        if (datos.contador() == 0 || datos.resultados().isEmpty() || datos.resultados().get(0).titulo() == null) {
            System.out.println("El libro no se encuentra en la API");
            return null;
        } else {
            System.out.println("Libro encontrado");
        }

        return datos;
    }


    private void buscarLibrosRegistrados() {
        List<Libro> libros = libroRepository.findAll();

        if(!libros.isEmpty()) {
            System.out.println("\nLibros registrados en la BD ");
            System.out.println("------------------");
            for(Libro libro : libros) {
                System.out.println("Titulo: " + libro.getTitulo());
                System.out.println("Autor: " + libro.getAutores().getNombre());
                System.out.println("Idioma: " + libro.getIdiomas().get(0));
                System.out.println("Numero de descargas: " + libro.getDescargas());
                System.out.println("------------------");
            }
        }
    }

    private void listarAutoresRegistrados() {
        List<Autor> autores = autorRepository.findAll();

        System.out.println("\nLista de Autores en la BD");
        System.out.println("------------------");
        if(!autores.isEmpty()) {
            for(Autor autor : autores) {
                System.out.println("Nombre: " + autor.getNombre());
                System.out.println("Fecha de Nacimiento: " + autor.getNacimiento());
                System.out.println("Fecha de fallecimiento: " + autor.getFallecimiento());
                System.out.println("Libros: " + autor.getLibros().getTitulo());
                System.out.println("------------------");
            }
        }
    }

    private void listarAutoresConVida() {
        System.out.println("\nEscribe el año para la consulta ");
        var fecha = teclado.nextInt();
        teclado.nextLine();
        List<Autor> autores = autorRepository.findForYear(fecha);
        if(!autores.isEmpty()) {
            for(Autor autor : autores) {
                System.out.println("Nombre: " + autor.getNombre());
                System.out.println("Fecha de Nacimiento: " + autor.getNacimiento());
                System.out.println("Fecha de fllecimiento: " + autor.getFallecimiento());
                System.out.println("Libros: " + autor.getLibros().getTitulo());
                System.out.println("------------------");
            }
        }
    }

    private void listarLibrosPorIdioma() {
        var seleccion = -1;
        var idiomaABuscar = "en";
        while(seleccion !=0) {
            var menuIdioma = """
                    Ingrese el idioma a buscar 
                    
                    1.- Español
                    2.- Inglés
                    3.- Francés
                    4.- Italiano
                    0.- Salir de idioma
                    
                    """;

            System.out.println(menuIdioma);
            seleccion = teclado.nextInt();
            teclado.nextLine();

            switch (seleccion) {
                case 1 -> idiomaABuscar = "es";
                case 2 -> idiomaABuscar = "en";
                case 3 -> idiomaABuscar = "fr";
                case 4 -> idiomaABuscar = "it";
                case 0 -> System.out.println("Salir de idioma ");
                default -> System.out.println("Opción incorrecta...");
            }

            System.out.println(idiomaABuscar);

            List<Libro> libros = libroRepository.buscarPorIdioma(idiomaABuscar);

            if (!libros.isEmpty()) {
                for (Libro libro : libros) {
                    System.out.println("Título: " + libro.getTitulo());
                    System.out.println("Autor: " + libro.getAutores().getNombre());
                    System.out.println("Idioma: " + libro.getIdiomas().get(0));
                    System.out.println("Numero de descargas: " + libro.getDescargas());
                    System.out.println("-----------------");
                }
            }
        }
    }

}

