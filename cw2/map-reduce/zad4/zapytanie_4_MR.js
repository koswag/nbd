
db.people.mapReduce(
    function () {
        const square = x => x * x;
    
        const weight = parseFloat(this.weight);
        const height = parseFloat(this.height) / 100;
        
        const bmi = weight / square(height);
        emit(this.nationality, bmi);
    },
    function (nationality, bmis) {
        const sumCount = values => 
            values.reduce(
                ({sum, count}, el) => ({
                    sum: sum + el,
                    count: count + 1
                }),
                {sum: 0, count: 0}
            );
        
        return {
            minBMI: Math.min(...bmis),
            maxBMI: Math.max(...bmis),
            sumCount: sumCount(bmis),
        };
    },
    {
        out: 'zad4',
        finalize: function (nationality, {minBMI, maxBMI, sumCount: {sum, count}}) {
            return {
                minBMI, 
                maxBMI,
                avgBMI: sum / count,
            };
        }
    }
)

printjson(
    db.zad4.find().toArray()
)

