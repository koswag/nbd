
db.people.mapReduce(
    function () { 
        this.credit.forEach(
            ({currency, balance}) => emit(currency, parseFloat(balance))
        );
    },
    function (currency, balances) {
        const sumCount = values => 
            values.reduce(
                ({sum, count}, el) => ({
                    sum: sum + el,
                    count: count + 1
                }),
                {sum: 0, count: 0}
            );
            
        return sumCount(balances);
    },
    {
        out: 'zad5',
        query: {nationality: 'Poland', sex: 'Female'},
        finalize: function (nationality, {sum, count}) {
            return {
                totalBalance: sum,
                avgBalance: sum / count,
            }
        }
    }
)

printjson(
    db.zad5.find().toArray()
)

