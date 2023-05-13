package com.breaktime.gp_core.core.util.error;

import java.util.ArrayList;
import java.util.List;

import com.breaktime.gp_core.core.util.TGContext;
import com.breaktime.gp_core.core.util.singleton.TGSingletonFactory;
import com.breaktime.gp_core.core.util.singleton.TGSingletonUtil;

public class TGErrorManager {
	
	private List<TGErrorHandler> errorHandlers;
	
	private TGErrorManager(){
		this.errorHandlers = new ArrayList<TGErrorHandler>();
	}
	
	public void handleError(Throwable throwable){
		for(TGErrorHandler tgErrorHandler : this.errorHandlers) {
			tgErrorHandler.handleError(throwable);
		}
	}
	
	public void addErrorHandler(TGErrorHandler errorHandler){
		if(!this.errorHandlers.contains(errorHandler) ){
			this.errorHandlers.add(errorHandler);
		}
	}
	
	public void removeErrorHandler(TGErrorHandler errorHandler){
		if( this.errorHandlers.contains(errorHandler) ){
			this.errorHandlers.remove(errorHandler);
		}
	}
	
	public List<TGErrorHandler> getErrorHandlers() {
		return this.errorHandlers;
	}
	
	public void clear() {
		this.errorHandlers.clear();
	}
	
	public static TGErrorManager getInstance(TGContext context) {
		return TGSingletonUtil.getInstance(context, TGErrorManager.class.getName(), new TGSingletonFactory<TGErrorManager>() {
			public TGErrorManager createInstance(TGContext context) {
				return new TGErrorManager();
			}
		});
	}
}
