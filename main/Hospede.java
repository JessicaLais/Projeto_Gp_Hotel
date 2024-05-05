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
}
	
