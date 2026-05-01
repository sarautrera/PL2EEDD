package ParteB;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        // test para comprobar si hay partes del grafo que no estan conectadas entre si
        System.out.println("TEST GRAFO DISJUNTO");
        System.out.println();
        probarGrafo("data/datos.json", "persona:Albert Einstein", "persona:Marie Curie");


        // test para ver si existe un camino directo o indirecto entre dos puntos
        System.out.println();
        System.out.println("TEST GRAFO CONEXO");
        System.out.println();
        probarGrafo("data/datos.json", "persona:Albert Einstein", "lugar:Ulm");

        Grafo g = probarGrafo("data/datos.json", "persona:Albert Einstein", "pais:Alemania");

        if (g != null) {
            if (g.esDisjunto()) {
                System.out.println("el grafo es disjunto");
            } else {
                System.out.println("el grafo es conexo.");
            }
        }


        // busca personas que comparten ciudad de nacimiento y profesion segun el diseño del uml
        System.out.println();
        System.out.println("TEST: fisicos nacidos en la misma ciudad que Einstein");
        System.out.println();
        probarBusquedaHijos("data/datos.json");
        System.out.println("Insertar Antonio");

        // añade una nueva relacion al grafo cargado desde el archivo json
        probarAñadirTripleta("data/datos.json",
                "persona:Antonio",
                "nace_en",
                "lugar:Villarrubia de los Caballeros");


        // muestra la lista de ganadores del nobel y su origen
        System.out.println();
        System.out.println("TEST: lugar nacimiento premios nobel");
        System.out.println();
        probarConsultaNobel("data/datos.json");

    }

    // este metodo lee el archivo y busca el camino mas corto entre dos nombres
    public static Grafo probarGrafo(String rutaArchivo, String origen, String destino) {
        Grafo grafo = new Grafo();
        try {
            // lee el fichero y separa el texto para identificar cada objeto o relacion
            String contenido = new String(Files.readAllBytes(Paths.get(rutaArchivo)));
            String[] bloques = contenido.split("\\{");

            for (String bloque : bloques) {
                if (bloque.contains("\"s\":") && bloque.contains("\"p\":") && bloque.contains("\"o\":")) {
                    grafo.addTripleta(extraer(bloque, "s"),
                            extraer(bloque, "p"),
                            extraer(bloque, "o"));
                }
            }

            System.out.println("camino de " + origen + " a " + destino + ":");
            grafo.buscarCaminoMinimo(origen, destino);


        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }
        return grafo;
    }


    // busca informacion especifica de einstein para encontrar a otros cientificos parecidos
    public static void probarBusquedaHijos(String rutaArchivo) {
        Grafo g = new Grafo();

        try {
            // cargamos los datos del json al grafo para poder trabajar con ellos
            String contenido = new String(Files.readAllBytes(Paths.get(rutaArchivo)));
            String[] bloques = contenido.split("\\{");
            for (String b : bloques) {
                if (b.contains("\"s\":") && b.contains("\"p\":") && b.contains("\"o\":")) {
                    g.addTripleta(extraer(b, "s"), extraer(b, "p"), extraer(b, "o"));
                }
            }

            System.out.println("¿Qué físico famoso nació en la misma ciudad que Einstein?");

            // primero localizamos donde nacio einstein mirando sus conexiones
            String ciudadEinstein = "";
            Nodo einstein = g.buscar("persona:Albert Einstein");

            if (einstein != null) {
                Arista a = einstein.primeraArista;
                while (a != null) {
                    if (a.predicado.equals("nace_en")) {
                        ciudadEinstein = a.destino.nombre;
                        break;
                    }
                    a = a.siguiente;
                }
            }

            if (!ciudadEinstein.equals("")) {
                System.out.println("Einstein nació en: " + ciudadEinstein);
                System.out.println("otros físicos nacidos allí:");

                // usamos el metodo del grafo para filtrar por ciudad y profesion
                g.buscarCientificosPorCiudadYProfesion(ciudadEinstein, "Fisico", "persona:Albert Einstein");
            } else {
                System.out.println("no se encontro la ciudad de Einstein.");
            }

        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }
    }

    // prueba a meter datos nuevos en el grafo y comprueba si se han guardado bien
    public static void probarAñadirTripleta(String rutaArchivo, String s, String p, String o) {
        Grafo g = new Grafo();
        cargarGrafoDesdeFichero(g, rutaArchivo);

        System.out.println("intentando añadir: <" + s + ", " + p + ", " + o + ">");
        g.addTripleta(s, p, o);

        // comprobamos que el nodo existe y que tiene la conexion que acabamos de crear
        Nodo n = g.buscar(s);
        if (n != null) {
            String valorEncontrado = encontrarValorPredicado(n, p);
            if (valorEncontrado != null && valorEncontrado.equals(o)) {
                System.out.println("el nodo '" + s + "' ahora está conectado con '" + o + "' mediante '" + p + "'.");
            } else {
                System.out.println("aviso: el nodo existe pero la relacion no coincide.");
            }
        } else {
            System.out.println("error: no se pudo crear o encontrar el nodo sujeto.");
        }
    }

    // carga el grafo y llama a la funcion de listar los premios nobel
    public static void probarConsultaNobel(String rutaArchivo) {
        Grafo g = new Grafo();
        if (cargarGrafoDesdeFichero(g, rutaArchivo)) {
            System.out.println("científicos con Nobel encontrados:");
            g.listarNacimientosPremiosNobel();
        }
    }

    // funcion auxiliar para limpiar el texto del json y sacar solo los nombres
    private static String extraer(String bloque, String clave) {
        String busqueda = "\"" + clave + "\": \"";
        int inicio = bloque.indexOf(busqueda);
        if (inicio == -1) return "";
        inicio += busqueda.length();
        int fin = bloque.indexOf("\"", inicio);
        if (fin == -1) return "";
        return bloque.substring(inicio, fin);
    }

    // busca dentro de un nodo una relacion concreta para devolver el nombre del destino
    private static String encontrarValorPredicado(Nodo n, String predicadoBuscado) {
        Arista a = n.primeraArista;
        while (a != null) {
            if (a.predicado.equals(predicadoBuscado)) {
                return a.destino.nombre;
            }
            a = a.siguiente;
        }
        return null;
    }

    // metodo para rellenar el grafo con la informacion del archivo de texto
    private static boolean cargarGrafoDesdeFichero(Grafo g, String ruta) {
        try {
            String contenido = new String(Files.readAllBytes(Paths.get(ruta)));
            String[] bloques = contenido.split("\\{");
            for (String b : bloques) {
                if (b.contains("\"s\":") && b.contains("\"p\":") && b.contains("\"o\":")) {
                    g.addTripleta(extraer(b, "s"), extraer(b, "p"), extraer(b, "o"));
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}