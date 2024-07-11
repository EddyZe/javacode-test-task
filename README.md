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
  amount: "Decimal"
}
````
И выполняет логику изменения счета в БД.
И возвращает ответ в виде json:

````json5
{
    "status": "OK",
    "newBalance": "Decimal",
    "timestamp": "timestamp"
}
````


Также есть возможность получить баланс кошелька
````http
GET api/v1/wallets/{WALLET_UUID}
````

Ответ приходи в виде json 
````json5
{
    "walletId": "UUID",
    "balance": "Decimal"
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