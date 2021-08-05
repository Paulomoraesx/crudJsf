package br.com.projetoweb.util;

public class VerificadorUtil {
	
	public static boolean estaNulo(Object valor) {
		return valor == null;
	}
	public static boolean naoEstaNulo(Object valor) {
		return valor != null;
	}
	public static boolean estaNuloOuVazio(Object valor) {
		return estaNulo(valor) || estaVazio(valor);
	}
	public static boolean estaVazio(Object valor) {
		return StringUtil.isEmpty(valor.toString());
	}
	public static boolean naoEstaNuloOuVazio(Object valor) {
		return !estaNuloOuVazio(valor);
	}

}
