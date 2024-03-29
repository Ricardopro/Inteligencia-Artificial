package agents;

// Exemple de Bitxo
public class Bitxo2 extends Agent {

    static final boolean DEBUG = true;

    final int PARET = 0;
    final int NAU = 1;
    final int RES = -1;

    final int ESQUERRA = 0;
    final int CENTRAL = 1;
    final int DRETA = 2;
    final int DISTANCIA_MINIMA = 45;

    Estat estat;
    int espera = 0;
    long temps;
    int cont = 0;
    boolean enemic;
    int impacte_rebut;
    int passivitatanterior;
    int passivitat_menjar;

    public Bitxo2(Agents pare) {
        super(pare, "Bitxo2", "imatges/robotank2.gif");
    }

    @Override
    public void inicia() {
        setAngleVisors(33);
        setDistanciaVisors(350);
        setVelocitatLineal(4);
        setVelocitatAngular(5);
        espera = 0;
        temps = 0;
        impacte_rebut = 0;
        passivitat_menjar = 0;
        passivitatanterior = 0;
    }

    @Override
    public void avaluaComportament() {
        temps++;
        estat = estatCombat();
        enemic = false;
        modificarenergia((long) estat.fuel);
        hyper(estat);
        setAngleVisors(33);

        if (espera > 0) {
            if (DEBUG) {
                System.out.println("Espera " + espera);
            }
            espera--;
        } else {
            atura();
            if (estat.enCollisio) // situació de nau bloquejada
            {
                resoldreColisio(); // tria un cami que te sortida
            } else {
                endavant();
                if (estat.veigEnemic) {
                    atacar();
                } else if (estat.nbonificacions > 0 && !estat.enCollisio) {                    
                    trobarBonificacio();
                } else {
                    esquivarObstacle();
                    System.out.print("obstacle");
                }
            }
        }
    }

    void esquivarObstacle() {
        // Miram els visors per detectar els obstacles
        int sensor = 0;

        if (estat.objecteVisor[DRETA] == PARET && estat.distanciaVisors[DRETA] < DISTANCIA_MINIMA) {
            sensor += 1;
        }
        if (estat.objecteVisor[CENTRAL] == PARET && estat.distanciaVisors[CENTRAL] < DISTANCIA_MINIMA) {
            sensor += 2;
        }
        if (estat.objecteVisor[ESQUERRA] == PARET && estat.distanciaVisors[ESQUERRA] < DISTANCIA_MINIMA) {
            sensor += 4;
        }
        if (DEBUG) {
            System.out.println("Sensor: " + sensor);
        }

        switch (sensor) {
            case 0:
                endavant();
                break;
            case 1:
                esquerra();// dreta bloquejada
            case 3:
                esquerra();
                break;
            case 4: 
                 dreta();// esquerra bloquejada
            case 6:
                dreta();
                break;
            case 5: // centre lliure
                endavant();
                break;
            case 2: 
                enrere();// paret devant
            case 7:  // si estic molt aprop, torna enrere
                double distancia;
                distancia = minimaDistanciaVisors();

                if (distancia < 15) {                    
                    enrere();
                    espera = 8;                    
                } else{ // gira aleatòriament a la dreta o a l'esquerra
                    if (estat.distanciaVisors[ESQUERRA] > estat.distanciaVisors[DRETA]){
                        gira(60);
                    }else{
                        gira(-60); 
                    }   
                }
                break;
        }
    }

    void trobarBonificacio() {
        Punt punt_mes_proxim = new Punt(puntMesProxim().x, puntMesProxim().y);
        setAngleVisors(33);
        if (!estat.enCollisio) {
            System.out.println("Recurs localitzat");           
            if (distancia(estat.posicio, punt_mes_proxim) < 85 && cont == 0) {                
                if (estat.objecteVisor[CENTRAL] == PARET && estat.distanciaVisors[CENTRAL] < 50 ){
                    resoldreColisio();
                }else{
                   mira(punt_mes_proxim.x, punt_mes_proxim.y);
                   if (estat.estatVisor[CENTRAL] && estat.objecteVisor[1] != 0) {
                   } else {
                    cont = 5;
                    setAngleVisors(33);
                   }
                }                                    
            }
            if (cont > 0 && !estat.enCollisio) {
                System.out.println(cont);
                setAngleVisors(10);
                endavant();
                cont--;                
            }else{
                esquivarObstacle();
            }
        } else {
            resoldreColisio();
        }
    }

