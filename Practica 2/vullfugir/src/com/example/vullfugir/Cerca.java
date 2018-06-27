package com.example.vullfugir;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;


public class Cerca {

		ArrayList<Node> camitrobat;
		private ArrayList<Node> llistamogut;
        private ArrayList<Node> llistapedres;
        private ArrayList<Node> llistaprueba2;
        ArrayList<Punt> llistaprueba;
        
        
        ArrayList<ArrayList<Punt>> llistacaminos;
        
        
        private GraellaJoc graella;   
        Punt punt_inicial;
        int x=5;
        int j=0;
        Punt puntanterior;
        ArrayList<Integer> peso;
        static int aaa = 5;
       static int bbb = 5;
        
        
        Cerca(GraellaJoc graella){
    		this.graella = graella;
            llistamogut = new ArrayList<Node>();
            llistapedres = new ArrayList<Node>();
            llistaprueba = new ArrayList<Punt>();
            peso = new ArrayList<Integer>();
            
            llistacaminos = new ArrayList<ArrayList<Punt>>();
            this.punt_inicial = new Punt(5,5);
            this.puntanterior = new Punt(5,5);
        }

        public Node calculaCasella(Punt inici) 
        {
        	System.out.print("\n"+"Nodo inici:"+inici.x+" "+inici.y);
        	System.out.print("\n"+"Nodo anterior:"+this.aaa+" "+this.bbb);
        	peso.clear();
        	
        	ArrayList<Node> llistasolucionsparcials;
        	llistasolucionsparcials = new ArrayList<Node>();
        	llistasolucionsparcials.clear();
        	
        	boolean trobat2=false;
        	int trobatcamins2=0;
        	// retorna el proper node on s'ha de moure o null si no hi ha cap possibilitat de moviment
        	// inici és la casella inicial on està el bitxo

    		Node tmpNode;     		
            // Mira si hi ha una pedra on vull anar
    		
            if (graella.noPucMoure(inici)) return null;
            
            Punt casellaPitjada = graella.casellaPitjada();
            
            //tmpNode = new Node(casellaPitjada.x, casellaPitjada.y);
            //tmpNode=Buscarsolucion(inici);
            
            
            
            llistamogut.add(Convertirpuntanode(inici));
            llistapedres.add(Convertirpuntanode(casellaPitjada));
            
           //// System.out.println("INICI:"+inici.x+" "+inici.y);
            ////System.out.println("PIEDRA:"+casellaPitjada.x+" "+casellaPitjada.y);
            //printarray (calculardescendentes(inici));
            
            llistaprueba2=calculardescendentes(inici);
            if (llistaprueba2.size()==0) {
            	return null;
            }
            
            
            for(int i = 0; i < llistaprueba2.size(); i++) {
            		trobat2=Buscarsolucion(inici,Convertirnodeapunt(llistaprueba2.get(i)));
            		////System.out.print("\n"+"e:"+trobat2);
            		//System.out.print(" Nodo ir:"+llistaprueba2.get(i).x+" "+llistaprueba2.get(i).y);
            		
            		if (trobat2 ){
            			trobatcamins2++;
            			//System.out.println("Nodo ir:"+llistaprueba2.get(i).x+" "+llistaprueba2.get(i).y);
            			llistasolucionsparcials.add(llistaprueba2.get(i));
            			//System.out.print("\n"+"FUNCIONA:"+peso.get(i));
            			
            			//tmpNode =llistaprueba2.get(i);
            			//return llistaprueba2.get(i);
            		}
            
           
            }
            
            if (llistasolucionsparcials.size()==peso.size()){
            	
            for(int i = 0; i < llistasolucionsparcials.size(); i++) {
            	System.out.print("___5000");
            	
            
            if (llistasolucionsparcials.get(i).getX()==this.aaa && llistasolucionsparcials.get(i).getY()==this.bbb){
            	
            	
            	int jjp= peso.get(i)+5000;
            	peso.set(i,jjp);
            	
            }
            
            }
            }
            
            System.out.println("\n Totalsoluciones"+llistasolucionsparcials.size());
            for(int i = 0; i < llistasolucionsparcials.size(); i++) {
            	System.out.print("\n"+"Nodo ir:"+llistasolucionsparcials.get(i).x+" "+llistasolucionsparcials.get(i).y);
            	System.out.print(" Peso:"+peso.get(i));
            	//if (llistacaminos.size()>1){
            	System.out.print(" largo:"+llistacaminos.get(i).size());
            	//}
            }
            
            if (trobatcamins2==0){
            	System.out.println("---------------ENCERRADO----------------");
            	//return llistaprueba2.get(0);
            	//graella.camiSolucio.clear();
            	return null;
            	
            	
            }
            
            
            if (trobatcamins2==1){
            	//graella.camiSolucio.clear();
            	this.puntanterior=Convertirnodeapunt(llistasolucionsparcials.get(0));
            	return llistasolucionsparcials.get(0);
            	
            }
            
            if (trobatcamins2>1){
            	int wmenorpeso=0;
            	
            	for(int i = 1; i < peso.size(); i++) {
                    if (peso.get(wmenorpeso)>peso.get(i)){
                    	wmenorpeso=i;
                    }
                    
                   /* if (peso.get(wmenorpeso)==peso.get(i)){
                        if (llistacaminos.size()>1){
                    		if (llistacaminos.get(wmenorpeso).size()>=llistacaminos.get(i).size()){
                    		wmenorpeso=i;
                           }
                    	
                    }}
            	*/
            	
                    
                 }
            	
            	//for(int i = 1; i < llistacaminos.size(); i++) {
                //    if (llistacaminos.get(wmenorpeso).size()>=llistacaminos.get(i).size()){
                //    	wmenorpeso=i;
                    	
                    	
                 //   }
                    
                // }
            	graella.camiSolucio.clear();
            	graella.camiSolucio.addAll(llistacaminos.get(wmenorpeso));
            	
            	
            	System.out.print("cambiarnodeanterior");
            	this.aaa=inici.x;
            	this.bbb=inici.y;
            	//this.aaa=llistasolucionsparcials.get(wmenorpeso).x;
            	//this.bbb=llistasolucionsparcials.get(wmenorpeso).y;
            	return llistasolucionsparcials.get(wmenorpeso);
            	
            }
            
            
            
            
            
            
            
            
            System.out.println("---------------nosolution----------------");
            return llistasolucionsparcials.get(0);
            
            //return new Node(5,6);
            
            //x++;
            
            //trobat2=backtraking(Convertirpuntanode(inici));
            //System.out.println("BOOlean:"+trobat2);
            
            
            
            
            //tmpNode = new Node(5,6);
            //return tmpNode;
        }

