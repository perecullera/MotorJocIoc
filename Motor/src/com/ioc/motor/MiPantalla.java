package com.ioc.motor;

import com.ioc.motor.Grafics.PixmapFormat;

public class MiPantalla extends Pantalla {
	
	Pixmap dibuix; 
	int x;
	int moviment = 1;

	public MiPantalla(Joc joc) {
		super(joc);
		//Carreguem un dibuix
		dibuix = joc.getGraphics().nouPixmap("data/dibuix.png",PixmapFormat.RGB565);
	}

	@Override
	//Actualitza l’estat del món/pantalla
	public void actualitza(float transcorregut) {
		//En aquest cas, simplement anem modificant la posició en què es mostra el dibuix
	  x+=moviment;
	  if (x > 200) 
		  moviment=-1;
	  else if (x<5)
		  moviment=1;
		
	}

	@Override
	public void mostra(float transcorregut) {
		//Esborrem la pantalla
		  joc.getGraphics().clear(0);
		  //Dibuixem
		  joc.getGraphics().dibuixaPixmap(dibuix, x, 0, 0, 0,dibuix.getAmple(), dibuix.getAlt());

		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void llibera() {
		//Alliberem la memòria del dibuix
		  dibuix.llibera();
		
	}

}
