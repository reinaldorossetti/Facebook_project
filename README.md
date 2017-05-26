# Facebook_project
This Project show how create automation test with Page Object, Cucumber and Maven in one real project (facebook).

O Projeto Simples em Java, usando Cucumber e Page Object, faltou fazer as classes Helps ao invés disso, existe um pacote Utils que contém funções básicas.


Temos duas features Automatizadas:
1. Login Page;
2. LogOut Page;

# Ordem dos Elementos
ID, CLASS, CSS Selector

** Deixamos alguns elementos pré-mapeados em HomePage.java, vou mudar para um nome mais sugestivo depois.

# Boas Práticas 
### Deixei uma dicionário de elementos da página no topo, pra não ficar mexendo no código abaixo.
### Não deixamos o login e a senha no código, vai aparecer um pop-up para digitar o mesmo.
### Não usamos o texto para localizar os elementos.
### Não usamos Xpath, é uma recomendação dos mestres em selenium.
### Não usamos thread.sleep, as esperas são explícitas.