		private Node Convertirpuntanode(Punt inici) {
			Node a; 
			a = new Node(inici.x,inici.y);
			return a;
			
		}
		private Punt Convertirnodeapunt(Node inici) {
			Punt a; 
			a = new Punt(inici.getX(),inici.getY());
			return a;
			
		}
		
		

		public boolean Buscarsolucion(Punt inici,Punt ir) {
			 ArrayList<Punt> originallista;
			 originallista = new ArrayList<Punt>();
			 ArrayList<Punt> listanueva;
			 listanueva = new ArrayList<Punt>();
			 //ArrayList<Punt> listanueva2;
			// listanueva2 = new ArrayList<Punt>();
			
			boolean trobat3=false;
			//ir.xx=inici.x;
			//ir.yy=inici.y;
			//llistaprueba.add(inici);
			ir.raiz=1;
			originallista.add(ir);
			Punt pp;
			int i; //minimo de iteraciones
			i=9800;
			int j=0; //puntero donde hemos comprobado si hay algun solution
			int z=1; //final lista
			int w=0; //puntero donde hemos hecho los descendentes
			int d=0;
			
			if (graella.isASolution(ir.x, ir.y)){
				peso.add(0);
				llistacaminos.add(originallista);
				return true;
			}
			
			
			while(i!=0) {
				d++;
				//añadir descendentes
				
				
				
				
				
				////arrayList1.remove(arrayList2);
				
				
				if (w==originallista.size()){
					return false;
				}
				
				listanueva= calculardescendentespunt(originallista.get(w));
				//listanueva.remove(originallista);
				//////originallista.addAll(listanueva);
				//z=originallista.size();
				
				
				for(int q = 0; q < listanueva.size(); q++) {
					
					
					
					
					if   (originallista.contains( listanueva.get(q))){
					
					
					} else {
						pp=listanueva.get(q);
						pp.xx=originallista.get(w).x;
						pp.yy=originallista.get(w).y;
						pp.puntprevi=originallista.get(w);
						originallista.add(pp);
						//z++;
					}
				}
				
				w++;
				//originallista.addAll(listanueva2);
				
				z=originallista.size();
				
				while(j<z){
				//comprobar si hay nodo final;
					originallista.get(j);
				
				if (graella.isASolution(originallista.get(j).x, originallista.get(j).y) ){
					////System.out.println("*********SOL********"+d+"***********************");
					//ir.peso=i;
					peso.add(d);
					
					
					
					llistacaminos.add(calcularcaminoverdadero(originallista,originallista.get(j) ));
					//i=0; 
				
					return true;
					
				}
				j++;
				}
			
				
				
			i--;	
			}
			
			
			return trobat3;
		}
		
		
		private ArrayList<Punt> calcularcaminoverdadero (ArrayList<Punt> a, Punt b){
			ArrayList<Punt> d=new ArrayList<Punt> ();
			
			Punt c;
			c=b;
			d.add(c);
			while (c.raiz==0) {
				
				//c=a.get(buscarindex(a,c));
				c=c.puntprevi;
				d.add(c);
				
			}
			
			
			
			
			return d;
		}
		
		
		private int buscarindex(ArrayList<Punt> a, Punt c) {
			for(int q = 0; q < a.size(); q++) {
			if (c.x==a.get(q).x && c.y==a.get(q).y){
				return q;
				
			}
			}
			return 0;
		}
		
		
		
		
		
		
		
		
		
