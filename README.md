# API do projeto de finanças pessoais
API de um projeto pessoal de finanças pessoais, para uso próprio. Desenvolvido em prol de uma necessidade pessoal minha de ter um controle e noção dos meus gastos mensais. 

## Funcionalidades:
- Cadastro de despesas (nome, categoria(já pré-definida pelo enum), valor, método de pagamento(já pré-definido pelo enum), data);
- Editar e excluir despesa;
- Fechar mês (ter a opção de fechar o mês e, assim que fechado, não é mais possível adicionar uma despesa com a data que condiz com esse mês);
- Reabrir mês (caso o usuário queira mudar algo em um mês que já foi fechado, ele consegue, entretanto, apenas o último mês que foi fechado);
- Relatórios de despesa;
- Acessar o histórico de cada mês;

## Tecnologias:
- Java
- Spring Boot
- PostgreSQL
- Spring Data JPA
- Lombok
- DevTools
- Spring Web

## Deploy e Banco de Dados:
Deploy feito no Render e Banco de Dados em nuvem por meio do Supabase.
