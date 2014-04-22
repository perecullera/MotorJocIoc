package com.ioc.motor.implementacio;

import android.media.SoundPool;

import com.ioc.motor.So;

public class MotorSo implements So {
	int idSo;
	SoundPool soundPool;
	public static boolean soActivat = true;
	
	//Constructor
	public MotorSo(SoundPool soundPool, int idSo) {
		this.idSo = idSo;
		this.soundPool = soundPool; 
	}

	@Override
	public void play(float volum) {
		//Reproduïm el so
		if(soActivat)
		soundPool.play(idSo, volum, volum, 0, 0, 1);


	}

	@Override
	public void llibera() {
		//Alliberem la memòria reservada 
		soundPool.unload(idSo);
	}

}
