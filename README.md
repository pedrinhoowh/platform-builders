<h1 align="center">Entrevista Builders</h1>

## Cadastro de cliente
<p align="center">Foi realizado um breve cadastro de clientes no qual atende a todos os requisitos 
propostos: CRUD completo.</p>

## Tecnologia
<p id="tecno" align="center">Projeto foi feito em JAVA 8 utilizando spring boot (2.0.0.RELEASE). Além disso, é utilizado
Spring data, Spring mvc, Lombok e Mapstruct. Seu gerenciador de dependências é o Maven 3.6.3. Além disso, foi 
acrescido os testes de mutação afim de medir a qualidade dos testes unitários.</p>

## Como subir a aplicação
<p id="tecno" align="center">Passo à passo descrito abaixo:</p>

<p id="tecno" align="center">1º - mvn clean install (buildar o projeto e rodar os testes unitários).</p>
<p id="tecno" align="center">2º - mvn org.pitest:pitest-maven:mutationCoverage (para rodar os testes de mutação. Para 
conferi-los, em target -> pit-reports -> index.html).</p>
<p id="tecno" align="center">3º - java -jar target/*.jar (Rodar comando no root do projeto, afim de que o mesmo possa subir e executar).</p>

## Endpoints
<p id="tecno" align="center">No root do projeto, existe uma collection postman com todos os endpoints disponíveis na aplicação.</p>