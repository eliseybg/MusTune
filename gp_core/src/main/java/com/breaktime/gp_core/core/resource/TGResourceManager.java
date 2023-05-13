package com.breaktime.gp_core.core.resource;

import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;

import com.breaktime.gp_core.core.util.TGContext;
import com.breaktime.gp_core.core.util.singleton.TGSingletonFactory;
import com.breaktime.gp_core.core.util.singleton.TGSingletonUtil;

public class TGResourceManager implements TGResourceLoader {
	
	private TGResourceLoader resourceLoader;
	
	private TGResourceManager(){
		super();
	}
	
	public <T> Class<T> loadClass(String name) throws TGResourceException {
		if( this.resourceLoader != null ) {
			return this.resourceLoader.loadClass(name);
		}
		return null;
	}

	public InputStream getResourceAsStream(String name) throws TGResourceException {
		if( this.resourceLoader != null ) {
			return this.resourceLoader.getResourceAsStream(name);
		}
		return null;
	}

	public URL getResource(String name) throws TGResourceException {
		if( this.resourceLoader != null ) {
			return this.resourceLoader.getResource(name);
		}
		return null;
	}

	public Enumeration<URL> getResources(String name) throws TGResourceException {
		if( this.resourceLoader != null ) {
			return this.resourceLoader.getResources(name);
		}
		return null;
	}
	
	public TGResourceLoader getResourceLoader() {
		return resourceLoader;
	}

	public void setResourceLoader(TGResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

	public static TGResourceManager getInstance(TGContext context) {
		return TGSingletonUtil.getInstance(context, TGResourceManager.class.getName(), new TGSingletonFactory<TGResourceManager>() {
			public TGResourceManager createInstance(TGContext context) {
				return new TGResourceManager();
			}
		});
	}
}
