package br.unicamp.padroescriacionais.legacy;

import br.unicamp.padroescriacionais.legacy.domain.FormatoRelatorio;
import br.unicamp.padroescriacionais.legacy.domain.Relatorio;
import br.unicamp.padroescriacionais.legacy.domain.TipoRelatorio;
import br.unicamp.padroescriacionais.legacy.factory.RelatorioGeneratorFactory;
import br.unicamp.padroescriacionais.legacy.generator.RelatorioGenerator;
import br.unicamp.padroescriacionais.legacy.service.ConfiguracaoService;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class RelatorioGeneratorFactoryTest {

    private Relatorio relatorioExemplo() {
        return new Relatorio("Titulo Teste", "Conteudo de teste", TipoRelatorio.VENDAS, LocalDateTime.now());
    }

    @Test
    void deveCriarGeneratorParaPdf() {
        RelatorioGenerator generator = RelatorioGeneratorFactory.criar(FormatoRelatorio.PDF);
        assertNotNull(generator);
        String resultado = generator.gerar(relatorioExemplo());
        assertFalse(resultado.isBlank());
        assertTrue(resultado.contains("PDF"));
    }

    @Test
    void deveCriarGeneratorParaCsv() {
        RelatorioGenerator generator = RelatorioGeneratorFactory.criar(FormatoRelatorio.CSV);
        assertNotNull(generator);
        String resultado = generator.gerar(relatorioExemplo());
        assertFalse(resultado.isBlank());
        assertTrue(resultado.contains(","));
    }

    @Test
    void deveCriarGeneratorParaJson() {
        RelatorioGenerator generator = RelatorioGeneratorFactory.criar(FormatoRelatorio.JSON);
        assertNotNull(generator);
        String resultado = generator.gerar(relatorioExemplo());
        assertFalse(resultado.isBlank());
        assertTrue(resultado.contains("{"));
        assertTrue(resultado.contains("}"));
    }

    @Test
    void deveCriarGeneratorParaXml() {
        RelatorioGenerator generator = RelatorioGeneratorFactory.criar(FormatoRelatorio.XML);
        assertNotNull(generator);
        String resultado = generator.gerar(relatorioExemplo());
        assertFalse(resultado.isBlank());
        assertTrue(resultado.contains("<?xml"));
        assertTrue(resultado.contains("<relatorio>"));
        assertTrue(resultado.contains("</relatorio>"));
    }

    @Test
    void deveCriarGeneratorParaHtml() {
        RelatorioGenerator generator = RelatorioGeneratorFactory.criar(FormatoRelatorio.HTML);
        assertNotNull(generator);
        String resultado = generator.gerar(relatorioExemplo());
        assertFalse(resultado.isBlank());
        assertTrue(resultado.contains("<!DOCTYPE html>"));
        assertTrue(resultado.contains("<html>"));
        assertTrue(resultado.contains("</html>"));
    }

    @Test
    void xmlDeveConterDadosDoRelatorio() {
        Relatorio relatorio = relatorioExemplo();
        String resultado = RelatorioGeneratorFactory.criar(FormatoRelatorio.XML).gerar(relatorio);
        assertTrue(resultado.contains("<titulo>Titulo Teste</titulo>"));
        assertTrue(resultado.contains("<tipo>VENDAS</tipo>"));
    }

    @Test
    void htmlDeveConterDadosDoRelatorio() {
        Relatorio relatorio = relatorioExemplo();
        String resultado = RelatorioGeneratorFactory.criar(FormatoRelatorio.HTML).gerar(relatorio);
        assertTrue(resultado.contains("<h1>Titulo Teste</h1>"));
        assertTrue(resultado.contains("VENDAS"));
    }

    @Test
    void todosFormatosDevemTerGeneratorNaoNulo() {
        Relatorio relatorio = relatorioExemplo();
        for (FormatoRelatorio formato : FormatoRelatorio.values()) {
            RelatorioGenerator generator = RelatorioGeneratorFactory.criar(formato);
            assertNotNull(generator, "Generator nulo para: " + formato);
            String resultado = generator.gerar(relatorio);
            assertFalse(resultado.isBlank(), "Resultado vazio para: " + formato);
        }
    }

    @Test
    void singletonDeveRetornarMesmaInstancia() {
        ConfiguracaoService instancia1 = ConfiguracaoService.getInstance();
        ConfiguracaoService instancia2 = ConfiguracaoService.getInstance();
        assertSame(instancia1, instancia2);
    }

    @Test
    void singletonDeveCompartilharConfiguracaoConsistente() {
        ConfiguracaoService instancia = ConfiguracaoService.getInstance();
        assertNotNull(instancia.getConfiguracao());
        assertFalse(instancia.getConfiguracao().getNomeEmpresa().isBlank());
        assertFalse(instancia.getConfiguracao().getAmbiente().isBlank());
        assertFalse(instancia.getConfiguracao().getDiretorioExportacao().isBlank());
    }

    @Test
    void alteracaoNaSingletonDeveRefletirEmTodasAsReferencias() {
        ConfiguracaoService ref1 = ConfiguracaoService.getInstance();
        ConfiguracaoService ref2 = ConfiguracaoService.getInstance();
        String ambienteOriginal = ref1.getConfiguracao().getAmbiente();

        ref1.getConfiguracao().setAmbiente("HOMOLOG");
        assertEquals("HOMOLOG", ref2.getConfiguracao().getAmbiente());

        ref1.getConfiguracao().setAmbiente(ambienteOriginal);
    }
}
