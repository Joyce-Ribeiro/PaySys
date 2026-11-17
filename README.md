# PaySys — Payment System

Aplicativo Android para organizar cobranças entre **empresas** e seus **clientes**. A empresa vê a lista de clientes vinculados a ela; o cliente vê os pagamentos que tem com cada empresa (data, valor, status e frequência).

Projeto desenvolvido em 2021, em Java, no Android Studio.

## Funcionalidades

- Cadastro de **cliente** e de **empresa** (nome, número de contato e senha)
- Login separado para cliente e para empresa
- **Visão da empresa:** lista dos clientes vinculados a ela; ao tocar em um cliente, a empresa registra uma cobrança (valor, data inicial e frequência), que começa como pendente
- **Visão do cliente:** lista de pagamentos com a empresa, mostrando data, valor, status (pago ou pendente) e frequência
- Persistência local com **SQLite**, sem depender de servidor

## Tecnologias

- **Java 8** e **Android SDK** (minSdk 22, targetSdk 31)
- **SQLite** com `SQLiteOpenHelper`
- **AndroidX:** AppCompat, ConstraintLayout, RecyclerView, Navigation Component e View Binding
- **Material Components** (Toolbar, Floating Action Button, Snackbar)

## Arquitetura

O código separa interface, domínio e acesso a dados:

```
app/src/main/java/
├── com/example/ps/        telas (Activities e Fragments) e adapters das listas
├── dominio/entidade/      entidades: Pessoa, Cliente, Empresa, Pagamento, Adiciona, Registro
├── dominio/repositorio/   repositórios com as consultas SQL de cada entidade
└── database/              criação do banco (ScriptDDL) e conexão (PaymentSystemOpenHelper)
```

### Modelo de dados

| Tabela | Conteúdo |
|---|---|
| `CLIENTE` | id, nome, número e senha do cliente |
| `EMPRESA` | código, nome, número e senha da empresa |
| `ADICIONA` | vínculo N:N entre empresa e cliente |
| `PAGAMENTO` | data, valor, status, frequência e comprovante (PDF) de cada cobrança entre um cliente e uma empresa |

## Fluxo do app

1. A tela inicial tem um botão flutuante que leva à escolha entre **cadastrar** e **entrar**.
2. No cadastro, a pessoa escolhe se é cliente ou empresa.
3. Ao entrar como **empresa**, o app lista os clientes vinculados; tocar em um cliente abre o registro de uma nova cobrança.
4. Ao entrar como **cliente**, o app lista os pagamentos com a empresa.

## Como rodar

O repositório contém apenas o módulo `app/`. Para abrir no Android Studio:

1. Crie um projeto vazio no Android Studio (Java, minSdk 22).
2. Substitua a pasta `app/` do projeto novo pela deste repositório.
3. Sincronize o Gradle e rode em um emulador ou dispositivo com Android 5.1 ou superior.

## Status

Protótipo acadêmico. O envio do comprovante em PDF e a exclusão de vínculos ainda não estão concluídos, e as senhas são guardadas sem criptografia, por isso o app não deve ser usado com dados reais.
