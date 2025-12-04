# 🎓 Mini Sistema Acadêmico  
Sistema desenvolvido para o Trabalho Final da disciplina **Tópicos em Java Web (TJW)**.

Este projeto implementa um sistema web completo utilizando **Spring Boot**, **Spring MVC**, **Spring Security**, **Spring Data JPA**, **Thymeleaf** e **Bootstrap**, seguindo o padrão MVC e todas as regras do trabalho.

---

## 🚀 Tecnologias Utilizadas

- Java 17  
- Spring Boot 4  
- Spring MVC  
- Spring Data JPA (Hibernate)  
- Spring Security  
- Thymeleaf  
- Bootstrap 5  
- H2 Database  
- Maven  

---

## 🔐 Perfis de Usuário

O sistema possui dois perfis:

### 👑 Administrador (ADMIN)
- Acesso total  
- Gerencia Alunos, Disciplinas e Matrículas

### 📝 Secretaria (SECRETARIA)
- NÃO pode gerenciar Disciplinas  
- Gerencia Alunos e Matrículas  

---

## 🔑 Usuários Iniciais (DataLoader)

| Usuário     | Senha | Perfil            |
|-------------|--------|-------------------|
| admin       | 123    | ROLE_ADMIN        |
| secretaria  | 123    | ROLE_SECRETARIA   |

---

## 📦 Como Executar o Projeto

### ▶️ Via Eclipse/STS
1. Importe o projeto **Maven → Existing Project**  
2. Abra `TjwAcademicoApplication.java`  
3. Clique com o botão direito → **Run As → Java Application**  
4. Acesse: http://localhost:8080

### ▶️ Via terminal

mvn spring-boot:run


---

## 📚 Funcionalidades

### ✔️ Alunos
- Listar  
- Cadastrar  
- Editar  
- Excluir  
- Validações  
- Registro de quem criou

### ✔️ Disciplinas
- Acesso somente a ADMIN  
- CRUD completo (código, nome, semestre, carga horária)  

### ✔️ Matrículas
- Relaciona aluno + disciplina  
- Situações: CURSANDO, APROVADO, REPROVADO, TRANCADO  
- Nota Final  
- Data automática  

### 🔒 Regra de Negócio Implementada

> ❗ Um aluno **NÃO pode** ter duas matrículas **CURSANDO** para a mesma disciplina ao mesmo tempo.

O sistema bloqueia a duplicação na criação **e** na edição.

---

## 🗄️ Banco de Dados H2

Console disponível em: http://localhost:8080/h2-console


Configuração:
JDBC URL: jdbc:h2:mem:tjwdb
Username: sa
Password: (vazio)

---

## 🖼️ Interface (Bootstrap)

O sistema utiliza **Bootstrap 5** para estilização:

- Navbar dinâmica por perfil (ADMIN/SECRETARIA)  
- Tabelas modernas  
- Formulários responsivos  
- Botões padronizados  

---

## 📁 Estrutura do Projeto

src/main/java.br.edu.ifce.tjw
├── config -> Spring Security + Thymeleaf Dialect
├── controller -> Controladores MVC
├── model -> Entidades JPA
├── repository -> Interfaces JPA
├── service -> Regras de negócio / UserDetailsService
└── TjwAcademicoApplication.java

src/main/resources
├── templates -> HTML Thymeleaf
│ ├── alunos
│ ├── disciplinas
│ ├── matriculas
│ └── login.html / index.html
└── application.properties


---

## 🎥 Vídeo de Demonstração

A apresentação deve mostrar:

1. Login com ADMIN e SECRETARIA  
2. Navbar dinâmica por perfil  
3. CRUD de alunos  
4. CRUD de disciplinas  
5. CRUD de matrículas  
6. Regra de negócio funcionando (bloqueio CURSANDO)  
7. Segurança (403 quando SECRETARIA tenta acessar disciplinas)  

---

## ✔️ Autor
GIOVANNE FERREIRA DE OLIVEIRA.

---






