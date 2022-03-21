
const findCity = (_id) => db.people.findOne({_id}).location.city

const attachCity = ({_id, first_name, last_name, birth_date}) => 
    birth_date.startsWith('2') 
        ? ({first_name, last_name, city: findCity(_id)}) 
        : ({first_name, last_name})


const allPeopleData = db.people.find({}, {first_name: 1, last_name: 1, birth_date: 1})

printjson(
    allPeopleData.map(attachCity)
)