    Punt puntMesProxim() {
        int bonificacio = 0;
        if (estat.nbonificacions > 0) {
            for (int i = 1; i < estat.bonificacions.length; i++) {
                if (!(estat.bonificacions[i].x == 0 && estat.bonificacions[i].y == 0)) {
                    if (distancia(estat.posicio, estat.bonificacions[i]) < distancia(estat.posicio, estat.bonificacions[bonificacio])) {
                        bonificacio = i;
                    }
                }
            }
        }
        return estatCombat().bonificacions[bonificacio];
    }

    int distancia(Punt punt1, Punt punt2) {
        return (int) Math.hypot(punt2.x - punt1.x, punt2.y - punt1.y);
    }

   

    boolean hiHaParedDavant(int dist) {

        if (estat.objecteVisor[ESQUERRA] == PARET && estat.distanciaVisors[ESQUERRA] <= dist) {
            return true;
        }

        if (estat.objecteVisor[CENTRAL] == PARET && estat.distanciaVisors[CENTRAL] <= dist) {
            return true;
        }

        if (estat.objecteVisor[DRETA] == PARET && estat.distanciaVisors[DRETA] <= dist) {
            return true;
        }

        return false;
    }

    double minimaDistanciaVisors() {
        double minim;
            System.out.println("distancia");
        minim = Double.POSITIVE_INFINITY;
        if (estat.objecteVisor[ESQUERRA] == PARET) {
            minim = estat.distanciaVisors[ESQUERRA];
        }
        if (estat.objecteVisor[CENTRAL] == PARET && estat.distanciaVisors[CENTRAL] < minim) {
            minim = estat.distanciaVisors[CENTRAL];
        }
        if (estat.objecteVisor[DRETA] == PARET && estat.distanciaVisors[DRETA] < minim) {
            minim = estat.distanciaVisors[DRETA];
        }
        return minim;
    }

    void resoldreColisio() {
        if (estat.objecteVisor[CENTRAL] == NAU && estat.impactesRival < 5)// si veu la nau, dispara
        {
            dispara();   //bloqueig per nau, no giris dispara
        } else // hi ha un obstacle, gira i parteix
        {
            switch (cercaMillorCami(estat.distanciaVisors[0], estat.distanciaVisors[1], estat.distanciaVisors[2])) {//angles de gir permesos en cas de colisió
                case 0:
                    gira(angleGir(5, 60));
                case 1:
                    gira(-angleGir(5, 45));
                case 2:
                    gira(160);
            }
        }
    }

    int cercaMillorCami(double visor1, double visor2, double visor3) {
        int cami;
        if (visor1 > visor2) {
            cami = 0;
        } else {
            cami = 1;
        }
        if (cami == 0) {
            if (visor1 > visor3) {
                return 0;
            }
        } else if (cami == 1) {
            if (visor2 > visor3) {
                return 1;
            }
        }
        return 2;
    }

    int angleGir(int angle_min, int angle_max) {
        return (int) (Math.random() * (angle_max - angle_min + 1) + angle_min);
    }

    void atacar() {
        if (estat.sector == 2 || estat.sector == 3) {
            mira(estat.posicioEnemic.x, estat.posicioEnemic.y);
            if (estat.impactesRival < 5) {
                dispara();
            }
        } else if (estat.sector == 1) {
            dreta();
        } else {
            esquerra();
        }
    }

    public void hyper(Estat estat) {
            if ((estat.distanciaBalaEnemiga < 30) && estat.balaEnemigaDetectada) {
                hyperespai();
            } else if ((estat.enCollisio && (estat.passivitat > passivitatanterior) && (!estat.veigEnemic))) {
                if (estat.passivitat - passivitatanterior > 50) {
                    passivitatanterior = estat.passivitat;
                    hyperespai();
                }
            }        
    }

    public void modificarenergia(long a) {
        if (a < 1000) {
            setVelocitatLineal(1);
            setVelocitatAngular(1);
        }
        if (a > 1000) {
            setVelocitatLineal(3);
            setVelocitatAngular(4);
        }
        if (a > 5000) {
            setVelocitatLineal(5);
            setVelocitatAngular(4);
        }
        if (a > 8000) {
            setVelocitatLineal(6);
            setVelocitatAngular(7);
        }
        if (a > 10000) {
            setVelocitatLineal(6);
            setVelocitatAngular(9);
        }
    }

}
