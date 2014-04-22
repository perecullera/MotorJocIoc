package com.ioc.motor;

public abstract class Pantalla {
	//Instància de joc per poder accedir als altres mòduls i
	//poder canviar de pantalla 
	protected final Joc joc;
	//Constructor
	public Pantalla(Joc joc) {
	this.joc = joc;
	}
	//Actualitza l’estat de la pantalla
	public abstract void actualitza(float transcorregut);
	//Mostralapantalla
	public abstract void mostra(float transcorregut);
	public abstract void pause(); 
	public abstract void resume();
	//Allibera memòria
	public abstract void llibera();
}
