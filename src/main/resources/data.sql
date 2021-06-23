INSERT INTO tb_uf (id, dsc_nome, dsc_sigla)
values(1, 'Minas Gerais', 'MG');

INSERT INTO tb_uf (id, dsc_nome, dsc_sigla)
values(2, 'São Paulo', 'SP');

INSERT INTO tb_cidade(id, dsc_nome, fk_uf)
values(1, 'Belo Horizonte', 1);

INSERT INTO tb_cidade(id, dsc_nome, fk_uf)
values(2, 'São Paulo', 2);

INSERT INTO tb_tipo_cliente(tipo_cliente, dsc_tipo_cliente)
values('PF', 'Pessoa Física');

INSERT INTO tb_tipo_cliente(tipo_cliente, dsc_tipo_cliente)
values('PJ', 'Pessoa Jurídica');