
db.people.mapReduce(
    function () { 
        this.credit.forEach(
            ({currency, balance}) => emit(currency, parseFloat(balance))
        );
    },
    function (currency, balances) {
        return balances.reduce((a, b) => a + b, 0);
    },
    {out: 'zad2'}
)

printjson(
    db.zad2.find().toArray()
)

