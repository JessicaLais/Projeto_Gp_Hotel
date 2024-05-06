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
    // Aloca uma pessoa para um quarto
	public synchronized void alocar(Hospede hospede) throws Exception {

		// Todos os quartos do hotel
		List<Quarto> quartos = Main.quartos;

		// Procura alguém da família dele em algum quarto
		for (Quarto quarto : quartos) {
			// Hospede sozinho
			if (hospede.familia == 0) break;

			// Confere se tem alguém e se tem espaço ao mesmo tempo
			if (quarto.getHospedes().size() <= 0 
					|| quarto.getHospedes().size() >= 4) continue;

			// Confere se esse quarto é da família dele
			if (quarto.getHospedes().get(0).familia == hospede.familia) {
				quarto.inserirHospede(hospede); // Insere o hospede
				hospede.quarto = quarto; // Designa o quarto à ele
				Main.fila.remove(hospede);
				//Log
				System.out.println("\n");
				System.out.println(this.nome + " alocou "+ hospede.nome + " para o quarto " 
				+ quarto.numero + " onde estava sua família " + hospede.familia + ".");
				return;
			}
		}
}
