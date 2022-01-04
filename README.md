# ControledeVacinas

# Introdução

Com o caos gerado pela pandemia da COVID-19 e a procura da população pelas vacinas, notamos que em UBS (Unidade Básica de Saúde), os funcionários dessas unidades têm uma enorme dificuldade em fazer a gestão das vacinas, pois precisam fazer as anotações em cadernos para poder realizar todo uma gestão de qual vacina um cidadão tomou, os dados básicos para um cadastro de cada cidadão, o estoque de vacinas, gerando assim, uma demora ainda maior e filas.
Visando uma melhora para esses profissionais, desenvolveremos uma API para automatizar todas esse procedimento anterior a aplicação das vacinas.

## Problema

Com a alta procura de pessoas pela vacinação durante a pandemia de COVID-19, notou-se que, nas UBS os funcionários precisam de algumas informações antes de aplicar a vacina nas pessoas, como Ex.: Nome da pessoa, CPF/SUS, data de nascimento, nome da vacina, lote, data de fabricação da vacina, entre outras informações. E essas informações são recolhidas para cada pessoa e são anotadas em um caderno, como uma forma de fazer a gestão. 
A falta de um sistema que auxilie os profissionais e que seja integrado ao sistema de consultas do SUS, gera uma espera por parte da população na hora das vacinas serem aplicadas.

## Justificativa

A necessidade de se agilizar o atendimento e entregar um sistema que auxilie os profissionais para que os dados sejam mais precisos e que eles consigam fazer a gestão das vacinas.
## Público-Alvo

O nosso público alvo são profissionais de saúde que trabalham nas salas de vacinação de Unidades Básica de Saúde espalhadas pelo Brasil.

## Requisitos

As tabelas que se seguem apresentam os requisitos funcionais e não funcionais que detalham o escopo do projeto.

### Requisitos Funcionais

|ID    | Descrição do Requisito  | Prioridade |
|------|-----------------------------------------|----|
|RF-001| A API deve cadastrar, novas pessoas na base de dados. | ALTA |
|RF-002| A API deve deletar uma determinada pessoa da base de dados.  | ALTA |
|RF-003| A API deve atualizar as informações de uma determinada pessoa na base de dados. | ALTA 
|RF-004| A API deve ser capaz de identificar em qual UBS uma pessoa tomou uma vacina. | ALTA |
|RF-005| A API deve ser capaz de identificar qual vacina uma pessoa tomou. | ALTA |
|RF-006| A API deve listar cidadãos com base em filtros por “nome” e “idade”. | ALTA |
|RF-007| A API deve fazer login/autenticação dos usuários no sistema de acordo com seu perfil. | ALTA |

### Requisitos não Funcionais

|ID     | Descrição do Requisito  |Prioridade |
|-------|-------------------------|--
|RNF-001| Requisições precisam ser retornadas com sucesso ou falha. | ALTA | 
|RNF-002| Utilizar boas práticas na construção da API.|  ALTA |
