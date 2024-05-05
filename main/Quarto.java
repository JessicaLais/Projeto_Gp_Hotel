package main;
import java.util.ArrayList;
import java.util.List;

public class Quarto {
	int numero;
	boolean vago;
	boolean limpo;
	private List<Hospede> hospedes;
	boolean limpando;/* "limpando" criada para evitar o grande conflito entre camareiras*/

	public Quarto() {
		this.numero = Main.quartos.size() + 1;
		// já começa vago e limpo
		this.vago = true;
		this.limpo = true;

		this.hospedes = new ArrayList<Hospede>();
	}

	public synchronized void inserirHospede(Hospede hospede) throws Exception {
		
		// Barrar pra impedir de adicionar mais que a capacidade máxima
		if (this.hospedes.size() >= 4) {
			throw new Exception("Não é possível adicionar " +
					hospede.nome + " no quarto " + this.numero +
					". Quarto cheio");
		}
		this.hospedes.add(hospede);
		this.vago = false;// Ocupando e sujando o quarto
		this.limpo = false;
	}
}