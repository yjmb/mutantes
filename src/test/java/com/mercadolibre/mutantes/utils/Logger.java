package com.mercadolibre.mutantes.utils;

import org.joda.time.DateTime;


public class Logger {

	private static final String DATEPATTERN = "MM-dd-yyyy HH:mm:ss ZZ";
	private static final String LOGTEMPLATE = "[%s %s] %s";
	
	public static void printInfo(String message){
		String logMessage = String.format(LOGTEMPLATE,"INFO",DateTime.now().toString(DATEPATTERN),message);
		//por ahora se envia a consola, debe escribir en un archivo
		System.out.print(logMessage);
	}
	public static void printError(String message) {
		String logMessage = String.format(LOGTEMPLATE,"ERROR",DateTime.now().toString(DATEPATTERN),message);
		//por ahora se envia a consola, debe escribir en un archivo
		System.out.print(logMessage);
		
	}


}
