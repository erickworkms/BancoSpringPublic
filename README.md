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



/////////////////////////////////////////////////////////////////////////////




Template created to demonstrate the usage of Spring Boot 3

The sole purpose of this repository is to showcase content for a portfolio and publicly provide content that can be used by anyone interested in the technologies used. If you use this template, at least credit that you used content from this repository.

I hope this repository is useful for understanding and implementing applications with Spring Boot, as well as Spring Security, dependency injection, Swagger, Docker, and other common elements in this type of application. Additionally, there is the possibility of using React + Bootstrap to create a coherent front-end. In the end, you'll have an application ready for deployment on AWS or any other cloud platform.

Some basic tips for usage:

Regarding the back-end:
Before anything else, add the properties in the application.properties file for accessing the database. In this project, MySQL was used, but it is easily adjustable to any database by specifying the driver in "spring.datasource.driver-class-name" for the chosen database. Inside the application.properties, you will find properties like the one below, where you have to use your own database, which can be local or even in the cloud, such as AWS.

spring.datasource.url=jdbc:mysql://{database}:3306/bancospring?createDatabaseIfNotExist=true&autoReconnect=true&allowPublicKeyRetrieval=true&useSSL=false
spring.datasource.username=username
spring.datasource.password=password

Regarding deploying the project on AWS, remember to install the AWS Toolkit, create an AWS account, and follow Amazon's recommendations for using services like RDS, S3, Lambda, etc. These services will allow you to create a database, store data, run REST applications, etc. Everything done here follows Amazon's tutorials and documentation using SAM and SAM CLI, which facilitate the integration and creation of these services in Visual Studio and IntelliJ IDEA.
Also, the StreamLambdaHandler class is responsible for executing in a Lambda function and starting the Spring application in the AWS cloud. If you are interested in running your application in the cloud, this class is crucial for that. In the example below, it is indicated where to put the class that starts your Spring Boot application for AWS.

handler = SpringBootLambdaContainerHandler.getAwsProxyHandler({Your class that starts the project here}.class);

Remember that before any deployment using SAM CLI, have the template.yaml file for your project, as it will configure and specify what your project needs to work in the AWS cloud. It creates buckets, permissions, and other things that would be very bureaucratic to do manually.

If you want to create new pages and addresses, remember to look for the controller that manages access to pages and add the new page, or the filter will block any access.

Regarding the front-end:

You can start your back-end in two ways: one is to start only the React project in the UI folder in SRC using the npm start command in the project folder in the command line, which will start the site at localhost:3000. The other way is to start alongside Spring Boot by running the main Spring class at localhost:8080. However, remember to compile with Maven using mvn clean install to generate new HTML and CSS files, or any visual changes will not be updated.

If you have issues with Bootstrap in the cloud, it is likely related to the MIME type of files and how the cloud server reads your CSS. You may have to set manually on your server where your project is hosted any CSS file with Content-type = text/css. If you upload your application as a Docker image, you may not be able to make this adjustment manually as it is closed in a package. In that case, the best approach is to package your Spring application separately from your React application and upload your site to a static service where you have this configuration control. However, you will have to configure all the post and get of your site to the paths of your back-end server since React is running separately from your Spring Boot project.

I hope these instructions are helpful. Thank you.



/////////////////////////////////////////


Spring Boot 3の使用法をデモンストレーションするために作成されたテンプレート

このリポジトリの唯一の目的は、ポートフォリオのコンテンツを紹介し、使用された技術に興味を持つ誰でもが利用できるコンテンツを公開することです。このテンプレートを使用する場合は、少なくともこのリポジトリのコンテンツを使用したことをクレジットしてください。

このリポジトリがSpring Boot、Spring Security、依存性の注入、Swagger、Dockerなど、この種のアプリケーションに共通する要素の理解と実装に役立つことを期待しています。さらに、React + Bootstrapを使用して一貫したフロントエンドを作成する可能性があります。最終的には、AWSやその他のクラウドプラットフォームでのデプロイに適したアプリケーションが完成します。

使用の基本的なヒント：

バックエンドに関して：
まず最初に、データベースへのアクセスのためのapplication.propertiesファイルにプロパティを追加します。このプロジェクトではMySQLが使用されましたが、選択したデータベースのドライバを「spring.datasource.driver-class-name」で指定することで、任意のデータベースに簡単に調整できます。application.properties内には以下のようなプロパティがあります。ここでは、独自のデータベースを使用する必要があり、それはローカルまたはAWSのようなクラウド上でも可能です。

spring.datasource.url=jdbc:mysql://{データベース}:3306/bancospring?createDatabaseIfNotExist=true&autoReconnect=true&allowPublicKeyRetrieval=true&useSSL=false
spring.datasource.username=ユーザー名
spring.datasource.password=パスワード

AWSでプロジェクトをデプロイする場合は、AWS Toolkitをインストールし、AWSアカウントを作成し、RDS、S3、Lambdaなどのサービスの使用に関するAmazonの推奨事項に従ってください。これらのサービスにより、データベースの作成、データの保存、RESTアプリケーションの実行などが可能になります。ここで行われているすべては、Visual StudioとIntelliJ IDEAでこれらのサービスの統合と作成を容易にするSAM

