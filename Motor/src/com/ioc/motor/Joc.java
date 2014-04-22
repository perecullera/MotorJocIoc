package com.ioc.motor;

public interface Joc {
	//Per obtenir els diferents mòduls
	public Input getInput(); 
	public FileIO getFileIO(); 
	public Grafics getGraphics(); 
	public Audio getAudio();
	//Selecciona la pantalla
	public void setPantalla(Pantalla pantalla);
	//Obté la pantalla actual Autor desconocido 
	public Pantalla getPantallaActual();
	//Obté la pantalla inicial
	public Pantalla getPantallaInicial();
}
