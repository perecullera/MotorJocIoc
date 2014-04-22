package com.ioc.motor.implementacio;

import java.util.List;

import android.content.Context;
import android.view.View;

import com.ioc.motor.Input;

public class MotorInput implements Input {
	

	//Els tres handlers per cada tipus d’event d’entrada
	AccelerometreHandler handlerAccelerometre;
	TeclatHandler handlerTeclat;
	TouchHandler handlerTouch;
	
	//Constructor, inicialitzem els handlers
	public MotorInput(Context context, View view, float escalaX, float escalaY)
	{
		handlerAccelerometre = new AccelerometreHandler(context); 
		handlerTeclat = new TeclatHandler(view);
		handlerTouch = new SimpleTouch(view, escalaX, escalaY);
	}
	
	//Cridem els diferents handlers per implementar els mètodes de la interfície
	
	@Override
	public boolean teclaPremuda(int codiTecla) {
		return handlerTeclat.teclaPremuda(codiTecla);
	}

	@Override
	public boolean punterPremut(int punter) {
		return handlerTouch.pantallaPremuda(punter);
	}

	@Override
	public boolean pantallaPremuda() {
		return handlerTouch.pantallaPremuda(0);
	}

	@Override
	public int getTouchX(int punter) {
		return handlerTouch.getTouchX(punter);
	}

	@Override
	public int getTouchY(int punter) {
		return handlerTouch.getTouchY(punter);
	}

	@Override
	public float getAccelX() {
		return handlerAccelerometre.getAccelX();
	}

	@Override
	public float getAccelY() {
		return handlerAccelerometre.getAccelY();
	}

	@Override
	public float getAccelZ() {
		return handlerAccelerometre.getAccelZ();
	}

	@Override
	public List<EventTeclat> getEventsTeclat() {
		return handlerTeclat.getEventsTeclat();
	}

	@Override
	public List<EventTouch> getEventsTouch() {
		return handlerTouch.getEventsTouch();
	}

}
