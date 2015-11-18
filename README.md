# ControlePessoal
######Sistema de Gerenciamento de Usuário

Este projeto foi desenvolvido com o objeto de analisar o conhecimento acerca das ferramentas e tecnologias utilizadas no Laboratório de Tecnologia da Informação e Mídias Educacionais (LABTIME) no ano de 2015/2.

### Desenvolvedor
Kélvin Santiago - [Acesse nosso site](http://www.kelvinsantiago.com.br)

### Módulos
* CRUD Usuário
  * Create
  * Read
  * Update
  * Delete

### Arquitetura Rest Java EE
![Webservice Rest](http://d2huq83j2o5dyd.cloudfront.net/spring-mvc-angular/SpringMVCAngular2.jpg)

O sistema foi desenvolvido utilizando a arquitetura Representational State Transfer (REST) para fornecer recursos no lado back-end. No lado front-end foi utilizado o Framework AngularJS para consumir os serviços da API Rest.
Tecnologias (Frameworks) utilizadas no sistema:
- Spring Rest
- JPA
- Json
- AngularJS
- Bootstrap
- Maven
- IDE Intellij
- Postgres

###  Design Responsive
O sistema se adapta ao browser do usuário sem precisar definir diversas folhas de estilos para cada resolução, então juntamente com o bootstrap, customizamos todo o sistema para adaptar aos diferentes tamanhos de tela.

### Instalação 
#### Back-end - Server-side
  * Faça o clone do projeto.
  * Instale o SGBD Postgres.
  * Defina o usuário 'postgres' com a senha 'postgres', ou altere no arquivo de configuração 'persistence.xml' do JPA o seu usuário do SGBD.
  * Crie um banco de dados com o nome 'controle_pessoal'.
  * Rode a aplicação na sua IDE com o TOMCAT7 utilizando o Maven.
  * Teste o serviço no navegador acessando http://localhost:9090/ControlePessoal/

#### Front-end - Client-side
  * Instale o Apache
  * Mova a pasta do seu projeto 'ClienteWeb' dentro do diretório 'webapp' para o diretório do seu servidor Apache, no linux fica em '/var/www/html/'.
  * Abra o arquivo 'UsuarioServico.js' dentro do diretório 'resources/js/service'.
  * Na variável URL_BASE, coloque a URL do seu servidor Webservice Rest, exemplo: "http://IP_DO_SERVIDOR_REST:9090/ControlePessoal/usuario/"
  * Acesse o sistema: http://localhost/ClienteWeb/app/


