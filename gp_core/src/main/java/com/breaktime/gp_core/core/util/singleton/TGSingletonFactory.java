package com.breaktime.gp_core.core.util.singleton;

import com.breaktime.gp_core.core.util.TGContext;

public interface TGSingletonFactory<T> {
	
	T createInstance(TGContext context);
}
