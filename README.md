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

### `cadastrarGerente ( nome : String, cpf : String, senha : String, restauranteCnpj : String, restauranteNome : String, NegociosRestaurante : negocioRestaurante ) : void`

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

### `getRestaurante ( cpfDoGerente : String ) : Restaurante`

Verifica se o gerente com o referido CPF já existe e:

* se o gerente ainda não existe, lança uma `UsuarioNaoEncontradoException()`
* caso contrário, pega o usuário com tal CPF e retorna seu respectivo restaurante

## NegociosRestaurante

### `restauranteExiste ( cnpj : String ) : boolean`

Verifica se o restaurante existe, retornando `true` ou `false`.

### `cadastrarRestaurante ( cnpj : String, nome : String ) : void`

Verifica se o restaurante já existe:

* Se o restaurante existir no repositório, lança `JaExisteException`
* Caso contrário, tenta cadastrar o restaurante
* Se der alguma Exception, lança Exception

### `inserirProdutoNoCardapio ( restaurante : Restaurante, nome : String, valor : double, categoria : String, descricao : String ) : void`

Verifica se o restaurante existe:

* Se não existir, lança `NaoEncontradoException`
* Se existir, verifica se o item já existe no cardápio
* Se o item já existe no cardápio, lança uma `JaExisteException`
* Se ainda não existir, tenta inserir o item e caso dê algum erro, lança Exception

### `removerProdutoDoCardapio ( restaurante : Restaurante, itemID : int )  : void`

Mesma coisa do `inserirProdutoNoCardapio`, só que pra **remover** 😛

### `restaurantesAbertos () : ArrayList< Restaurante >`

Se existir mais de 1 restaurante cadastrado, vai retornar uma `ArrayList` com os restaurantes abertos no momento.

Se não existir nenhum restaurante cadastrado, retorna `null`

### `getItensDoCardapio ( cnpjRestaurante : String ) : Hashtable < String, ArrayList < Item > >`

Verifica se o restaurante existe e:

* Se não existir, lança `NaoEncontradoException`
* Se existir, retorna os itens agrupados por categoria em uma `Hashtable< categorias : String, itensDaCategoria : ArrayList < Item > >`

A ideia de ter esse método é para quando o cliente abrir um restaurante para visualizar o cardápio, a gente pegar essa Hashtable e exibir na tela os itens agrupados por categorias, tipo no iFood.

### `getCategorias ( cnpjRestaurante : String ) : ArrayList < String >`

Verifica se o restaurante existe e:

* Se não existir, lança `NaoEncontradoException`
* Se existir, retorna as categorias únicas no cardápio do restaurante