# Prova Java

Caso de uso: em um API Restful, com back-end isolado do front, é necessário adicionar item(s) no carrinho;

### Detalhes da aplicação

* Projeto Java, configurado p/ JDK 1.8;
* Build e gestão de dependências no Maven;
* Projeto c/ suporte a Spring Framework, lombok e Guava;
* Aplicação web autocontida (tomcat) via Spring Boot;
* Por simplicidade a persistência default é em memoria;

### Objetivo Geral 

De forma geral o objetivo é avaliar e ajustar, se necessário, as camadas/componentes:
* API Restful e coesa.
* Código de fluxo de negócio.
* Código de infra-estrutura.

### Ponto de Atenção:

Avalie bem o fluxo que adicionar item no carrinho. Analise:
* Caso o carrinho ja exista, o fluxo para adicionar um novo item está correto? 
* Posso adicionar o mesmo item mais de uma vez?

### Dicas:

* Cobrir o código de negócio com testes p/ aumentar a agilidade de evolução, além de trazer uma certa visão de impacto na evolução do código;
* O código deve ser o mais simples e limpo possível, lembre-se que quase sempre nosso dia-a-dia é manutenção.
* Fique a vontade refatore o quanto você avaliar necessário.
