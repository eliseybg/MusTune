package com.breaktime.gp_core.core.document;

import com.breaktime.gp_core.core.graphics.control.TGFactoryImpl;
import com.breaktime.gp_core.core.managers.TGSongManager;
import com.breaktime.gp_core.core.models.TGSong;
import com.breaktime.gp_core.core.util.TGContext;
import com.breaktime.gp_core.core.util.singleton.TGSingletonFactory;
import com.breaktime.gp_core.core.util.singleton.TGSingletonUtil;

public class TGDocumentManager {
	
	private TGSongManager songManager;
	private TGSong song;
	
	private TGDocumentManager() {
		this.songManager = new TGSongManager(new TGFactoryImpl());
		this.song = this.songManager.newSong();
	}
	
	public TGSongManager getSongManager() {
		return songManager;
	}

	public TGSong getSong() {
		return song;
	}
	
	public void setSong(TGSong song) {
		if( song != null ){
			this.song = song;
			this.songManager.autoCompleteSilences(this.song);
		}
	}

	public static TGDocumentManager getInstance(TGContext context) {
		return TGSingletonUtil.getInstance(context, TGDocumentManager.class.getName(), new TGSingletonFactory<TGDocumentManager>() {
			public TGDocumentManager createInstance(TGContext context) {
				return new TGDocumentManager();
			}
		});
	}
}
