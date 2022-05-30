const pipeline = [
    {$group: {_id: null, job: {$addToSet: '$job'}}},
    {$unwind: '$job'},
    {$project: {job: 1, _id: 0}}
]

printjson(
    db.people.aggregate(pipeline).toArray()
)

