package com.ioc.motor.implementacio;

import java.util.List;

import com.ioc.motor.Input.EventTouch;

import android.view.View.OnTouchListener;

public interface TouchHandler extends OnTouchListener {
	//Si està premuda la pantalla
	public boolean pantallaPremuda(int punter);
	//Coordenades on s’ha premut public 
	int getTouchX(int punter);
	public int getTouchY(int punter);
	//Obtenir els events de touch
	public List<EventTouch> getEventsTouch();
}
