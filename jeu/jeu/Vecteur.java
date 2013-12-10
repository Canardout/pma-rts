package jeu;

public class Vecteur {
	public float x, y;
	
	public Vecteur(float x, float y){
		this.x = x;
		this.y = y;
	}
	
	public float norme (){
		return (float) Math.sqrt(x*x + y*y);
	}
	
	public Vecteur addition (Vecteur v){
		return new Vecteur(x + v.x, y + v.y);
	}
	
	public void additionEgale (Vecteur v){
		x += v.x;
		y += v.y;
	}
	
	public Vecteur soustraction (Vecteur v){
		return new Vecteur(x - v.x, y - v.y);
	}

	public void soustractionEgale (Vecteur v){
		x -= v.x;
		y -= v.y;
	}
	
	public Vecteur multiplication (float m){
		return new Vecteur(x * m, y * m);
	}
	
	//multiplcationEgale
	//division
	

	
	public Vecteur getDirecteur (){
		float n = this.norme();
		return new Vecteur(x / n, y / n);
	}
}
/*
deplacement = (arrivee - unite).getDirecteur * vitesse;
unite += ()
*/