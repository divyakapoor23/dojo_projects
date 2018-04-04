class Ninja {
    constructor(name) {
        this.name = name;
        this.health = 100;
        this.speed = 3;
        this.strength = 3;
    }

    sayName() {
        console.log("My name is "+this.name+"!");
        return this;
    }
    getSpeed() {
        return this.speed;
    }
    getStrength () {
        return this.strength;
    }
    showStats() {
        console.log("Name: " + this.name + ", Health: " + this.health + ", Speed: " + this.getSpeed() + ", Strength: " + this.getStrength());
        return this;
    }
    drinkSake() {
        this.health += 10;
        console.log("Mmmmm sake!")
        this.showStats();
        return this;
    }
    punch(ninja) {
        if(ninja instanceof Ninja) {
            ninja.health -= 5;
            console.log("Hiya!!!");
            console.log(`${this.name} punched ${ninja.name}`);
            console.log(`${ninja.name}'s health is now ${ninja.health}`);
            return this;
        }
        else {return "That is not a ninja";}
    }
    kick(ninja) {
        if(ninja instanceof Ninja) {
            ninja.health -= 15;
            console.log("Roundhouse kick!!!");
            console.log(`${this.name} kicked ${ninja.name}`);
            console.log(`${ninja.name}'s health is now ${ninja.health}`);
            return this;
        }
        else {return "That is not a ninja";}
    }
}
// const kobayashi = new Ninja("Kobayashi");
// kobayashi.sayName().showStats().drinkSake();
// const blueNinja = new Ninja("Goemon");
// const redNinja = new Ninja("Bill Gates");
// blueNinja.kick(redNinja);
// redNinja.punch(blueNinja);

class Sensei extends Ninja{
    constructor(name) {
        super(name);
        this.health = 200;
        this.speed = 10;
        this.strength = 10;
        this.wisdom = 10;
    }
    speakWisdom() {
        console.log("What one programmer can do in one month, two programmers can do in two months.");
    }
    showStats() {
        const message = super.showStats();
    }
    
}
const superSensei = new Sensei("Master Splinter");
superSensei.speakWisdom();
superSensei.showStats();