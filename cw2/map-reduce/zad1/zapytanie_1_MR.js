
db.people.mapReduce(
        function () { 
            emit(this.sex, {
                weight: parseFloat(this.weight),
                height: parseFloat(this.height),
            })
        },
        function (sex, values) {
            const sumCount = (values, getter) => 
                values.map(getter).reduce(
                    ({sum, count}, el) => ({
                        sum: sum + el,
                        count: count + 1
                    }),
                    {sum: 0, count: 0}
                );
                
            return {
                weights: sumCount(values, el => el.weight),
                heights: sumCount(values, el => el.height),
            };
        },
        {
            out: 'zad1',
            finalize: function (sex, {weights, heights}) {
                const avg = ({sum, count}) => sum / count;
                return {
                    avgWeight: avg(weights),
                    avgHeight: avg(heights),
                };
            }
        }
    )

printjson(
    db.zad1.find().toArray()
)

