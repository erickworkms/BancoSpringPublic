# BancoSpringPublic
 Templete criado para demonstrar o uso do Spring boot 3

O unico objeto deste repository é demonstrar conteúdo para portfolio e disponibilizar publicamente um conteúdo que pode ser utilizado por qualquer pessoal interessada nas tecnologias utilizadas.
Caso utilize este templete, ao menos credite que utilizou conteúdo deste repository.
Espero que este repository seja util para o entendimento e aplicações com o spring boot, assim como o spring security, injeção de dependência,swagger,docker e outras coisas que são comuns neste tipo de aplicação, além da possibilidade de utilizar em conjunto o react + bootstrap para criação de um front-end coerente, e no fim ter uma aplicação pronta para deploy na aws ou qualquer outra plataforma cloud.

Algumas dicas básicas para o uso:

Referente ao back-end:
Antes de qualquer coisa, adicione as propriedades no application.properties os dados para acesso ao banco de dados, neste projeto foi utilizado o mysql, no entanto é facilmente ajustavel a qualquer banco de dados apenas determinando o driver no "spring.datasource.driver-class-name" referente ao banco de dados escolhido.
Dentro do application.properties irá encontrar propriedades como esta abaixo, na qual você terá que utilizar os sua propria base de dados, podendo ser local ou mesmo na cloud como na Aws.

spring.datasource.url=jdbc:mysql://{banco de dados}:3306/bancospring?createDatabaseIfNotExist=true&autoReconnect=true&allowPublicKeyRetrieval=true&useSSL=false
spring.datasource.username=usuario
spring.datasource.password=senha

Referente a subir o projeto na Aws, lembre-se de instalar o Aws toolkit, criar uma conta na aws, e seguir as recomendações da amazon para a utilização dos serviços como RDS,S3,Lambda etc, que vão permitir a criação de um banco de dados, armazenar dados, executar aplicações Rest etc. Tudo que foi feito aqui seguiu os tutoriais e documentação da amazon utilizando a SAM e SAM CLI que facilitam a integração e criação desses serviços no visual studio e Intellij Idea.
Além disso, a classe StreamLambdaHandler, é responsável por executar em uma função lambda e iniciar a aplicação spring na cloud da aws, caso tenha interesse em executar sua aplicação na cloud, essa classe é crucial para isso. Neste exemplo abaixo, esta indicado onde deve-se colocar a classe que inicia sua aplicação spring boot para a aws.

handler = SpringBootLambdaContainerHandler.getAwsProxyHandler({Sua classe que inicia o projeto aqui}.class);

Lembre-se que antes de qualquer deploy utilizando a SAM CLI, tenha o arquivo template.yaml do seu projeto, pois ele que irá configurar e dizer o que seu projeto precisa para funcionar na cloud da Aws, e criando buckets,permissões e outras coisas que seriam muito burocraticas de fazer manualmente.

Caso queira criar novas páginas e endereços, lembre-se de procurar o controller que gerencia o acesso a paginas e adicione a nova página, senão o filtro irá bloquear qualquer acesso.

Referente ao front-end:

É possível iniciar seu back-end de duas formas: Uma é iniciar apenas o projeto react que está na pasta UI em SRC utilizando o comando npm start na pasta do projeto na linha de comando, que irá iniciar o site em localhost:3000, e a outra forma é subir junto ao spring boot executando a classe principal do spring em localhost:8080, no entanto lembre de compilar no maven utilizando mvn clean install para gerar os novos arquivos html e css, senão qualquer alteração visual não será atualizada.

Caso tenha problemas com o bootstrap na nuvem, provavelmente terá relação ao tipo MIME de arquivos e como o servidor da nuvem lê o seu css, e terá que setar manualmente no seu server onde está hospedado o seu projeto qualquer arquivo css com o Content-type = text/css. Caso faça o upload da sua aplicação como imagem do docker, pode ser que não seja possivel fazer este ajuste manualmente por estar fechado em um pacote, caso isto ocorra, o melhor a se fazer é empacotar sua aplicação spring separada da sua aplicação react, e subir seu site em um serviço estático na qual terá este controle de configuração. No entanto terá que configurar todos os post e get do seu site para os caminhos do seu server back-end já que o react está subindo separadamente do seu projeto spring boot.

Espero que essas instruções sejam de alguma ajuda, obrigado.
