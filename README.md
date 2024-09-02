## Sistema-de-Gestão-de-Estacionamento

Certifique-se de ter as seguintes ferramentas instaladas:
- Java Development Kit (JDK) 11 ou superior
- MySql ver 8.0 ou superior.

Dependências
- Mysql-Connector-j-9.0.0
  
## Instalação
Siga os passos abaixo para configurar o projeto no seu ambiente

1. **Clone o repositório**
Em um respositório git execute o seguinte comando:

1) Crie uma pasta para conter o projeto.
2) Abra o gitbash na pasta desejada
3) Digite: 
   
 ```bash
 git clone https://github.com/MatheusZago/Sistema-de-Gestao-de-Estacionamento.git
 ```
 
2. **Importe o projeto**

1) Abra sua IDE de escolha (Recomendado STS ou Eclipse)  no workspace do projeto clonado
2) Importe o projeto como Existing Projects on Workspace.
3) Abra a opção de navegar, selecione o projeto e clique em finish.

3. **Adicione a biblioteca Mysql-Connector-j-9.0.0**

1) Acesse https://dev.mysql.com/downloads/connector/j/ ou pesquise por mysql java connector e entre no link mysql::
2) Selecione a opção de "Platform Independent" e faça o download. 3
3) Abra o arquivo zip, localize mysql-connector-j-9.0.0.jar e o extraia para uma pasta de fácil acesso.

4) Abra o projeto em sua IDE desejada, e acesse Propriedades > Java Build Path > Libraries
5) Selecione Class Path, e em seguida clique em Add external Jars.
6) Localize mysql-connector-j-9.0.0.jar e o abra no classPath.
7) Por fim, clique em "apply" e "apply on close"
 
 3. **Execute os SQL**
Em uma conexão mysql abra os arquivos da pasta scripts-mysql e execute-os da seguinte maneira:
(Cada arquivo também tem um select para consulta em tempo real)

1) sql createdatabase and enrollees table.sql - Execute a criação da database e da tablea.
2) sql registers.sql - Execute a criação da tabela.
3) sql ticket.sql - Execute a criação da tabela.
4) sql vehicle.sql - Execute a criação da tabela.
5) sql vehicle.sql - Execute a criação da tabela.
6) Para inicializar a tabela parkingslots com todos os espaços desejados acesse 
* `src/application/App2`: E execute o metodo CreateTable.
* 
sql parkingslots.sql - O arquivo serve mais para ajuda de consulta.


 
 4. **Atualize db.properties**

1)Em sua IDE no projeto acesse o arquivo db.properties.
1)Atualize o user caso a conexão não esteja usando root.
2)Atualize a senha de sua conexão. (a oferecida é de testes)
 
### Estrutura do Projeto
* `src/app`: Onde o código main do projeto esta.
* `src/app2`: Main secundário feito para testes e para iniciar a tabela ParkingSlots.
* `src/db`: Onde estão as classes para instanciamento e uso do Banco de Dados.
* `src/model.dao`: Onde as interfaces e DaFactory estão 
* `src/model.dao.impl`: Onde as interfaces são implementadas para JBDC.
* `src/model.entites`: Pacote onde as entidades do projeto estão.
* `src/model.enums`: Onde os enums do projeto estão.
* `src/model.services`: Onde os services estão.
* `db.properties`: Documento com as informações do banco de dados.

scripts-mysql : Pasta onde estão todos os scripts para criação e verificação do banco de dados e tabelas. 
Arquivo README.md 3

### Contribuindo
Se você quiser contribuir para este projeto, siga as etapas a
1. Fork o repositório.
2. Crie uma branch para suas alterações (`git checkout -b min
3. Faça as alterações e commit (`git commit -am 'Adiciona nova
4. Push para a branch (`git push origin minha-nova-feature`).
5. Abra um Pull Request.

### Contato
Para dúvidas ou problemas, entre em contato com:
* Nome: Matheus Lui Zago
* Email: matheuszago134@gmail.com
* GitHub: github.com/MatheusZago
