# Documentação

Vou mudar o que tava antes e, em vez de fazer a documentação de todas as funções, vou fazer só dos negócios. Porque são as camadas que cada um tá trabalhando que são mais próximas uma das outras: **negócios** e **fachada**.

**Implementados até agora, temos**

## NegociosCliente

### `clienteExiste ( cpf : String ) : boolean`

Retorna `true` se o cliente com o referido CPF já está cadastrado no nosso repositório e `false` caso contrário.

### `cadastrarCliente ( nome : String, cpf : String, senha : String, endereco : String ) : void`

Verifica se o cliente com o referido CPF já existe e: 

* se o cliente já existe, lança uma `ClienteJaExisteException()`
* caso contrário, tenta realizar o cadastro
* se der erro no cadastro, lança a Exception

### `matchLoginSenha ( cpf : String, senha : String ) : boolean`

Verifica se o cliente com o referido CPF já existe e:

* se o cliente ainda não existe, lança uma `UsuarioNaoEncontradoException()`
* caso contrário, pega o usuário com tal CPF do repositório e verifica a sua senha registrada
* se a senha registrada e a senha passada para a função forem as mesmas, retorna `true`
* caso contrário, retorna `false`

## NegociosGerente

### `gerenteExiste ( cpf : String ) : boolean`

Retorna `true` se o gerente com o referido CPF já está cadastrado no nosso repositório e `false` caso contrário.

### `cadastrarGerente ( nome : String, cpf : String, senha : String, restauranteCnpj : String, restauranteNome : String, repositorioRestaurante : RepositorioRestaurantes ) : void`

Verifica se o gerente com o referido CPF já existe e: 

* se o gerente já existe, lança uma `JaExisteException()`
* caso contrário, tenta realizar o cadastro do gerente e seu respectivo restaurante
* se der erro no cadastro, lança a Exception de erro no cadastro

### `matchLoginSenha ( cpf : String, senha : String ) : boolean`

Verifica se o gerente com o referido CPF já existe e:

* se o gerente ainda não existe, lança uma `UsuarioNaoEncontradoException()`
* caso contrário, pega o usuário com tal CPF do repositório e verifica a sua senha registrada
* se a senha registrada e a senha passada para a função forem as mesmas, retorna `true`
* caso contrário, retorna `false`