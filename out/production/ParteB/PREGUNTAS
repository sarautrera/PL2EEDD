 PREGUNTA:¿Cuál es el camino mínimo entre dos entidades A y B del grafo?
 RESPUESTA: Por ejemplo, el camino mínimo entre Einstein y su ciudad es: [persona:Albert Einstein, lugar:Ulm]

 PREGUNTA:Dado un archivo de datos que se carga en el grafo, ¿genera un grafo disjunto?  Cree dos archivos que generen cada opción posible y compruebe en el código.
 RESPUESTA: Sí, el resto de la respuesta se verá reflejado en el código

 PREGUNTA:Suponiendo un grafo de conocimiento general con la información de los premios Nobel de todas las áreas, cómo harías para responder a la pregunta: ¿Qué físico famoso nació en la misma ciudad que Einstein?. Crea el fichero de datos para completar el grafo y poder extraer la respuesta a esta pregunta. Crea el código para verificarlo.
 RESPUESTA: En la terminal te va a aparecer como solución Hermann Anselm, el resto de la respuesta se verá reflejado en el código

 PREGUNTA: Añada una tripleta <"persona:Antonio", "nace_en", "lugar:Villarrubia de los Caballeros"> al grafo. Liste cuáles son los lugares de nacimiento de los premios Nobel. ¿Qué caminos necesita recorrer para que su respuesta fuese correcta?
 RESPUESTA: En mi caso, me sale en la terminal persona:Albert Einstein tiene un Nobel y nacio en lugar:Ulm y persona:Marie Curie tiene un Nobel y nacio en lugar:Varsovia debido a los valores que he fijado. para que la respuesta sea correcta, el sistema no recorre un camino lineal entre dos puntos, sino que realiza un salto a múltiples vecinos desde cada nodo. Necesita validar simultáneamente que existe el camino hacia el nodo del premio y el camino hacia el nodo del lugar de nacimiento. Si uno de los dos caminos no existe para un nodo concreto (como ocurre con Antonio), se descarta de la consulta.


 PREGUNTA: ¿Qué tipos de nodos tiene el grafo?
 RESPUESTA: El grafo se compone fundamentalmente de tres elementos lógicos:
            Sujeto: El recurso que se está describiendo (ej. `persona:Albert Einstein`). Son nodos de origen.
            Predicado: La propiedad o relación que describe al sujeto (ej. `nace_en`, `premio:Nobel`). Representan las aristas dirigidas en el grafo.
            Objeto : El valor de la propiedad (ej. `lugar:Ulm`, `1921`). Son nodos destino (que a su vez pueden ser sujetos de otras relaciones).
            A nivel de tipología semántica, tenemos nodos de tipo `persona`, `lugar`, `premio`, `tipo` y valores puros (como fechas).


 PREGUNTA: ¿Qué es una ontología? ¿Qué relación tiene con los grafos? ¿Podríamos crear una ontología para nuestro problema? ¿Qué haríamos con ella?
 RESPUESTA: Una ontología es un modelo formal que define un conjunto de conceptos dentro de un dominio específico y las relaciones jerárquicas o lógicas entre ellos. Es como el "esquema" o el diccionario que da sentido a los datos.
            Mientras que el Grafo de Conocimiento almacena los datos concretos (Einstein nació en Ulm), la ontología define las reglas de esos datos (Un "Físico" es una subclase de "Persona"; "nace_en" debe conectar una "Persona" con un "Lugar"). La ontología dota de semántica al grafo.
            Sí, podríamos crear una usando estándares como OWL (Web Ontology Language) o RDFS (RDF Schema).
            La usaríamos para la **inferencia lógica**. Por ejemplo, si en la ontología definimos que si alguien gana un "premio:Nobel" automáticamente es un "tipo:Galardonado", el grafo podría inferir y añadir esa categoría a Einstein y Marie Curie sin que nosotros lo hayamos programado explícitamente en el JSON. También validaría errores (como impedir decir que un lugar nace en una persona).