INSERT INTO tb_conta(saldo, numero_Conta, exclusive) VALUES(100.0, '101084-20', TRUE);
INSERT INTO tb_conta(saldo, numero_Conta, exclusive) VALUES(50.0, '202084-40', FALSE);
INSERT INTO tb_conta(saldo, numero_Conta, exclusive) VALUES(1000.00, '303024-84', FALSE);
INSERT INTO tb_conta(saldo, numero_Conta, exclusive) VALUES(10.00, '2510-25', TRUE);

INSERT INTO tb_extrato(data_Extrato, descricao_Extrato, id_conta) VALUES('2023-03-22', 'valor Deposito: 100.0 - 22-03-2023', 1);
INSERT INTO tb_extrato(data_Extrato, descricao_Extrato, id_conta) VALUES('2023-03-22', 'valor saque: 50 - 22-03-2023', 1);
INSERT INTO tb_extrato(data_Extrato, descricao_Extrato, id_conta) VALUES('2023-03-22', 'valor Deposito: 50.0 - 22-03-2023', 2);
INSERT INTO tb_extrato(data_Extrato, descricao_Extrato, id_conta) VALUES('2023-03-22', 'valor Deposito: 1000.0 - 22-03-2023', 3);
INSERT INTO tb_extrato(data_Extrato, descricao_Extrato, id_conta) VALUES('2023-03-22', 'valor Deposito: 10.0 - 22-03-2023', 4);


INSERT INTO tb_cliente(nome, data_Nascimento, conta_id)  VALUES('EVERTON DE ALMEIDA', '1996-01-07', 1);
INSERT INTO tb_cliente(nome, data_Nascimento, conta_id)  VALUES('CAROLINE MALAGUTTE', '1985-03-10', 2);
INSERT INTO tb_cliente(nome, data_Nascimento, conta_id)  VALUES('JOÃO DA SILVA', '2002-12-25', 3);
INSERT INTO tb_cliente(nome, data_Nascimento, conta_id)  VALUES('MATEUS OLIVEIRA', '1997-07-14', 4);