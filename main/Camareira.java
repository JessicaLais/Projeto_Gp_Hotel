package main;
public class Camareira extends Thread{
	String nome;
	
	public Camareira() {
		this.nome = "Camareira" + (Main.camareiras.size() + 1);
		this.start();
	}
	
	@Override
	public void run() {
		try {
			while(true) {
				tentarLimpar();
				Thread.sleep(1000); // Ela espera 1 segundo a cada tentativa
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Procura algum quarto para limpar
	public void tentarLimpar() throws InterruptedException {
        
    }
