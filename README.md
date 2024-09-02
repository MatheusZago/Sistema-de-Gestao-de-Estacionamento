Siga os passos abaixo para configurar o projeto no seu ambiente

Clone o repositório
git clone https://github.com/MatheusZago/Sistema-de-Gestao-de-Estacionamento.git
Compile o projeto
cd Sistema-de-Gestao-de-Estacionamento
javac -d bin src/**/*.java
Execute o projeto Em um banco de dados mysql abra os arquivos da pasta scripts-mysql e execute-os da seguinte maneira:
sql createdatabase and enrollees table.sql - Execute a criação da database e da tablea. sql registers.sql - Execute a criação da tabela. sql ticket.sql - Execute a criação da tabela. sql vehicle.sql - Execute a criação da tabela. sql vehicle.sql - Execute a criação da tabela. sql parkingslots.sql - O arquivo serve mais para ajuda de consulta.

Para inicializar a tabela parkingslots com todos os espaços desejados acesse

src/application/App2: E execute o metodo CreateTable.
Ele irá criar uma tabela vazia.

Para executar o projeto, use o comando:

java -cp bin application.App
Estrutura do Projeto
src/application/java: Onde o código main do projeto esta.
src/db/java: Onde estão as classes para instanciamento e uso do Banco de Dados.
' scripts-mysql : Pasta onde estão todos os scripts para criação e verificação do banco de dados e tabelas. Arquivo README.md 3
db.properties: Documento com as informações do banco de dados.
Contribuindo
Se você quiser contribuir para este projeto, siga as etapas a

Fork o repositório.
Crie uma branch para suas alterações (`git checkout -b min
Faça as alterações e commit (`git commit -am 'Adiciona nova
Push para a branch (git push origin minha-nova-feature).
Abra um Pull Request.
Contato
Para dúvidas ou problemas, entre em contato com:

Nome: Matheus Lui Zago
Email: matheuszago134@gmail.com
GitHub: github.com/MatheusZago