package com.ioc.motor.implementacio;

import java.io.IOException;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;

import com.ioc.motor.Musica;

public class MotorMusica implements Musica, OnCompletionListener {
	
	//Media player que farem servir per reproduir la música
	MediaPlayer mediaPlayer;
	boolean llest = false;
	public static boolean musicaActivada = true;
	
	//Constructor
	public MotorMusica(AssetFileDescriptor assetDescriptor) {
	mediaPlayer = new MediaPlayer();
	try {
		//Preparem el media player 
		mediaPlayer.setDataSource(assetDescriptor.getFileDescriptor(),assetDescriptor.getStartOffset(),assetDescriptor.getLength());
		mediaPlayer.prepare();
		llest = true; 
		mediaPlayer.setOnCompletionListener(this);
	} catch (Exception e) {
		throw new RuntimeException("No es pot carregar el fitxer de música"); 
		}
	}

	@Override
	//Per onCompletionLister
	public void onCompletion(MediaPlayer mp) {
		synchronized (this) {
			llest = false;
			}
	}
	@Override
	//Reprodueix el mediaplayer 
	public void play()
	{
		if(!musicaActivada)
			return;
		if (mediaPlayer.isPlaying())
			return;
		try {
			synchronized (this) {
			//Hem de mirar abans si el mediaplayer està llest per reproduir
			if (!llest) 
				mediaPlayer.prepare(); 
				mediaPlayer.start();
			}
		} catch (IllegalStateException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		} 
	}			

	@Override
	//Atura el reproductor
	public void stop() {
		mediaPlayer.stop();
		synchronized (this) 
		{
			llest = false; 
		}

	}

	@Override
	//Pausa el media player
	public void pausa() {
		if (mediaPlayer.isPlaying())
			mediaPlayer.pause();

	}

	@Override
	//Determinem si funciona en mode bucle o no
	public void setBucle(boolean bucle) {
		  mediaPlayer.setLooping(bucle);

	}

	@Override
	//Especifiquem el volum
	public void setVolum(float volum) {
		//El mateix volum a l’altaveu esquerre i dret
	    mediaPlayer.setVolume(volum, volum);

	}

	@Override
	//Retorna si està reproduint
	public boolean isPlaying() {
		return mediaPlayer.isPlaying();
	}

	@Override
	//Retorna si està aturat
	public boolean isStopped() {
		return !llest;
	}

	@Override
	public boolean isBucle() {
		return mediaPlayer.isLooping();
	}

	@Override
	public void llibera() {
		//Alliberem la memòria del media player
		//Si encara està reproduint, l’aturem 
		if (mediaPlayer.isPlaying())
			mediaPlayer.stop();
			//i alliberem
			mediaPlayer.release();

	}

}
