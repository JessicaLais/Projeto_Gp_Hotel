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
	}
}