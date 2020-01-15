# foreignexchange

Exchangeratesapi.io were used for foreign exchange rate

-Spring Boot
-Junit 
-Mockito
-H2 Memory Database


/rate (Request)
{
    "source": "USD",
    "target": "TRY"
}

(Returned Response)
{
    "exchangeRate": 5.8882591093
}


/conversion (Request)
{
    "sourceAmount": "5",
    "sourceCurrency": "USD",
    "targetCurrency": "TRY"
}

(Returned Response Saved to memory H2 database)
{
    "transactionId": 1,
    "amountInTargetCurrency": 29.4412955465,
    "date": "2020-01-15T00:17:01.397+0000"
}


/conversions (Request)
{
    "transactionId": "1"
}

(Returned Response)
[
    {
        "transactionId": 1,
        "amountInTargetCurrency": 29.4413,
        "date": "2020-01-15T00:17:01.397+0000"
    }
]


/conversions (Request)
{
    "date": "2020-01-15",
    "pageNumber": "1"
}

(Returned Response)
[
    {
        "transactionId": 1,
        "amountInTargetCurrency": 5.0,
        "date": "2020-01-15T00:15:55.042+0000"
    },
    {
        "transactionId": 2,
        "amountInTargetCurrency": 29.4413,
        "date": "2020-01-15T00:17:01.397+0000"
    },
    {
        "transactionId": 3,
        "amountInTargetCurrency": 3.8515,
        "date": "2020-01-15T00:37:58.448+0000"
    }
]

