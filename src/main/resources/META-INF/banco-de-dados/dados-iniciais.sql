insert into produto (id, nome, preco, data_criacao, descricao) values (1, 'Kindle', 499.0, date_sub(sysdate(), interval 1 day), 'Conhe�a o novo Kindle, agora com ilumina��o embutida ajust�vel, que permite que voc� leia em ambientes abertos ou fechados, a qualquer hora do dia.');
insert into produto (id, nome, preco, data_criacao, descricao) values (3, 'C�mera GoPro Hero 7', 1400.0, date_sub(sysdate(), interval 1 day), 'Desempenho 2x melhor.');

insert into cliente (id, nome, cpf) values (1, 'Fernando Medeiros','111.222.333-44');
insert into cliente_detalhe (cliente_id, sexo) values (1, 'MASCULINO');

insert into cliente (id, nome, cpf) values (2, 'Marcos Mariano','555.666.777-88');
insert into cliente_detalhe (cliente_id, sexo) values (2, 'MASCULINO');


insert into pedido (id, cliente_id, data_criacao, total, status) values (1, 1,date_sub(sysdate(), interval 2 day), 998.0, 'AGUARDANDO');
insert into pedido (id, cliente_id, data_criacao, total, status) values (2, 1,date_sub(sysdate(), interval 2 day), 499.0, 'AGUARDANDO');

insert into item_pedido (pedido_id, produto_id, preco_produto, quantidade) values (1, 1, 499.0, 2);
insert into item_pedido (pedido_id, produto_id, preco_produto, quantidade) values (2, 1, 499.0, 1);

insert into pagamento (pedido_id, status, numero_cartao, codigo_barras,  tipo_pagamento) values (2, 'PROCESSANDO', '123', '', 'cartao');
--insert into pagamento_cartao (pedido_id, status, numero_cartao) values (2, 'PROCESSANDO', '123');
--insert into pagamento (pedido_id, status, tipo_pagamento) values (2, 'PROCESSANDO', 'cartao');
--insert into pagamento_cartao (pedido_id, numero_cartao) values (2, '123');

insert into categoria (id, nome) values (1, 'Eletrônicos');
insert into categoria (id, nome) values (2, 'Livros');

insert into produto_categoria (produto_id, categoria_id) values (1,2);