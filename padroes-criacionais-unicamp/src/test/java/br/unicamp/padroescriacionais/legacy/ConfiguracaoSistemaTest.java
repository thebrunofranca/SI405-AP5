package br.unicamp.padroescriacionais.legacy;

import br.unicamp.padroescriacionais.legacy.domain.ConfiguracaoSistema;
import br.unicamp.padroescriacionais.legacy.service.ConfiguracaoService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConfiguracaoSistemaTest {

    @Test
    void deveCriarConfiguracaoComValoresInformados() {
        ConfiguracaoSistema config = new ConfiguracaoSistema(
                "Empresa Teste",
                "DEV",
                "/tmp/test",
                true
        );

        assertEquals("Empresa Teste", config.getNomeEmpresa());
        assertEquals("DEV", config.getAmbiente());
        assertEquals("/tmp/test", config.getDiretorioExportacao());
        assertTrue(config.isDebugAtivo());
    }

    @Test
    void devePermitirAlteracaoDeAmbiente() {
        ConfiguracaoSistema config = new ConfiguracaoSistema("Empresa", "DEV", "/tmp", false);
        config.setAmbiente("PROD");

        assertEquals("PROD", config.getAmbiente());
    }

    @Test
    void devePermitirAlteracaoDeDebug() {
        ConfiguracaoSistema config = new ConfiguracaoSistema("Empresa", "DEV", "/tmp", false);
        config.setDebugAtivo(true);

        assertTrue(config.isDebugAtivo());
    }

    @Test
    void devePermitirAlteracaoDeDiretorio() {
        ConfiguracaoSistema config = new ConfiguracaoSistema("Empresa", "DEV", "/tmp", false);
        config.setDiretorioExportacao("/novo/diretorio");

        assertEquals("/novo/diretorio", config.getDiretorioExportacao());
    }

    @Test
    void duasInstanciasIndependentesPodemTerAmbientesDiferentes() {
        ConfiguracaoSistema configDev = new ConfiguracaoSistema("Empresa", "DEV", "/tmp", true);
        ConfiguracaoSistema configProd = new ConfiguracaoSistema("Empresa", "PROD", "/exports", false);

        assertNotEquals(configDev.getAmbiente(), configProd.getAmbiente());
        assertNotEquals(configDev.getDiretorioExportacao(), configProd.getDiretorioExportacao());
        assertNotEquals(configDev.isDebugAtivo(), configProd.isDebugAtivo());
    }

    @Test
    void alteracaoEmUmaInstanciaNaoAfetaOutra() {
        ConfiguracaoSistema config1 = new ConfiguracaoSistema("Empresa", "DEV", "/tmp", false);
        ConfiguracaoSistema config2 = new ConfiguracaoSistema("Empresa", "DEV", "/tmp", false);

        config1.setAmbiente("PROD");

        assertEquals("PROD", config1.getAmbiente());
        assertEquals("DEV", config2.getAmbiente());
    }

    @Test
    void configuracaoServiceDeveRetornarConfiguracaoNaoNula() {
        ConfiguracaoService service = ConfiguracaoService.getInstance();
        assertNotNull(service.getConfiguracao());
        assertFalse(service.getConfiguracao().getNomeEmpresa().isBlank());
    }
}
