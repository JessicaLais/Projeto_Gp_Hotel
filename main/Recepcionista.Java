package main;
import java.util.List;

public class Recepcionista extends Thread {
    String nome;

    public Recepcionista(){
        this.nome = "Recepcionista" + (Main.recepcionistas.size() + 1);
        
        this.start();
    }

    public void run(){

    }
}
