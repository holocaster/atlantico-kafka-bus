# Atlantico Kafka-Bus

Projeto responsável por ler tópico do kafka e executar o envio de email

## Considerações

O projeto tem o objetivo de ler o tópico do kafka responsável por receber eventos de email. Como o exercício pede
para enviar o evento de email para o rabbitMQ, para melhor integração e testes, resolvi criar um listener do rabbitmq que simplesmente repassa o evento de email
para um tópico do kafka que seria feito via web.

O projeto usa o redis para pegar do cache os usuários que são administradores para envio de email.

## Pontos de melhoria

* Usar o spring mail para envio de email que não foi implementado. Coloquei somente um log
* O objeto de evento, assim como o repository/POJO que acessa o redis para pegar o cache poderia ficar
em um projeto separado, já que serviria para todos os micro serviços
  
* Implementar testes da aplicação
  
## Rodando o projeto

* O projeto pode ser rodado de maneira local usando o docker-compose através do arquivo 
  [docker](https://github.com/holocaster/atlantico-user-api/blob/master/src/main/docker/docker-compose.yml)
