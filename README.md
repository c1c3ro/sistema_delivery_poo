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

### `adicionaDinheiro ( cpf : String, valor : double ) : double`

Verifica se o cliente com o referido CPF já existe e:

* se o cliente ainda não existe, lança uma `UsuarioNaoEncontradoException()`
* caso contrário, pega o usuário com tal CPF do repositório e adiciona o valor à carteira
* se der erro no processo, lança a Exception

### `realizarCompra ( cpf : String ) : double`

Verifica se o cliente com o referido CPF já existe e:

* se o cliente ainda não existe, lança uma `UsuarioNaoEncontradoException()`
* caso contrário, pega o usuário com tal CPF do repositório, verifica o total da sacola e retira da carteira
* se der erro nesse processo, retorna a Exception (pode ser uma genérica ou `SemDinheiroException`)
* se não, retorna o valor atual da carteira depois da compra

### `adicionarItemNaSacola ( cpf : String, item : Item, gerente : Gerente ) : void`

Verifica se o cliente com o referido CPF já existe e:

* se o cliente ainda não existe, lança uma `UsuarioNaoEncontradoException()`
* caso contrário, pega o usuário com tal CPF do repositório e tenta adicionar o item na sacola
* se der erro nesse processo, retorna a Exception 

### `removerItemDaSacola ( cpf : String ) : void`

Mesma coisa que o de **adicionar**, mas para **remover**.

### `esvaziarSacola ( cpf : String, item : Item, gerente : Gerente ) : void`

Mesma coisa que o de **remover**, mas remove tudo da sacola e reseta todos os valores da sacola.

### `pedidosAntigos ( cpf : String ) :  ArrayList< Sacola >`

Verifica se o cliente com o referido CPF já existe e:

* se o cliente ainda não existe, lança uma `UsuarioNaoEncontradoException()`
* Retorna todos os pedidos feitos pelo cliente até o momento
* Se der erro, lança uma Exceção

### `pedidoAtivoMaisRecente ( cpf: String ) : Sacola`

Mesma coisa que a `pedidosAntigos`, mas retorna só o pedido feito mais recentemente e que ainda está ativo, ou seja:

* Nenhum gerente autorizou os pedidos
* Falta o(s) gerente(s) de algum(ns) restaurante(s) autorizar algum(ns) pedido(s)
* Se retornar `null` é porque não existe nenhum pedido ativo

### `getStatusPedidos ( cpf: String ) : int`

Verifica se o cliente com o referido CPF já existe e:

* se o cliente ainda não existe, lança uma `UsuarioNaoEncontradoException()`
* caso contrário, tenta verificar o status dos seus pedidos
* se der erro no processo, lança a Exception
* se não, retorna algum destes valores:
    * 0 - Nenhum gerente autorizou os pedidos
    * 1 - Todos os gerentes autorizaram os pedidos
    * 2 - Alguns gerentes já autorizaram seus pedidos mas outros não

A ideia aqui é que o cliente pode adicionar itens de mais de um restaurante em sua sacola. Asism, os gerentes de todos os restaurantes precisam autorizar o pedido. O status do pedido mostra isso. Dá pra usar o método seguinte para ver os gerentes/restaurantes que autorizaram os pedidos ou não:

### `gerentesQueAutorizaram  ( cpf : String ) : Hashtable < Gerente, Integer >`

Verifica se o cliente com o referido CPF já existe e:

* se o cliente ainda não existe, lança uma `UsuarioNaoEncontradoException()`
* caso contrário, tenta verificar os gerentes que autorizaram o pedido da Sacola atual
* se der algum erro, lança a Exception
* caso contrário, retorna uma Hashtable com os gerentes e seu status de aprovação:
    * 1 - pedido aprovado pelo gerente
    * 0 - pedido não aprovado ainda

### `atualizarCliente( cpf : String, campo : int, novoValor : String) : void`

Verifica se o cliente existe:

* se não existir, lança `NaoEncontradoException`
* se existir, verifica o valor `campo : int` passado para o método:
    * 1: atualizar Nome
    * 2: atualizar Senha
    * 3: atualizar Endereço
* se `campo : int` não for nenhuma destas opções acima, lança Exception `OpcaoInvalida`
* caso contrário, atualiza o atributo correspondente com `novoValor : String`
* retorna o respectivo campo atualizado

### `getSacolaAtual( cpf : String ) : Sacola`

Verifica se o cliente existe:

