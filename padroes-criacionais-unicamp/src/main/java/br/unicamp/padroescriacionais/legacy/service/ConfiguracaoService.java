package br.unicamp.padroescriacionais.legacy.service;

import br.unicamp.padroescriacionais.legacy.domain.ConfiguracaoSistema;

public class ConfiguracaoService {

    private static class Holder {
        private static final ConfiguracaoService INSTANCIA = new ConfiguracaoService();
    }

    private final ConfiguracaoSistema configuracao = new ConfiguracaoSistema(
            "Empresa XPTO Ltda.",
            "DEV",
            "/tmp/relatorios",
            true
    );

    private ConfiguracaoService() {}

    public static ConfiguracaoService getInstance() {
        return Holder.INSTANCIA;
    }

    public ConfiguracaoSistema getConfiguracao() {
        return configuracao;
    }

    public void exibirConfiguracao() {
        System.out.println("=== Configuracao do Sistema ===");
        System.out.println("Empresa    : " + configuracao.getNomeEmpresa());
        System.out.println("Ambiente   : " + configuracao.getAmbiente());
        System.out.println("Diretorio  : " + configuracao.getDiretorioExportacao());
        System.out.println("Debug ativo: " + configuracao.isDebugAtivo());
        System.out.println("================================");
    }
}
