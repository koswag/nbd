
db.people.mapReduce(
    function () { 
        emit(null, this.job);
    },
    function (ignored, jobs) {
        const unique = array => 
            array.filter((v, i, arr) => arr.indexOf(v) === i);
        
        return unique(jobs);
    },
    {out: 'zad3'}
)

printjson(
    db.zad3.find().toArray()
)

