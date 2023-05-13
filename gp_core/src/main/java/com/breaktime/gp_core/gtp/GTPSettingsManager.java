package com.breaktime.gp_core.gtp;

import com.breaktime.gp_core.core.util.TGContext;
import com.breaktime.gp_core.core.util.configuration.TGConfigManager;
import com.breaktime.gp_core.core.util.singleton.TGSingletonFactory;
import com.breaktime.gp_core.core.util.singleton.TGSingletonUtil;

public class GTPSettingsManager {
	
	public static final String KEY_CHARSET = "charset";
	
	private TGContext context;
	private TGConfigManager config;
	
	private GTPSettings settings;
	
	private GTPSettingsManager(TGContext context){
		this.context = context;
		this.settings = new GTPSettings();
		this.loadSettings();
	}
	
	public GTPSettings getSettings(){
		return this.settings;
	}
	
	public TGConfigManager getConfig(){
		if( this.config == null ){ 
			this.config = new TGConfigManager(this.context, "tuxguitar-gtp");
		}
		return this.config;
	}
	
	public void loadSettings(){
		String charsetDefault = System.getProperty("file.encoding");
		if( charsetDefault == null ){
			charsetDefault = GTPSettings.DEFAULT_CHARSET;
		}
		this.settings.setCharset( getConfig().getStringValue(KEY_CHARSET, charsetDefault) );
	}
	
	public void saveSettings(){
		this.getConfig().setValue(GTPSettingsManager.KEY_CHARSET, this.settings.getCharset());
		this.getConfig().save();
	}
	
	public static GTPSettingsManager getInstance(TGContext context) {
		return TGSingletonUtil.getInstance(context, GTPSettingsManager.class.getName(), new TGSingletonFactory<GTPSettingsManager>() {
			public GTPSettingsManager createInstance(TGContext context) {
				return new GTPSettingsManager(context);
			}
		});
	}
}
