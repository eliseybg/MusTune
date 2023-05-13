package com.breaktime.gp_core.core.player.base;

public interface MidiSynthesizer {
	
	public MidiChannel openChannel(int channelId) throws MidiPlayerException;
	
	public void closeChannel(MidiChannel midiChannel) throws MidiPlayerException;
	
	public boolean isChannelOpen(MidiChannel midiChannel) throws MidiPlayerException;
	
	public boolean isBusy() throws MidiPlayerException;
}
