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
		
		this.nome = "Hospede" + Main.rotatividade + "(" + this.familia + ")";
		
		this.start();
	}
	// hóspede cansado de esperar vai passear
	public synchronized void passearPelaCidade() throws InterruptedException {
		Main.fila.remove(this); // Sai da fila
		this.passeando = true; // Vai passear
		//Log
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
}
	
