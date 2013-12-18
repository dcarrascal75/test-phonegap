
/** modulos: asigno un ID a una funcion
 * 
 * ID: com.prueba.myplugin.InfoTelefono
 *  
 *  */

cordova.define("com.prueba.myplugin.InfoTelefono", 
		
		function( require, exports, module ){

		var InfoTelefono=function (numero, imei, imsi) {
			this.numero = numero;
			this.imei=imei;
			this.imsi = imsi;
		}

		/** ... */
		InfoTelefono.prototype.numero = null;
		InfoTelefono.prototype.imei = null;
		InfoTelefono.prototype.imsi = null;
		
	/* Cuando se pida esta funcion, es esta linea la que la exporta */
		
		module.exports = InfoTelefono; 
});