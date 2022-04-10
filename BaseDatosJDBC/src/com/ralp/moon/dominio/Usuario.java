package com.ralp.moon.dominio;

public class Usuario {
	private int idUsario;
	private String usuario;
	private String pass;
	public int getIdUsario() {
		return idUsario;
	}
	public void setIdUsario(int idUsario) {
		this.idUsario = idUsario;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	@Override
	public String toString(){
		return "idUsuario: " + this.idUsario
				+ "usuario: " + this.usuario
				+ "password: " + this.pass;
	}
}
