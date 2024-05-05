package main;
public class Camareira{
	String nome;
	
	public Camareira() {
		this.nome = "Camareira" + (Main.camareiras.size() + 1);
		this.start();
	}
    @Override
	public void run() {
    }
}
