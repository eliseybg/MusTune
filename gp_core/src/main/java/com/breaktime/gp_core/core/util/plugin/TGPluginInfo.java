package com.breaktime.gp_core.core.util.plugin;

import com.breaktime.gp_core.core.util.TGContext;
import com.breaktime.gp_core.core.util.properties.TGProperties;
import com.breaktime.gp_core.core.util.properties.TGPropertiesManager;
import com.breaktime.gp_core.core.util.properties.TGPropertiesUtil;

public class TGPluginInfo {
	
	public static final String RESOURCE = "plugin-info";
	
	private TGContext context;
	private TGProperties properties;
	
	public TGPluginInfo(TGContext context, String moduleId){
		this.context = context;
		this.initialize(moduleId);
	}
	
	public void initialize(String moduleId){
		this.properties = TGPropertiesManager.getInstance(this.context).createProperties();
		this.loadPluginInfo(moduleId);
	}
	
	public void loadPluginInfo(String moduleId){
		TGPropertiesManager.getInstance(this.context).readProperties(this.properties, RESOURCE, moduleId);
	}
	
	public TGProperties getProperties(){
		return this.properties;
	}
	
	public String getStringValue(String key) {
		return TGPropertiesUtil.getStringValue(this.properties, key);
	}
	
	public String getName(){
		return TGPropertiesUtil.getStringValue(this.properties, "plugin.name");
	}
	
	public String getDescription(){
		return TGPropertiesUtil.getStringValue(this.properties, "plugin.description");
	}
	
	public String getVersion(){
		return TGPropertiesUtil.getStringValue(this.properties, "plugin.version");
	}
	
	public String getAuthor(){
		return TGPropertiesUtil.getStringValue(this.properties, "plugin.author");
	}
}
