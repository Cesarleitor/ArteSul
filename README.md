# 🏭 ArteSul - Sistema de Gestão de Pedidos

Sistema em desenvolvimento para gerenciamento de pedidos e catálogo de produtos da empresa **ArteSul**, focado em controle de produção, organização de vendas e rastreabilidade dos pedidos.

---

## 🚀 Tecnologias Utilizadas

* ☕ Java 17
* 🚀 Spring Boot
* 🌐 Spring Web
* 🗄️ Spring Data JPA
* 🔐 Spring Security *(em implementação)*
* 🐬 MySQL
* 🐳 Docker *(em breve)*
* ☁️ AWS *(em breve)*

---

## 📦 Funcionalidades

### 👥 Usuários

* Cadastro de usuários
* Perfis:

  * ADMIN
  * VENDEDOR

### 👕 Catálogo de Produtos

* Cadastro de peças
* Nome e tamanho dos produtos

### 🛒 Pedidos

* Criação de pedidos
* Informações:

  * Produto
  * Cor
  * Tamanho
  * Quantidade
  * Valor
* Associação com vendedor

---

## 🧠 Regras de Negócio

* Apenas administradores poderão cadastrar produtos
* Vendedores poderão criar pedidos
* Cada pedido será vinculado ao vendedor logado
* Validação de dados antes de persistir no banco

---

## 🔧 Estrutura do Projeto

```bash
src/main/java/com/empresa/pedidosystem

├── controller
├── service
├── repository
├── entity
├── dto
├── security (em desenvolvimento)
├── config
├── exception
```

---

## 🗄️ Banco de Dados

* MySQL
* Criação automática de tabelas via JPA

---

## ▶️ Como rodar o projeto

1. Clonar o repositório:

```bash
git clone https://github.com/seu-usuario/pedido-system.git
```

2. Configurar o banco no `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/pedido_system
spring.datasource.username=root
spring.datasource.password=SUA_SENHA
```

3. Rodar o projeto:

```bash
./mvnw spring-boot:run
```

4. Acessar:

```
http://localhost:8080
```

---

## 🧪 Testes

Testes unitários com JUnit *(em evolução)*

---

## 🔮 Melhorias Futuras

* 🔐 Autenticação com JWT (Spring Security)
* 👥 Controle de acesso por perfil (RBAC)
* 📄 Documentação com Swagger
* 🐳 Dockerização da aplicação
* ☁️ Deploy na AWS
* 🌐 Desenvolvimento de Front-End (React ou Angular)
* 📊 Dashboard de pedidos
* 📦 Controle de status de produção

---

## 📌 Status do Projeto

🚧 Em desenvolvimento

---

## 🤝 Contribuição

Projeto em desenvolvimento interno. Sugestões e melhorias são bem-vindas.

---

## 👨‍💻 Autor

Desenvolvido por Cesar 🚀
