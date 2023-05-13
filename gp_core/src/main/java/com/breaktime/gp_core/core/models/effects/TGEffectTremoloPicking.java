package com.breaktime.gp_core.core.models.effects;

import com.breaktime.gp_core.core.factory.TGFactory;
import com.breaktime.gp_core.core.models.TGDuration;

public abstract class TGEffectTremoloPicking {
	
	private TGDuration duration;
	
	public TGEffectTremoloPicking(TGFactory factory) {
		this.duration = factory.newDuration();
	}
	
	public TGDuration getDuration() {
		return this.duration;
	}
	
	public void setDuration(TGDuration duration) {
		this.duration = duration;
	}
	
	public TGEffectTremoloPicking clone(TGFactory factory){
		TGEffectTremoloPicking effect = factory.newEffectTremoloPicking();
		effect.getDuration().setValue(getDuration().getValue());
		effect.getDuration().setDotted(getDuration().isDotted());
		effect.getDuration().setDoubleDotted(getDuration().isDoubleDotted());
		effect.getDuration().getDivision().setEnters(getDuration().getDivision().getEnters());
		effect.getDuration().getDivision().setTimes(getDuration().getDivision().getTimes());
		return effect;
	}
	
}
