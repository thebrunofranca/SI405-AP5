package br.unicamp.padroescriacionais.legacy.factory;

import br.unicamp.padroescriacionais.legacy.domain.FormatoRelatorio;
import br.unicamp.padroescriacionais.legacy.generator.CsvRelatorioGenerator;
import br.unicamp.padroescriacionais.legacy.generator.HtmlRelatorioGenerator;
import br.unicamp.padroescriacionais.legacy.generator.JsonRelatorioGenerator;
import br.unicamp.padroescriacionais.legacy.generator.PdfRelatorioGenerator;
import br.unicamp.padroescriacionais.legacy.generator.RelatorioGenerator;
import br.unicamp.padroescriacionais.legacy.generator.XmlRelatorioGenerator;

public class RelatorioGeneratorFactory {

    public static RelatorioGenerator criar(FormatoRelatorio formato) {
        switch (formato) {
            case PDF:
                return new PdfRelatorioGenerator();
            case CSV:
                return new CsvRelatorioGenerator();
            case JSON:
                return new JsonRelatorioGenerator();
            case XML:
                return new XmlRelatorioGenerator();
            case HTML:
                return new HtmlRelatorioGenerator();
            default:
                throw new IllegalArgumentException("Formato desconhecido: " + formato);
        }
    }
}
