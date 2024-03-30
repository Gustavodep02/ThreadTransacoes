package view;

import java.util.concurrent.Semaphore;

import controller.TransacoesController;

public class Main {

	public static void main(String[] args) {
		int permSaque = 1;
		int permDeposito = 1;
		int tipoTransacao;
		Semaphore semaforoSaque = new Semaphore(permSaque);
		Semaphore semaforoDeposito = new Semaphore(permDeposito);
		for (int id = 0; id < 20; id++) {
            tipoTransacao = (int) (Math.random() * 2);
            Thread thread = new TransacoesController(id, tipoTransacao, permSaque, permDeposito, semaforoSaque, semaforoDeposito);
            thread.start();
        }

	}

}
