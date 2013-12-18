cordova.define("com.prueba.myplugin.infotelefonoplugin",
		
		function(require, exports, module){
	

	          
	          //podria cargar incluso com.apache.plugin.contacts de la misma forma 
	          //var contacts = require('org.apache...');
	          // (habria que añadir el plugin, claro)
	          // o si fuera mio 
	          //
	          //var contacts = require('com.prueba.myplugin.miotroplugin');
	          //
	          
    	      //esto es "cargar jScript sin jScript"
              var InfoTelefono = require('./InfoTelefono');
              
              var exec = require('cordova/exec');
              
              var InfoTelefonoPlugin = function (){
            	  
              };
              
              //Esta es la funcion que ejecutará el usuario:
              //window.telefono.obtenerInfo();
              
              InfoTelefonoPlugin.prototype.obtenerInfo = function (success, fail){
            	  
            	  //exec(success, fail, 'InfoTelefonoPlugin', [] );  //invoca a java
            	  
            	  // y más limpio, en vez de retornar un literal java:
            	  
            	  exec(function (jsonJava) {
            		  
            		  var resultado = new InfoTelefono();
            		  resultado.imei = jsonJava.imei;
            		  resultado.numero = jsonJava.numero;
            		  resultado.imsi = jsonJava.imsi;
            		
            		  //y lo que se quiera añadir de calculos y funciones.
            		  
            		  success(resultado);  
            		  
            	  }, fail, 'InfoTelefonoPlugin', 'OBTENER_INFO_ACCION', [] );  //invoca al java InfoTelefonoPlugin con el parametro obtener_info_accion y el array vacio
            	  
              };
              
              //quiero exportar un objeto ya creado.
              module.exports = new InfoTelefonoPlugin();
              
             //para invocarlo se usará:
             // window.telefono.obtenerInfo(function(infotelefono) {...})
         }
);
		