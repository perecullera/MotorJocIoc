package com.ioc.motor;

public interface Grafics {
	//Formats de píxel
	public static enum PixmapFormat
	{
	  ARGB8888, ARGB4444, RGB565
	}
	//Introdueix el píxel en les coordenades x, y al color especificat 
	public void dibuixaPixel(int x, int y, int color);
	//Buida la pantalla amb el color donat (en format ARGB8888) public void clear(int color);
	//Dibuixa una línia entre els dos punts del color donat
	public void dibuixaLinia(int x, int y, int x2, int y2, int color);
	//Dibuixa un rectangle amb x,y com posició de
	public void dibuixaRectangle(int x, int y, int ample, int alt, int color);
	//Carrega un mapa de píxels
	public Pixmap nouPixmap(String nomFitxer, PixmapFormat format);
	//Dibuixa un pixmap complet per pantalla
	public void dibuixaPixmap(Pixmap pixmap, int x, int y);
	//Dibuixa una porció d’un Pixmap en la posició x,y del framebuffer. srcX i srcY
	//fan referència a la posició del rectangle del Pixmap que es vol dibuixar 
	public void dibuixaPixmap(Pixmap pixmap, int x, int y, int origenX, int origenY, int origenAmple, int origenAlt);
	//Obtenir ample i altdelframebuffer
	public int getAmple();
	public int getAlt();
	public void clear(int i);
}
