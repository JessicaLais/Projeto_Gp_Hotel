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
}