package com.breaktime.gp_core.core.player.impl.sequencer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.breaktime.gp_core.core.player.base.MidiPlayerException;
import com.breaktime.gp_core.core.player.base.MidiSequencer;
import com.breaktime.gp_core.core.player.base.MidiSequencerProvider;
import com.breaktime.gp_core.core.util.TGContext;

public class MidiSequencerProviderImpl implements MidiSequencerProvider {
	
	private TGContext context;
	private List<MidiSequencer> sequencers;
	
	public MidiSequencerProviderImpl(TGContext context){
		this.context = context;
	}
	
	public List<MidiSequencer> listSequencers() throws MidiPlayerException {
		if(this.sequencers == null){
			this.sequencers = new ArrayList<MidiSequencer>();
			this.sequencers.add(new MidiSequencerImpl(this.context));
		}
		return this.sequencers;
	}
	
	public void closeAll() throws MidiPlayerException {
		Iterator<MidiSequencer> it = listSequencers().iterator();
		while(it.hasNext()){
			MidiSequencer sequencer = (MidiSequencer)it.next();
			sequencer.close();
		}
	}
}
