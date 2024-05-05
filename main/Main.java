package main;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static List<Quarto> quartos;

	public static List<Hospede> hospedes;

	public static List<Camareira> camareiras;

	public static List<Recepcionista> recepcionistas;

	public static List<Integer> chaves;

	public static List<Hospede> fila;

	public static void main(String[] args) {


		quartos = new ArrayList<Quarto>();
		hospedes = new ArrayList<Hospede>();
		camareiras = new ArrayList<Camareira>();
		recepcionistas = new ArrayList<Recepcionista>();

		chaves = new ArrayList<Integer>();
		fila = new ArrayList<Hospede>();
		try {
			while (quartos.size() < 10 + Configs.QUANTIDADE_DE_QUARTOS) {
				quartos.add(new Quarto());
				Thread.sleep(10);
			}
			System.out.println("----------------------");
			System.out.println("Quartos:");
			for (Quarto quarto : quartos) {
				System.out.println("    " + quarto.numero);
			}
			System.out.println("-----------------------");
		} catch (Exception e) {}
	}
}
