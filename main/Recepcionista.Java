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

}
