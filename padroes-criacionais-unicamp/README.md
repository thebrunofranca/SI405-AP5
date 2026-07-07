# Sistema de Relatórios Corporativos — Versão Legado

> Atividade Prática — Padrões Criacionais de Projeto  
> Disciplina: Padrões de Projeto de Software | Instituto de Computação — Unicamp

---

## Sobre o sistema

Este repositório contém o código-fonte de um sistema interno de geração e exportação de relatórios corporativos desenvolvido para uma empresa fictícia. O sistema permite gerar relatórios de vendas, estoque e clientes nos formatos PDF, CSV e JSON, com suporte a configurações globais de ambiente.

O sistema foi desenvolvido ao longo do tempo por diferentes equipes e encontra-se em produção. O código funciona corretamente, atende aos requisitos funcionais e possui cobertura de testes automatizados.

---

## Objetivo da atividade

Esta atividade propõe uma **refatoração arquitetural** do sistema, com foco na aplicação de padrões criacionais de projeto. O sistema atual, embora funcional, apresenta características típicas de código legado que dificultam sua manutenção e evolução.

Vocês deverão analisar o código existente, identificar os aspectos que dificultam a extensibilidade e a manutenção, e propor e implementar uma solução utilizando os padrões **Factory Method** e **Singleton**.

---

## Pré-requisitos

- Java 17 ou superior
- Apache Maven 3.8 ou superior

---

## Como executar

Compilar e empacotar:

```bash
mvn clean package -q
```

Executar o sistema interativo:

```bash
java -jar target/sistema-relatorios.jar
```

Alternativamente, sem gerar o JAR:

```bash
mvn exec:java -Dexec.mainClass="br.unicamp.padroescriacionais.legacy.Main"
```

---

## Como rodar os testes

```bash
mvn test
```

Para exibir o resultado detalhado de cada caso de teste:

```bash
mvn test -Dsurefire.useFile=false
```

---
