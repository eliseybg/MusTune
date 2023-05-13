package com.breaktime.gp_core.core.player.base;

import java.util.List;

public interface MidiOutputPortProvider {
	
	public List<MidiOutputPort> listPorts() throws MidiPlayerException;
	
	public void closeAll() throws MidiPlayerException;
	
}
