package com.breaktime.gp_core.core.gm.port;

import com.breaktime.gp_core.core.player.base.MidiPlayerException;

public interface GMReceiver {
	
	public void sendAllNotesOff() throws MidiPlayerException;
	
	public void sendNoteOn(int channel, int key, int velocity) throws MidiPlayerException;
	
	public void sendNoteOff(int channel, int key, int velocity) throws MidiPlayerException;
	
	public void sendProgramChange(int channel, int value) throws MidiPlayerException;
	
	public void sendControlChange(int channel, int controller, int value) throws MidiPlayerException;
	
	public void sendPitchBend(int channel, int value) throws MidiPlayerException;
	
}
