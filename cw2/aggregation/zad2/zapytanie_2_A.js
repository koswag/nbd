const pipeline = [
    {$project: {'credit.currency': 1, 'credit.balance': 1}},
    {$unwind: '$credit'},
    {$replaceRoot: {newRoot: '$credit'}},
    
    {$project: {currency: 1, balance: {$toDouble: '$balance'}}},
    {$group: {_id: '$currency', balanceSum: {$sum: '$balance'}}},
    
    {$project: {currency: '$_id', balanceSum: 1, _id: 0}}
]

printjson(
    db.people.aggregate(pipeline).toArray()
)