* se não existir, lança `NaoEncontradoException`
* retorna o objeto `Sacola()` do cliente ou uma Exception caso dê algum erro

### `getItensNaSacola( sacola : Sacola ) : Hashtable < Restaurante, ArrayList< Item > >`

A ideia é utilizar o `getSacolaAtual()` ou `pedidosAntigos()` para pegar a(s) sacola(s) e aí passar ela(s) para esse método para pegar a lista de itens agrupada por restaurantes (na sacola pode ter itens de mais de um restaurante). O método funciona assim:

* verifica se a Sacola está vazia. Se sim, lança `SacolaVaziaException`
* se não, retorna uma `Hashtable` onde as chaves são os Restaurantes de onde os pedidos foram realizados, e os itens são uma `ArrayList` com os itens que foram comprados do respectivo restaurante.
* se der algum erro no processo, lança a Exception.

### `getTotalSacola( sacola : Sacola ) : double`

Mesma ideia aqui: utilizar o `getSacolaAtual()` ou `pedidosAntigos()` para pegar a(s) sacola(s) e aí passar ela(s) para esse método para pegar o valor total da sacola. Se a sacola estiver vazia, uma `SacolaVaziaException` vai ser lançada.

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

### `getCategorias ( cnpjRestaurante : String ) : ArrayList < String >`

Verifica se o restaurante existe e:

* Se não existir, lança `NaoEncontradoException`
* Se existir, retorna as categorias únicas no cardápio do restaurante

### `abrirRestaurante ( gerente : Gerente ) : void`

Abre o restaurante do gerente especificado

### `fecharRestaurante ( gerente : Gerente ) : void`

Fecha o restaurante do gerente especificado

### `aprovarPedido ( gerente : Gerente, sacola : Sacola ) : boolean`

Tenta aprovar o pedido referente ao gerente na sacola:

* Se ocorrer alguma exceção, lança ela;
* Se não encontrar o gerente, lança a exceção `NãoEncontradoException`
* Se não der nenhum problema, retorna `true` se o pedido foi aprovado com sucesso e `false` caso contrário

### `pesquisarGerentePorRestaurante ( restaurante : Restaurante ) : Gerente`

Tenta encontrar o gerente que corresponde ao restaurante passado. **A ideia é usar esse método para encontrar o gerente quando tu só tem o dado do restaurante.**

* Se der alguma exceção, lança ela
* Se retornar `null`, não encontrou nenhum gerente com o tal restaurante
* Se encontrar, retornar o objeto `Gerente()` do gerente do restaurante

### `atualizarGerente( cpf : String, campo : int, novoValor : String) : String`

Verifica se o gerente existe:

* se não existir, lança `NaoEncontradoException`
* se existir, verifica o valor `campo : int` passado para o método:
    * 1: atualizar Nome
    * 2: atualizar Senha
* se `campo : int` não for nenhuma destas opções acima, lança Exception `OpcaoInvalida`
* caso contrário, atualiza o atributo correspondente com `novoValor : String`.
* retorna o respectivo campo atualizado

## NegociosRestaurante

### `restauranteExiste ( cnpj : String ) : boolean`

Verifica se o restaurante existe, retornando `true` ou `false`.

### `cadastrarRestaurante ( cnpj : String, nome : String ) : void`

Verifica se o restaurante já existe:

* Se o restaurante existir no repositório, lança `JaExisteException`
* Caso contrário, tenta cadastrar o restaurante
* Se der alguma Exception, lança Exception

### `ganhos ( cnpj : String ) : double`

Verifica se o restaurante existe:

* Se não existir, lança `NaoEncontradoException`
* Caso contrário, retorna quanto o restaurante vendeu até o momento

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

### `getItensDoCardapio ( restaurante : Restaurante ) : Hashtable < Integer, Item >`

Verifica se o restaurante existe e:

* Se não existir, lança `NaoEncontradoException`
* Se existir, retorna os itens agrupados por categoria em uma `Hashtable< ID : Integer, Item : Item >`

A ideia de ter esse método é para quando o cliente abrir um restaurante para visualizar o cardápio, a gente pegar essa Hashtable e exibir na tela os itens agrupados por categorias, tipo no iFood.

### `getItemPorID ( restaurante : Restaurante, ID : int ) : Item`

Verifica se o restaurante existe e:

* Se não existir, lança `NaoEncontradoException`
* Se existir, pesquisa o ID do item no Cardápio do restaurante e retorna o item encontrado.
* Se não encontrar o item, lança Exception.