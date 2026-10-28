# API do projeto de finanças pessoais
API de um projeto pessoal de finanças pessoais, para uso próprio. Desenvolvido em prol de uma necessidade pessoal minha de ter um controle e noção dos meus gastos mensais. 

## Anexos:
<img width="1916" height="1022" alt="image" src="https://github.com/user-attachments/assets/4a57e060-44b4-4f29-b9d7-77b1576d30a5" />
<img width="1913" height="1017" alt="image" src="https://github.com/user-attachments/assets/ebd38fb4-ad1b-42a7-931b-e8ba97faaa78" />
<img width="1910" height="1020" alt="image" src="https://github.com/user-attachments/assets/1b75e908-e91f-40d9-a951-ada0a2cb9cfe" />
<img width="1915" height="1017" alt="image" src="https://github.com/user-attachments/assets/e3242e2c-ff6e-4708-88f3-966fd28b58cb" />

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

