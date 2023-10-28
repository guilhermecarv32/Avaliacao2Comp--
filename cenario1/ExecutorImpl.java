package cenario1;

import java.util.List;

public class ExecutorImpl extends Executor{

	private Partida partida = null;
	private SensoresImpl sensores = null;
	
	AnaliseMedia analise = new AnaliseMedia();
	Medias media = null;
	
	public ExecutorImpl(Partida partida, SensoresImpl sensores, List<Chute> mediasChutes, int totalLeituras) {
		super(totalLeituras);
		
		this.partida = partida;
		this.sensores = sensores;
		this.media = new Medias();
	}
	
	@Override
    public void processarLeitura(int leituraAtual) {
        List<Chute> leituras = sensores.gerarLeituras(totalLeituras);

        for (Chute leitura : leituras) {
            partida.onLeitura(leitura);

            String informacoes = analise.atuar(partida.getLeituras());
            informacoes = informacoes.isEmpty() ? "Velocidade normal" : informacoes;
            informacoes += ": [Velocidade = " + leitura.getVelocidade() + " RPM = " + leitura.getRPM() + " Forca = " + leitura.getForca();

            System.out.println("Analise feita no chute #" + leituraAtual + " da partida " + partida.getId() + ": " + informacoes + "]");
        }
    }
}