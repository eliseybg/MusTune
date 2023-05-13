package com.breaktime.gp_core.core.player.plugin;

import com.breaktime.gp_core.core.player.base.MidiPlayer;
import com.breaktime.gp_core.core.player.base.MidiSequencerProvider;
import com.breaktime.gp_core.core.util.TGContext;
import com.breaktime.gp_core.core.util.plugin.TGPlugin;
import com.breaktime.gp_core.core.util.plugin.TGPluginException;

public abstract class TGMidiSequencerProviderPlugin implements TGPlugin {
	
	private MidiSequencerProvider provider;
	
	protected abstract MidiSequencerProvider createProvider(TGContext context) throws TGPluginException;
	
	public void connect(TGContext context) throws TGPluginException {
		try {
			if( this.provider == null ) {
				this.provider = createProvider(context);
				
				MidiPlayer.getInstance(context).addSequencerProvider(this.provider);
			}
		} catch (Throwable throwable) {
			throw new TGPluginException(throwable.getMessage(),throwable);
		}
	}
	
	public void disconnect(TGContext context) throws TGPluginException {
		try {
			if( this.provider != null ) {
				MidiPlayer.getInstance(context).removeSequencerProvider(this.provider);
				
				this.provider.closeAll();
				this.provider = null;
			}
		} catch (Throwable throwable) {
			throw new TGPluginException(throwable.getMessage(),throwable);
		}
	}
}
