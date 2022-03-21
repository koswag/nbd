
const person = {
    "sex" : "Male",
    "first_name" : "Konstanty",
    "last_name" : "Skarżyński",
    "job" : "Software Engineer I",
    "email" : "s15165@pjwstk.edu.pl",
    "location" : {
    	"city" : "Warsaw",
        "address" : {
            "streetname" : "Kolska",
            "streetnumber" : "2/4"
        }
    },
    "description" : "aaa xdd",
    "height" : "193",
    "weight" : "83",
    "birth_date" : "1997-04-06T02:22:07Z",
    "nationality" : "Poland",
    "credit" : [
        {
            "type" : "jcb",
            "number" : "3529195112892553",
            "currency" : "ILS",
            "balance" : "4265.17"
        }
    ]
}


printjson(
    db.people.insertOne(person)
)

