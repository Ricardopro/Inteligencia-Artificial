# Inteligencia-Artificial
Practica Inteligencia artificial

Funcionalidades de la inteligencia artificial:
Control de gasolina
Uso de la función hyper-espacio
Buscar bonificación
Punto próximo
Detectar pared
Evitar obstáculo


Comportamiento básico del agente

Primero el agente usara las funciones de estado definidas
para saber si hay que cambiar las velocidades y el uso del
hyper-espacio.
A continuación el agente realizara cuatro acciones básicas
dependiendo de las condiciones de ese momento.
Estas son:
Avanzar, atacar, buscar bonificación y evitar obstáculo

Funciones de estado: Control de gasolina & hyper-espacio

Dependiendo del nivel de gasolina restante el agente
inteligente será más agresivo o menos. Hemos declarado
cuatro niveles, 10000, 8000, 5000, 1000. Dependiendo del
intérvalo, las velocidades lineales como angulares serán
más rápidas. También decrecerán cuanto menor sea el
nivel de combustible.
El robot usará los hyper-espacios cuando se sienta
amenazado por una bala del rival intentando evitarla.
También se usará el hyper-espacio cuando el nivel de
pasividad sea elevado para intentar evitar un posible
bloqueo. Este mecanismo es de seguridad para mantener
el agente vivo y desarrollando su función.

Funciones básicas: Avanzar, atacar, buscar bonificación

El comportamiento base del agente consistirá en ir avanzando
por el mapa.
Cuando el agente detecte un enemigo irá directamente hacia
él disparando cada vez que sea posible. Hay que tener en
cuenta el mecanismo de seguridad que tenemos implementado,
que hará que el agente use el hyper-espacio cuando vea
una bala enemiga acercarse.
El agente, si no ve un enemigo cerca y cuando haya bonificaciones
en la zona, intentará ir hacia la bonificación más cercana,
con el objetivo de conseguir más bonificaciones y por tanto
más combustible.

Funciones básicas: Evitar obstáculo

Hemos creado una función de evitar obstáculo muy parecida a
la proporcionada en el enunciado para intentar evitar las colisiones.
Esta función se activa cuando el agente encuentra una
pared y no puede avanzar. Esta función se llama siempre que
se este buscando bonificación o se este moviendo el agente
por la zona.
Hay que destacar que cuando el agente está atacando, está
tan cegado por su presa que no considera la supervivencia
personal como una prioridad.


