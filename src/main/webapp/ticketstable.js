


let tickets = [
    {"id": 2,
    "amount": 1020.44
    },
    {"id": 4,
    "amount": 100.00
    }
];


function displayTicketTable() {


    //for ()
}



var x = 10;

{
// Block scope    
    let x = 2;
    console.log(x);
}

console.log("ANIMAL")
// MUST USE NEW FOR THIS TO WORK!
let animal = new function() {
    this.height = 5;
    this.color = "blue";
    
}

function AnimalV2() {
    this.height = 5;
    this.color = "blue";
}

let animalV2 = new AnimalV2();

console.log(animal);
console.log(animalV2);
console.log("-----------");





function functionA() {
    console.log("this from within a function:")
    console.log(this);
}

functionA();

console.log("this from global scope:")
console.log(this);


console.log("PENCIL:")
function pencil() {
    this.length = 11;
    return this;
}

console.log(pencil());

let p = 22;

function functionB() {

    {
        console.log("p = "+p);
    }

}

functionB();

let animalObject = 
{
    age: 22,
    weight: 10,
    wagTail: function() {
        console.log("wagging tail");
    }
}

console.log(animalObject);
animalObject.wagTail();