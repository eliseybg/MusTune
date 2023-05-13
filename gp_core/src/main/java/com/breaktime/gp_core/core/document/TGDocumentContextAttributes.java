package com.breaktime.gp_core.core.document;

import com.breaktime.gp_core.core.managers.TGSongManager;
import com.breaktime.gp_core.core.models.*;

public final class TGDocumentContextAttributes {

	public static final String ATTRIBUTE_SONG_MANAGER = TGSongManager.class.getName();
	public static final String ATTRIBUTE_SONG = TGSong.class.getName();
	public static final String ATTRIBUTE_TRACK = TGTrack.class.getName();
	public static final String ATTRIBUTE_HEADER = TGMeasureHeader.class.getName();
	public static final String ATTRIBUTE_MEASURE = TGMeasure.class.getName();
	public static final String ATTRIBUTE_BEAT = TGBeat.class.getName();
	public static final String ATTRIBUTE_VOICE = TGVoice.class.getName();
	public static final String ATTRIBUTE_NOTE = TGNote.class.getName();
	public static final String ATTRIBUTE_STRING = TGString.class.getName();
	public static final String ATTRIBUTE_DURATION = TGDuration.class.getName();
	public static final String ATTRIBUTE_CHANNEL = TGChannel.class.getName();
	public static final String ATTRIBUTE_TEMPO = TGTempo.class.getName();
	public static final String ATTRIBUTE_TIME_SIGNATURE = TGTimeSignature.class.getName();
	public static final String ATTRIBUTE_LYRIC = TGLyric.class.getName();
	public static final String ATTRIBUTE_CHORD = TGChord.class.getName();
	public static final String ATTRIBUTE_MARKER = TGMarker.class.getName();
	
	public static final String ATTRIBUTE_VELOCITY = "velocity";
	public static final String ATTRIBUTE_POSITION = "position";
	public static final String ATTRIBUTE_FRET = "fret";
	public static final String ATTRIBUTE_VALUE = "value";
}
