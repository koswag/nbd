const pipeline = [
    {$project: {sex: 1, height: {$toDouble: '$height'}, weight: {$toDouble: '$weight'}}},
    {
        $group: {
            _id: '$sex', 
            avgHeight: {$avg: '$height'}, 
            avgWeight: {$avg: '$weight'},
        }
    },
    {$project: {sex: '$_id', avgHeight: 1, avgWeight: 1, _id: 0}},
]

printjson(
    db.people.aggregate(pipeline).toArray()
)

