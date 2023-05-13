package com.breaktime.gp_core.core.player.base;

public interface MidiDevice {
	
	public String getKey();
	
	public String getName();
	
	public void open() throws MidiPlayerException;
	
	public void close() throws MidiPlayerException;
	
	public void check() throws MidiPlayerException;
}
