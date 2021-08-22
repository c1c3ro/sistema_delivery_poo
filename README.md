# Sistema de Delivery - POO

## Documentação

Vamos colocar aqui a documentação do projeto que estamos fazendo para facilitar a integração entre nossas partes.

**Implementados até agora, temos**

### UsuarioAbstrato

- Atributos: `private String: nome`, `private String: senha`
- Parâmetros de inicialização: `String: nome`, `String: senha`
- É chamada pelas classes `Cliente` e `Gerente` que são suas herdeiras

##### atualizarNome: void (`String: nomeNovo`)

- Troca o nome do usuário para `nomeNovo`

##### atualizarSenha: void (`String: senhaNova`)

- Troca a senha do usuário para `senhaNova`

### Cliente `extends UsuarioAbstrato`

- Atributos: `private String: cpf`, `private String: endereco`, `private double: carteira`, `private Sacola: sacola`
- Parâmetros de inicialização: `String: nome`, `String: cpf`, `String: senha`, `String: endereco`
- Exemplo: `new Cliente("João das Neves", "12345678911", "000000", "Rua X Nº 0 Bairro Y")`