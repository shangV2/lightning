package com.lighting.rpc.core.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.lighting.rpc.common.exception.LightningServiceException;
import com.lighting.rpc.core.utility.LoggerUtility;

public class LogProxyHandler<T> implements InvocationHandler {

	private T instance;

	public LogProxyHandler(T instance) {
		this.instance = instance;
	}
	
	public Object createProxy() {
		return Proxy.newProxyInstance(instance.getClass().getClassLoader(), 
				instance.getClass().getInterfaces(), this);
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		
		LoggerUtility.beforeInvoke();
		try {
			Object res = method.invoke(instance, args);
			LoggerUtility.returnInvoke();
			return res;
		} catch (Throwable e) {
			if ( e instanceof LightningServiceException ) {
				LoggerUtility.throwableInvode(
						"[result = exception : {%d, %s}]",
						((LightningServiceException) e).getErrorCode(),
						((LightningServiceException) e).getErrorMessage()
					);
			} else {
				LoggerUtility.throwableInvode("[result = exception: {%s}]", e.getMessage());
			}
			throw e;
		}
	
	}

}
