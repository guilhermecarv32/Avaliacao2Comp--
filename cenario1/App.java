package cenario1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;
import com.github.javafaker.Faker;

public class App {
	
	private static final int TOTAL_PARTIDAS = 10;
	private static final int TOTAL_LEITURAS = 3;
	
	private static List<Thread> executores = new ArrayList<>();
	
	public static String generateRandomNome(Faker faker) {
        String[] nomes = {"1", "2", "3", "4", "5"};
        int index = faker.random().nextInt(nomes.length);
        return nomes[index];
    }

	public static void iniciarProcessamentoDeLeituras(Map<Partida, SensoresImpl> partidas) {
		List<Chute> media = new ArrayList<>();
		media.add(new Chute(70, 35, 40));
		media.add(new Chute(80, 45, 25));
		media.add(new Chute(60, 25, 55));
		media.add(new Chute(90, 50, 65));
		
		for(Entry<Partida, SensoresImpl> item : partidas.entrySet()) {
			Partida partida = item.getKey();
			SensoresImpl sensores = item.getValue();
			
			Thread executor = new Thread(new ExecutorImpl(partida, sensores, media, TOTAL_LEITURAS));
			executores.add(executor);
			
			executor.start();
		}
	}
	
	public static void esperarFinalizacaoDeProcessamento() throws InterruptedException {
		for(Thread executor : executores) {
			executor.join();
		}
	}
	
	public static Map<Partida, SensoresImpl> gerarPartidas(){
		
		
		Map<Partida, SensoresImpl> partidas = new TreeMap<>();
		for(int i = 0; i < TOTAL_PARTIDAS; i++) {
			String idPartida = "Partida " + (i + 1);
			Partida partida = new Partida(idPartida);
			
			partidas.put(partida, new SensoresImpl());
		}
		
		return partidas;
	}
	
	public static void main(String[] args) throws Exception{

		Map<Partida, SensoresImpl> partidas = gerarPartidas();
		
		System.out.println("Iniciando Processamento...");
		iniciarProcessamentoDeLeituras(partidas);
        esperarFinalizacaoDeProcessamento();
        
        System.out.println("Processo Finalizado.");
	}

}
