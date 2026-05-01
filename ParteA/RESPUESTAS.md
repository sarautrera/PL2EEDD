 ---------PRUEBA 1: DATOS ORDENADOS-----------

 PREGUNTA: Verifica que la suma es la misma cuando se suman los elementos de los subárboles izquierdo y derecho. ¿Por qué?
 RESPUESTA: Sí, coinciden (0 + 0 + 8256 = 8256). La suma es la misma porque el valor de la raíz es 0, no hay elementos en el subárbol izquierdo (suma 0) y todos los elementos se encuentran en el subárbol derecho (suma 8256). La suma de las partes sigue siendo igual a la suma de todo el árbol.

 PREGUNTA: ¿Cuál es la altura del árbol?
 RESPUESTA: 129

 PREGUNTA: ¿Cuál es el camino para llegar al valor 110? ¿Cuál es su longitud de camino?
 RESPUESTA: Camino al 110: [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110]
            Longitud del camino: 110

 ---------PRUEBA 2: DATOS ALEATORIOS-------------

 PREGUNTA:Verifica que la suma es la misma cuando se suman los elementos de los subárboles izquierdo y derecho. ¿Por qué?
 RESPUESTA: Sí, coinciden (64 + 121 + 498 = 683). La suma es la misma porque el árbol se divide lógicamente entre su raíz (64), el subárbol izquierdo que acumula 121, y el subárbol derecho que acumula 498. La suma de estos tres componentes es exactamente la suma total del árbol.

 PREGUNTA:¿Cuál es la altura del árbol? ¿por qué?
 RESPUESTA: 5. Es mucho menor que en la prueba 1 porque al insertar datos desordenados o al azar, las inserciones se distribuyen de manera más uniforme entre las ramas izquierda y derecha, lo que mantiene el árbol equilibrado y evita que se vuelva una estructura lineal y profunda.

 PREGUNTA:¿Cuál es el camino para llegar al valor 110? ¿Cuál es su longitud de camino?
 RESPUESTA: Camino al 110: [64, 96, 112, 110]
            Longitud del camino: 3

 -------------OTRAS PREGUNTAS---------------------
 PREGUNTA:Explique las diferencias (si las ha habido) de los resultados obtenidos entre los dos programas de prueba.
 RESPUESTA: Las diferencias principales radican en la forma del árbol y su altura:

            ·Datos ordenados: Al insertar los elementos de forma secuencial, el árbol se degenera convirtiéndose en una estructura lineal (se comporta casi como una lista enlazada donde cada nodo tiene solo un hijo). Esto genera una altura muy grande (129) y hace que la búsqueda del valor 110 sea muy larga y costosa, recorriendo 110 nodos.
            ·Datos aleatorios: Al usar valores distribuidos de forma más equilibrada, el árbol divide bien las ramas izquierda y derecha a partir de la raíz. Esto reduce la altura a solo 5 niveles y hace que el camino al valor 110 sea mucho más corto (solo 4 nodos, longitud 3).

 PREGUNTA:¿Qué sucede con los resultados si ejecuta los programas de prueba varias veces?
 RESPUESTA: No ocurre ningún cambio. Los resultados son exactamente los mismos (deterministas). La inserción de datos, el cálculo de las sumas y la estructura resultante no dependen del azar en tiempo de ejecución, sino de los datos de entrada previamente establecidos.