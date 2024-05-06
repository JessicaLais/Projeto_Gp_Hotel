package main;

import java.util.Random;

public class Hospede extends Thread{
	String nome;
	boolean passeando = false;
	boolean irEmbora;
	int tentativa = 0; // vezes q tentou reservar
	int familia; // Se for "0": não é de nenhuma família, está sozinho

	Quarto quarto; // Se for "null" é  que o "hospede" é apenas uma pessoa não hospedada
	
	Random random;
	int escolha; // O que o hospede quer fazer

	public Hospede() {
		this.quarto = null;
		
		this.random = new Random();
		
		if (random.nextInt(101) <= Configs.PROBABILIDADE_SOZINHO) this.familia = 0;
		else this.familia = random.nextInt(1, Configs.QUANTIDADE_DE_FAMILIAS + 1);
		
		this.nome = "Hospede" + (Main.hospedes.size() + 1) + "(" + this.familia + ")";
		
		this.start();
	}
	@Override
	public void run() {
		System.out.println("\n");
		System.out.println(this.nome + " chegou ao hotel!");
		try {
			this.escolha = 0;
			Thread.sleep(5000);
			while (true) { 
				if (this.quarto == null) {
					if (tentativa == 1) { passearPelaCidade(); }
					if (tentativa >= 2) { 
						reclamar(); 
						Main.fila.remove(this); 
						Main.hospedes.remove(this);
						break; 
					}
					
					esperar();
					continue;
				}
				
				// Vai embora
				if (irEmbora) { 
					int numQuarto = this.quarto.numero;
					this.quarto.retirarHospede(this); // Sai da lista do quarto
					this.quarto = null; // Não pertencen a mais nenhum quarto
					Main.hospedes.remove(this); // Sai da lista de hospedes
					
					System.out.println("\n");
					System.out.println(this.nome + " saiu do quarto " + numQuarto);
					System.out.println(this.nome + " foi embora do Hotel. Agora têm " + Main.hospedes.size() + " hospedes no hotel");
					return; 
				}
				
				// Apenas fica no quarto
				if (this.escolha < 3) {}
				
				// Vai passear
				else if (this.escolha < 5) { passear(); }
				
				// Toma a iniciativa de ir embora
				else if (this.escolha == 5) { 
					irEmbora = true;
					
					// Levando toda a família junto com ele
					if (this.familia == 0) continue;
					for (Hospede hospede : Main.hospedes) {
						if (hospede.familia == this.familia) hospede.irEmbora = true;
					}
				}
				
				// Gera uma escolha entre 0 (inclusivo) e 6 (não inclusivo)
				// Caso tenha voltado de um passeio, vai ficar no quarto
				this.escolha = random.nextInt(6);
				Thread.sleep(3000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void esperar() throws InterruptedException {
		Thread.sleep(Configs.TEMPO_ESPERA); // Espera
		if (quarto == null) tentativa++;
	}
	// hóspede cansado de esperar vai passear
	public synchronized void passearPelaCidade() throws InterruptedException {
		Main.fila.remove(this); // Sai da fila
		this.passeando = true; // Vai passear
	
		System.out.println("\n");
		System.out.println(this.nome + " cansou de esperar, foi passear.");
		Thread.sleep(Configs.TEMPO_PASSEIO); // Tempo passeando
		Main.fila.add(this);
	}

	public synchronized void passear() throws InterruptedException {
		
		if (!Main.chaves.contains(this.quarto.numero) && this.escolha != 0) { Main.chaves.add(quarto.numero); }
		
		this.escolha = 0;

		// Agora, o resto do quarto tem que ir passear também
		for (Hospede hospede : this.quarto.getHospedes()) {
			if (hospede.equals(this)) continue; // Se for ele mesmo, ignore
			if (!hospede.passeando) hospede.escolha = 3;
		}
		
		this.escolha = 0;
		
		// O quarto foi usado, logo não está limpo
		this.quarto.limpo = false;
		
		this.passeando = true; // Vai passear

		System.out.println("\n");
		System.out.println(this.nome + "(quarto " + this.quarto.numero + ") foi passear!");

		Thread.sleep(Configs.TEMPO_PASSEIO);
		
		tentarRetornar();
	}
	public synchronized void tentarRetornar() throws InterruptedException {
		if (!passeando) return;
		
		// Se as chaves não estiverem na recepção, significa que a camareira ainda está lá.Então passeia dnv
		if (!Main.chaves.contains(this.quarto.numero)) { 
			System.out.println("\n");
			System.out.println(this.nome + " tentou voltar para o quarto depois do passeio, "
					+ "mas a camareira ainda estava o limpando.");
			passear();
			return;
		}
		// Pega as chaves novamente
		Main.chaves.remove(Main.chaves.indexOf(this.quarto.numero));
		
		retornar();
	}
	// Volta para o quarto
	public void retornar() {
		// Não está mais passeando
		this.passeando = false;
		// Traz o resto do quarto junto
		for (Hospede hospede : this.quarto.getHospedes()) {
			if (hospede.equals(this)) continue; // Se for ele mesmo, ignore
			if (hospede.passeando) hospede.retornar();
		}
		System.out.println("\n");
		System.out.println(this.nome + " voltou do passeio para o quarto " + this.quarto.numero);
	}
	public void reclamar() {
	System.out.println("\n");
	System.out.println(this.nome + " deixou sua reclamação por esperar demais!");
	}
}
	
