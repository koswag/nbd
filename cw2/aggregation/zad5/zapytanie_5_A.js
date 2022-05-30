const pipeline = [
    {$match: {sex: 'Female', nationality: 'Poland'}},
    {$project: {'credit.currency': 1, 'credit.balance': 1}},
    {$unwind: '$credit'},
    {$replaceRoot: {newRoot: '$credit'}},
    
    {$project: {currency: 1, balance: {$toDouble: '$balance'}}},
    {
        $group: {
            _id: '$currency', 
            avgBalance: {$avg: '$balance'}, 
            sumBalance: {$sum: '$balance'}
        }
    },
    
    {$project: {currency: '$_id', avgBalance: 1, sumBalance: 1, _id: 0}}
]

printjson(
    db.people.aggregate(pipeline).toArray()
)

