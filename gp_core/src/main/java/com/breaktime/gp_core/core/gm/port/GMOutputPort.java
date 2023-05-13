package com.breaktime.gp_core.core.gm.port;

import com.breaktime.gp_core.core.player.base.MidiOutputPort;
import com.breaktime.gp_core.core.player.base.MidiPlayerException;
import com.breaktime.gp_core.core.player.base.MidiSynthesizer;

public abstract class GMOutputPort implements MidiOutputPort {
	
	private GMSynthesizer midiSynthesizer;
	
	public GMOutputPort(){
		this.midiSynthesizer = new GMSynthesizer(this);
	}
	
	public abstract GMReceiver getReceiver() throws MidiPlayerException;
	
	public MidiSynthesizer getSynthesizer() throws MidiPlayerException{
		return this.midiSynthesizer;
	}
}