		private ArrayList<Node> calculardescendentes (Punt inici) {
			Node b; 
			b=Convertirpuntanode(inici);
			
			ArrayList<Node> llista2;
			llista2 = new ArrayList<Node>();
			
			ArrayList<Node> llistatemporal;
			llistatemporal = new ArrayList<Node>();
			llistatemporal= b.veinats();
			
			//for(int i = 6; i < 6; i = i+1) {
		         
		    //  }
			
			//Iterator<Node> iterator = llistatemporal.iterator();
			//while (iterator.hasNext()){
			for(int i = 0; i < llistatemporal.size(); i++) {
				//if (graella.hiHaObstacle(Convertirnodeapunt(iterator.next())) ){
				//	System.out.println("BORRANDO:"+iterator.next().x+" "+iterator.next().y);
				//	iterator.remove();
					
					
				//}
				
				if (!graella.hiHaObstacle(Convertirnodeapunt(llistatemporal.get(i))) ){
				//System.out.println("Descendentes:"+llistatemporal.get(i).x+" "+llistatemporal.get(i).y);
				llista2.add(llistatemporal.get(i));
					
					
				}
			//}
			}
			return llista2;
			
		}
		
		private ArrayList<Punt> calculardescendentespunt (Punt inici) {
			ArrayList<Punt> llistapunt;
			llistapunt = new ArrayList<Punt>();
			ArrayList<Node> llista2;
			llista2 = new ArrayList<Node>();
			
			llista2 = calculardescendentes(inici);
			
			for(int i = 0; i < llista2.size(); i++) {
				llistapunt.add(new Punt(llista2.get(i).x, llista2.get(i).y));
			
			}
			
			return llistapunt;
		}
		
		
		
		
		
		
		
		
		
		
		private void printarray (ArrayList<Node> llista) {
			
			System.out.print("Lista size: "+llista.size()+"\n");
			for(int i = 0; i < llista.size(); i++) {   
			    System.out.print("Lista posicion "+(i+1)+" :"+llista.get(i).x+" "+llista.get(i).y+"\n");
			    
			    //System.out.print("nodoanterior:"+llista.get(i).puntprevi.x+","+llista.get(i).puntprevi.y+"\n");
			}
			
			
		}
		
		
		private boolean backtraking (Node n){
			ArrayList<Node> llista3;
			llista3 = new ArrayList<Node>();
			boolean trobat;
			if (graella.isASolution(n.getX(), n.getY())) {
				camitrobat.add(n);
				return true;
				
			}
			
			j++;
			System.out.println("fail"+j);
			//llista3= calculardescendentes(Convertirnodeapunt(n));
			llista3=n.veinats();
			
			for(int i = 0; i < llista3.size(); i++) {
				trobat=backtraking(llista3.get(i));
				j++;j++;
				System.out.println("fail"+j);
				if (trobat){
					j++;j++;j++;
					System.out.println("fail"+j);
					camitrobat.add(n);
					return true;
					
				}
			}
			
			
			
			
			
		return false;
		}
		
		
        
}       
        