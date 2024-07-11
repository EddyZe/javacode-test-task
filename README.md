# Описание

<img src=https://img.shields.io/badge/Java-orange></img>
<img src=https://img.shields.io/badge/Spring--boot-green></img>
<img src=https://img.shields.io/badge/PostgreSQL-blue></img>
<img src=https://img.shields.io/badge/lombok-red></img>

Приложение принимает запрос на адрес:
````http
POST api/v1/wallet
````

Принимает json: 


````json5
{
  valletId: "UUID",
  operationType: "DEPOSIT or WITHDRAW",
  amount: "BigDecimal"
}
````
И выполняет логику изменения счета в БД.


Также есть возможность получить баланс кошелька
````http
GET api/v1/wallets/{WALLET_UUID}
````
Возвращает ответ в виде json: 

````json5
{
    "status": "OK",
    "newBalance": "BigDecimal",
    "timestamp": "timestamp"
}
````

### Компиляция и запуск

___1. Компиляция___
```
mvn clean install
```

___2. Запуск___

````
docker-compose up
````