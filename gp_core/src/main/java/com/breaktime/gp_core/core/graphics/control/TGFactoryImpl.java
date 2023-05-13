package com.breaktime.gp_core.core.graphics.control;

import com.breaktime.gp_core.core.factory.TGFactory;
import com.breaktime.gp_core.core.models.*;

public class TGFactoryImpl extends TGFactory {
	
	public TGFactoryImpl(){
		super();
	}
	
	public TGMeasureHeader newHeader(){
		return new TGMeasureHeaderImpl(this);
	}
	
	public TGTrack newTrack(){
		return new TGTrackImpl(this);
	}
	
	public TGMeasure newMeasure(TGMeasureHeader header){
		return new TGMeasureImpl(header);
	}
	
	public TGNote newNote(){
		return new TGNoteImpl(this);
	}
	
	public TGBeat newBeat(){
		return new TGBeatImpl(this);
	}
	
	public TGVoice newVoice(int index){
		return new TGVoiceImpl(this, index);
	}
	
	public TGLyric newLyric(){
		return new TGLyricImpl();
	}
	
	public TGChord newChord(int length){
		return new TGChordImpl(length);
	}
	
	public TGText newText(){
		return new TGTextImpl();
	}
}
