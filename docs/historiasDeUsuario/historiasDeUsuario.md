Nome do sistema: Biblioteca
Nome da história: Realizar autenticação no sistema
Como: Administrador do sistema 
Eu quero: Logar no sistema, cadastrar, editar, deletar e visualizar os usuários do sistema
Para: que todos os usuários possam realizar login

Cenário 1: realizar login  no sistema
Dado: que eu esteja cadastrado no sistema na função de administrador 
Quando: eu acessar a tela de login
E: informar meu username e senha
E: clicar em logar
Então: o sistema deve disponibilizar a tela inicial do sistema

Cenário 2: realizar cadastro de um novo usuário
Dado: que eu esteja autenticado no sistema como usuário administrador 
E: esteja na página de cadastro de usuário 
Quando: eu inserir os dados do novo usuário
E: clicar em salvar
Então: o sistema deve cadastrar esse novo usuário

Cenário 3: realizar a edição do cadastro de um usuário
Dado: que eu esteja autenticado no sistema como usuário administrador 
E: esteja na página de edição de usuário
Quando: eu inserir os novos dados da entidade
E: clicar em salvar
Então: o sistema deve atualizar o cadastro desse usuário

Cenário 4: realizar a remoção do cadastro de um usuário
Dado: que eu esteja autenticado no sistema como usuário administrador 
E: esteja na página de remoção de usuário
Quando: eu clicar no botão de deletar
E: clicar em salvar
Então: o sistema deve remover o cadastro desse usuário

Cenário 5: realizar a visualização do cadastro de um usuário
Dado: que eu esteja autenticado no sistema como usuário administrador 
E: esteja na página de visualização de usuário
Quando: eu inserir o usuário desejado
Então: o sistema deve listar o cadastro desse usuário
