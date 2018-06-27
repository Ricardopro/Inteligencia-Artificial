# Inteligencia-Artificial
Segunda practica de Inteligencia Artificial “cerca”

Para la implementación de esta práctica hemos utilizado uno de los método de búsqueda explicados en clase. En concreto, la búsqueda en amplitud. Vamos generando el árbol hasta encontrar que uno de los nodos está en el borde del mapa y por tanto es una salida para que el “bicho” salga y gane la partida. 

Calculamos el camino a seguir con la funcionalidad calculacasella. Dentro de este algoritmo tenemos la primera parte de la iteración. Calculamos los nodos vecinos que no tengan piedras y hacemos búsqueda por anchura en cada uno de los nodos. 

Una de las heurísticas aplicadas ha sido la de controlar la cantidad de nodos generados por el árbol. Otra de ellas es la longitud de nodos que faltan para llegar a la casilla que hará ganar al bicho.
Ya que muchas veces, debido a la hexagonalidad del problema nos encontramos varias soluciones diferentes.

Hemos aplicado una penalización de peso 5000 para que el programa no elija cada vez un nodo anterior y se quede un bucle. Penalizando así que recorra el mismo camino una y otra vez. A no ser que se quede en un rincón.

El algoritmo cada vez que se coloca una piedra en el mapa vuelve a calcular el árbol y encontrar los caminos por donde se puede ir el bicho para escaparse de la prisión.


Se han realizado un conjunto de funcionalidades que nos permiten llevar a cabo la realización de esta práctica y son las siguientes:


calculaCasella(Punt inici): Esta función realiza el cálculo del próximo movimiento que debe realizar el “bicho”, si es que es posible, una vez que la persona ha realizado el suyo. Para ello tenemos en cuenta cada piedra que hay en el tablero en cada movimiento y recalculamos a través de la búsqueda en anchura el camino que debe tomar el bicho hasta conseguir su objetivo.

Dos funciones que nos ayudan a trabajar con nodos y puntos conjuntamente:
Convertirpuntanode(Punt inici): Convertimos un punto x,y a coordenadas de nodo x,y 

Convertirnodeapunt(Node inici): Convertimos las coordenadas del nodo x,y a punto  x,y

Buscarsolucion(Punt inici,Punt ir): Desde el punto en el que estamos situados hasta el punto de salida nos calcula las posibles soluciones si son posibles, sino nos devuelve  que no ha encontrado solución. Es donde se aplica el algoritmo de búsqueda. Hemos aplicado un backtrackin de forma iterativa para calcular el árbol. Hemos cortado las ramas donde los nodos se repitieran para no tener arboles enormes.

Se hace uso de variables auxiliares para calcular el árbol. 
i, d: numero de nodos del árbol generado, la i la hemos usado de contador para que la aplicación no realice mas de 9800 sucesores del árbol.
j: puntero que nos indica si hemos comprobado y visitado el nodo. Comprobando si el nodo es salida. Aplicando graella.isASolution para comprobar cada nodo.
w: Puntero donde hemos hecho los descendentes de ese nodo.

calcularcaminoverdadero (ArrayList<Punt> a, Punt b): esta funcionalidad nos convierte el árbol generado por la búsqueda por anchura en un camino. Estos caminos se muestran luego cuando usamos la funcionalidad buscarcamí de la aplicación android.

calculardescendentes (Punt inici): Nos devuelve una lista con todos los nodos descendientes desde el punto que indicamos.  Ya nos filtra los nodos que tienen piedras para que no nos tengamos que preocupar luego de ellos.


calculardescendentespunt (Punt inici) Nos devuelve una lista con todos los puntos descendientes desde el punto inicial. Ya nos filtra los nodos que tienen piedras para que no nos tengamos que preocupar luego de ellos. La misma funcionalidad anterior, pero nos devuelve una array de puntos en vez de nodos.

printarray (ArrayList<Node> llista): nos imprime una array de nodos. La hemos usado para comprobar todos los pasos y corregir errores del código. 


Variables interesantes.
peso = new ArrayList<Integer>(); array donde nos guarda localmente la heurística que hemos utilizado cada vez que se calcula el camino deseado.

llistacaminos = new ArrayList<ArrayList<Punt>>(); Array donde se nos guarda los caminos que ha encontrado el algoritmo. Se usa para guardar el camino en graella.camiSolucio.addAll(llistacaminos.get(wmenorpeso)); para que se pueda ver desde la aplicación android.

Wmenorpeso: variable que se usa para elegir el camino con menor heurística.

llistaprueba2 = new ArrayList<Punt>(); Lista donde se calculan los posibles nodos que puede ir el “bicho” en un movimiento.

llistasolucionsparcials; Lista donde se guardan los nodos que tienen solución y luego aplicando la heurística encontramos el que sea mas indicado.

trobatcamins; Integer donde nos cuenta la cantidad de caminos solución que ha encontrado el algoritmo.


Hemos modificado la clase Punt para añadir que tenga un puntprevi como la clase nodo. La usamos para conseguir el camino que hemos encontrado para pintarlo y decidir una de las heurísticas. (longitud del camino).
p.puntprevi=originallista.get(w);

Hemos utilizado estas dos variables para que el algoritmo no repita el camino anterior y vaya el nodo anterior. Aplicando la penalización de peso por la heurística.
this.aaa=inici.x;
this.bbb=inici.y;
 

Funcionalidades fallidas: Backtracking(Node n), fue de las primeras ideas para resolver el problema que tuvimos, pero con el entorno no funcionaba debidamente, ya que la recursividad hacia uso de mucha memoria donde el emulador no tenia capacidad suficiente para soportarla. 

Impresión de datos por consola: System.out.println, Cada vez que se coloca una piedra se calcula un camino. Se muestra los puntos donde el algoritmo ha calculado que se puede ir y las heurísticas. Tanto la de peso de nodos generados por el árbol como la de longitud del camino.