package com.ioc.motor.implementacio;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Point;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.view.Window;
import android.view.WindowManager;

import com.ioc.motor.Audio;
import com.ioc.motor.FileIO;
import com.ioc.motor.Grafics;
import com.ioc.motor.Input;
import com.ioc.motor.Joc;
import com.ioc.motor.Pantalla;

public class MotorJoc extends Activity implements Joc {
	
	//El joc tindrà una instància de cadascun dels mòduls del joc
	MotorFilSurfaceView renderView;
	Grafics grafics;
	Audio audio;
	Input input;
	FileIO fileIO;
	Pantalla pantalla;
	WakeLock wakeLock;
	
	@TargetApi(13)
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//Fem que el joc s’executi en pantalla completa
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		//Anem a crear el frameBuffer, i per això necessitem saber si s’executa en mode apaïsat o vertical
		boolean apaisat = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
		//Els gràfics estan dissenyats a ×320480. Si canviem la configuració de la pantalla
		//s’han de refer tots els recursos de la pantalla 
		int ampleFrameBuffer = apaisat ? 480 : 320;
		int altFrameBuffer = apaisat ? 320 : 480;
		//Creem el frame buffer on es dibuixaran els gràfics primerament
		Bitmap frameBuffer = Bitmap.createBitmap(ampleFrameBuffer,altFrameBuffer,Config.RGB_565);
		//Obtenim les dimensions (en píxels) de la pantalla 
		Point punt = new Point();
		//Obtenim la mida de la pantalla
		//En diferents versions d’Android s’han de fer servir diferents mètodes 
		if(VERSION.SDK_INT > Build.VERSION_CODES.HONEYCOMB_MR2)
		{
			getWindowManager().getDefaultDisplay().getSize(punt); }
		else
		{
			punt.x = getWindowManager().getDefaultDisplay().getWidth();
			punt.y = getWindowManager().getDefaultDisplay().getHeight(); 
		}
		//Creem els factors d’escalat a partir de la mida del frameBuffer i les dimensions de la pantalla
		float factorEscalatX = (float) ampleFrameBuffer / punt.x;
		float factorEscalatY = (float) altFrameBuffer / punt.y;
		
		//Instanciem els diferents mòduls del joc
		renderView = new MotorFilSurfaceView(this, frameBuffer);
		grafics = new MotorGrafics(getAssets(), frameBuffer);
		fileIO = new MotorFileIO(getAssets());
		audio = new MotorAudio(this);
		input = new MotorInput(this, renderView, factorEscalatX, factorEscalatY);
		
		//Obtenim la pantalla inicial del joc. Es sobrecarregarà quan aquesta classe sigui heretada
		pantalla = getPantallaInicial();
	
		//Li diem a Android qui contindrà el contingut a mostrar per pantalla 
		setContentView(renderView);
		//Bloquegem la pantalla perquè no entri en mode de baix consum 
		PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
		wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "GLGame"); 
	}
	
	@Override
	public void onPause() {
		super.onPause();
		//Alliberem el lock de pantalla
		wakeLock.release();
		//posem en pausa els fils d’execució
		renderView.pause();
		pantalla.pause();
		//Si s’està acabant l’aplicació, alliberem memòria 
		if (isFinishing())
			pantalla.llibera();
	}
	
	@Override
	public void onResume() {
		super.onResume();
		//Obtenim el lock de pantalla
		wakeLock.acquire();
		//Reprenem l’execució
		pantalla.resume();
		renderView.resume();
	}
	
	@Override
	public Input getInput() {
		return input;
	}

	@Override
	public FileIO getFileIO() {
		return fileIO;
	}

	@Override
	public Grafics getGraphics() {
		return grafics;
	}

	@Override
	public Audio getAudio() {
		return audio;
	}

	@Override
	public void setPantalla(Pantalla pantalla) {
		if (pantalla == null)
			throw new IllegalArgumentException("Pantalla null!!!");
		//Aturem la pantalla actual...
		this.pantalla.pause();
		//...i alliberem memòria
		this.pantalla.llibera();
		//Iniciem la pantalla que obtenim com argument
		pantalla.resume();
		//actualitzem el seu temps
		pantalla.actualitza(0);
		//i la fem pantalla actual
		this.pantalla = pantalla;
	}

	@Override
	public Pantalla getPantallaActual() {
		return pantalla;
	}

	@Override
	public Pantalla getPantallaInicial() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
