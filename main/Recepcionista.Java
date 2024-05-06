package main;
import java.util.List;

public class Recepcionista extends Thread {
    String nome;

    public Recepcionista(){
        this.nome = "Recepcionista" + (Main.recepcionistas.size() + 1);
        
        this.start();
    }

    public void run() {
		try {
			while(true) { 
				tentarAlocar(); // Tenta alocar a "pessoa"
				Thread.sleep(1000); // Demora 1 segundo entre cada tentiva
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
        // Procura um hospede ou um grupo para tentar alocar
	public void tentarAlocar() {

		// Hóspedes aguardando alocação
		List<Hospede> hospedes = Main.fila;

		// Se não tiver ninguém esperando na fila, não há nada a fazer
		if (hospedes.size() <= 0) return;

		// Tenta alocar o primeiro da fila ou alguém que tem uma família em um quarto
		try {
			for (Hospede hospede : hospedes) {
				alocar(hospede);
			}
		} catch (Exception e) { return; }
	}
}
