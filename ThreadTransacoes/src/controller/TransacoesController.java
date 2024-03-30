package controller;

import java.util.concurrent.Semaphore;

public class TransacoesController extends Thread{
	private int id;
	private int tipoTransacao;
	private int permSaque;
	private int permDeposito;
	Semaphore semaforoSaque = new Semaphore(permSaque);
	Semaphore semaforoDeposito = new Semaphore(permDeposito);
	public TransacoesController(int id, int tipoTransacao, int permSaque, int permDeposito, Semaphore semaforoSaque, Semaphore semaforoDeposito) {
		this.id = id;
        this.tipoTransacao = tipoTransacao;
        this.permSaque = permSaque;
        this.permDeposito = permDeposito;
        this.semaforoSaque = semaforoSaque;
        this.semaforoDeposito = semaforoDeposito;  
	}
	@Override
	public void run() {
		if (tipoTransacao == 0) {
            try {
                semaforoSaque.acquire();
                saque();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaforoSaque.release();
            }
        } else if (tipoTransacao == 1) {
            try {
                semaforoDeposito.acquire();
                deposito();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaforoDeposito.release();
            }
        }
	}
	public void saque() {
		        System.out.println("Cliente " + id + " sacando");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Cliente " + id + " terminou de sacar");
    }
    public void deposito() {
        System.out.println("Cliente " + id + " depositando");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Cliente " + id + " terminou de depositar");
	}
}
