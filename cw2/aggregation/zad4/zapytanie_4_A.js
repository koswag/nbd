const pipeline = [
    {
        $project: {
            nationality: 1, 
            weight: {$toDouble: '$weight'}, 
            height: {$divide: [{$toDouble: '$height'}, 100]},
        }
    },
    {
        $project: {
            nationality: 1, 
            bmi: {$divide: ['$weight', {$multiply: ['$height', '$height']}]}
        }
    },
    {
        $group: {
            _id: '$nationality', 
            avgBMI: {$avg: '$bmi'},
            minBMI: {$min: '$bmi'},
            maxBMI: {$max: '$bmi'},
        }
    },
    {$project: {nationality: '$_id', avgBMI: 1, minBMI: 1, maxBMI: 1, _id: 0}}
]

printjson(
    db.people.aggregate(pipeline).toArray()
)

