# oxe-banking-investiments

If add new dependencies, run:
sbt compile

Run project:
sbt run

# Endpoints disponíveis

 ## Investimentos
 
| Método | URL                                               |             Descrição                    |     Paramêtros       |    
|--------|---------------------------------------------------|------------------------------------------|----------------------|
| GET    | http://localhost:8080/investment/types            | Obter todos os tipos de investimentos    | Nenhum               |


#### Retorno (um array de objetos):
| Id | name      |         
|----|-----------|
| 1  | Poupança  | 
| 2  | CDB       | 
| 3  | LCI       | 


 ## Simular Investimentos

| Método | URL                                               |             Descrição                    |     Paramêtros       |
|--------|---------------------------------------------------|------------------------------------------|----------------------|
| POST   | http://localhost:8080/investment/simulation       | Simular investimento                     |       Abaixo         |

### Paramêtros:
#### investmentAmount - Valor a investir (tipo double)
#### period - Tempo em meses (tipo int)
#### investmentType - Tipo de investimento (Tipo int. ID entre 1 a 3 conforme mostrado acima em investimentos)

Enviar para o endpoint um objeto como este:
{
    "investmentAmount": 1000,
    "period": 1,
    "investmentType": 2
}

#### Retorno (um objeto):
| investmentType                  | earnings              |  initial amount      | períod         |           
|---------------------------------|-----------------------|----------------------|----------------|
| Tipo de investimento escolhido  | Valor de ganho obtido | Valor investido      | Tempo em meses |

Retorna um objeto como este:
{
    "investmentType": "Poupança",
    "earnings": "60.00",
    "initial amount": "1000.00",
    "period": 12
}

