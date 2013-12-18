package com.prueba.myplugin;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.telephony.TelephonyManager;



public class InfoTelefonoPlugin extends CordovaPlugin {
	
    
	public static final String OBTENER_INFO_ACCION = "OBTENER_INFO_ACCION";

    
    public boolean execute (String action, JSONArray args, CallbackContext ctx) throws JSONException {
    	
    	boolean resultado = false;
    	
    	//si hay args, se pueden recuperar así: 
    	//long param = args.getLong(0);
    	
    	
    	try{
    		
    		System.out.println("LLego aqui 1");
    		
    		if(OBTENER_INFO_ACCION.equals(action) ){
    			
    			System.out.println("LLego aqui 2 " + action);
    			
    			JSONObject jsonSuccess = this.obtenerInfoTelefonoImpl();
    			//callbackContext es la función de retorno
    			//ctx.success(jsonSuccess); //esto valdria perfectamente
    			//Y esta es la version "larga"
    			ctx.sendPluginResult(
    					new PluginResult (PluginResult.Status.OK, jsonSuccess));
    					
    			
    		}
    		else
    			throw new IllegalArgumentException( action + " no soportada.");
    		
    		resultado = true;
    		
    		System.out.println("LLego aqui 3");
    	}
    	catch(Throwable exc){
    		
			
					JSONObject jsonError = new JSONObject( "{\"mensaje\" : \"" + exc.getMessage() + "\"}");
					ctx.sendPluginResult(
						new PluginResult (PluginResult.Status.ERROR, jsonError) );
			
    	}
    	return resultado;
    }


	private JSONObject obtenerInfoTelefonoImpl() throws JSONException {
		// TODO Auto-generated method stub
		
		//super.cordova.getActivity():
		//  Apunta al CordovaActivity, en este caso MostrarInfoTelefono
		//
		
		TelephonyManager manager = (TelephonyManager) super.cordova.getActivity().getSystemService(Context.TELEPHONY_SERVICE);
		
		String numero = manager.getLine1Number();
		String imei = manager.getDeviceId();		
		String imsi = manager.getSubscriberId(); //Es un numero UNICO (teóricamente) a nivel mundial de cada dispositivo.
		
		//Cómo retorno ésto?
		// El caso es obtener un objeto JSON. 
		
		String jsonString = "{ 'numero' : '{0}', 'imei' : '{1}', 'imsi' : '{2}'}";
		
		jsonString =  jsonString.replaceAll("'", "\"")
								.replace("{0}", numero)
								.replace("{1}", imei)
								.replace("{2}", imsi);
		
		return new JSONObject(jsonString);

	}
    
}
