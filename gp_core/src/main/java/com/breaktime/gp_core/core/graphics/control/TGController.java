package com.breaktime.gp_core.core.graphics.control;

import com.breaktime.gp_core.core.managers.TGSongManager;
import com.breaktime.gp_core.core.models.TGBeat;
import com.breaktime.gp_core.core.models.TGMeasure;
import com.breaktime.gp_core.core.models.TGMeasureHeader;
import com.breaktime.gp_core.core.models.TGSong;
import com.breaktime.gp_core.core.ui.resource.UIResourceFactory;

public interface TGController {
	
	public UIResourceFactory getResourceFactory();
	
	public TGResourceBuffer getResourceBuffer();
	
	public TGSongManager getSongManager();
	
	public TGSong getSong();
	
	public TGLayoutStyles getStyles();
	
	public int getTrackSelection();
	
	public boolean isRunning(TGBeat beat);
	
	public boolean isRunning(TGMeasure measure);
	
	public boolean isLoopSHeader(TGMeasureHeader measureHeader);
	
	public boolean isLoopEHeader(TGMeasureHeader measureHeader);
}
