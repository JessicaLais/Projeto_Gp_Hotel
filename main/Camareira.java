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
		
		//Confere quarto por quarto
		for (Quarto quarto : Main.quartos) {
			boolean vazio = true;
			
			// Se o quarto estiver limpo, ela ignora
			if (quarto.limpo) continue;
			
			// Se a chave do quarto estiver na recepção
			// É por que alguém foi passear e deixou lá
			if (Main.chaves.contains(quarto.numero)) {
				
				// Checa se o quarto está vazio
				for (Hospede h : quarto.getHospedes()) {
					if (!h.passeando) {
						vazio = false;
						break;
					}
				}
				
				if (!vazio) continue;
				
				Main.chaves.remove(Main.chaves.indexOf(quarto.numero)); // Pega as chaves
				limpar(quarto);
				Main.chaves.add(quarto.numero); // Devolve as chaves
				break;
			}
			
			// Se o quarto não tiver designado à ninguém
			// Provavelmente alguém terminou a estadia
			if (quarto.vago) {
				
				/* É importante a gente avisar que o quarto não está vago.
				 * Por mais que ele não esteja designado à nenhum hospede,
				 * tem uma camareira ali limpando. É útil não deixá-lo como
				 * vago nesse contexto pois aí impedimos os recepcionistas
				 * de alocar pessoas para este quarto e também impedimos 
				 * outras camareiras de tentar limpá-lo. */
				quarto.vago = false;
				limpar(quarto);
				quarto.vago = true;
			}
		}
	}
}