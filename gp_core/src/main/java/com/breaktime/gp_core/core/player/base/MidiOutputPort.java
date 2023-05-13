package com.breaktime.gp_core.core.player.base;

public interface MidiOutputPort extends MidiDevice {
	
	public MidiSynthesizer getSynthesizer() throws MidiPlayerException;
	
}