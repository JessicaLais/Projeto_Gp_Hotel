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
		try {
			while (hospedes.size() < 50 + Configs.QUANTIDADE_DE_HOSPEDES) {
				hospedes.add(new Hospede()); 
				Thread.sleep(10);
			}

			
			List<Integer> familias = new ArrayList<Integer>();

			
			for (Hospede h : hospedes) {
				if (!familias.contains(h.familia)) familias.add(h.familia);
			}

			
			for (int f : familias ) {
				for (Hospede h : hospedes) {
					if (h.familia == f) fila.add(h);
				}
			}

			
			System.out.println("--------------------");
			System.out.println("FILA:");
			for (Hospede h : fila) {
				System.out.println("    " + h.nome);
			}
			System.out.println("--------------------");


		} catch (Exception e) {}

		try {
			
			while (camareiras.size() < 10 + Configs.QUANTIDADE_DE_CAMAREIRAS) {
				camareiras.add(new Camareira()); 
				Thread.sleep(100);
			}
		} catch (Exception e) {}

		try {
		
			while (recepcionistas.size() < 5 + Configs.QUANTIDADE_DE_RECEPCIONISTAS) {
				recepcionistas.add(new Recepcionista());
				Thread.sleep(100);
			}
		} catch (Exception e) {}

		try {
			
			while (Configs.GERAR_HOSPEDES) {
				Thread.sleep(1000);
				if (hospedes.size() < 50) {
					Hospede novoHospede = new Hospede();
					hospedes.add(novoHospede); 
					fila.add(novoHospede); 
				}
			}
		} catch (Exception e) {}
	}
}
