package com.breaktime.gp_core.core.gm.port;

import com.breaktime.gp_core.core.gm.GMChannelRoute;
import com.breaktime.gp_core.core.gm.GMChannelRouter;
import com.breaktime.gp_core.core.player.base.MidiPlayerException;
import com.breaktime.gp_core.core.util.TGException;

public class GMSynthesizerRouter extends GMChannelRouter {
	
	private GMSynthesizer synth;
	
	public GMSynthesizerRouter(GMSynthesizer synth){
		this.synth = synth;
	}
	
	public void configureRoutes(GMChannelRoute route, boolean percussionChannel) {
		try {
			super.configureRoutes(route, percussionChannel);
			
			for(GMChannel gmChannel : this.synth.getChannels()) {
				gmChannel.sendProgramUpdated();
			}
		} catch (MidiPlayerException e) {
			throw new TGException(e);
		}
	}
}
