function Ninja (name) {
    this.name = name;
    this.health = 100;
    let speed = 3;
    let strength = 3;

    this.sayName = function() {
        console.log("My name is "+this.name+"!");
      return this;
    }
    this.getSpeed = function() {
        return speed;
    }
    this.getStrength = function () {
        return strength;
    }
    this.showStats = function() {
        console.log("Name: " + this.name + ", Health: " + this.health + ", Speed: " + this.getSpeed() + ", Strength: " + this.getStrength());
        return this;
    }
    this.drinkSake = function() {
        this.health += 10;
        console.log("Mmmmm sake!")
        return this;
    }
    this.punch = function(ninja) {
        if(ninja instanceof Ninja) {
            ninja.health -= 5;
            console.log("Hiya!!!");
            console.log(`${this.name} punched ${ninja.name}`);
            return this;
        }
        else {return "That is not a ninja";}
    }
    this.kick = function(ninja) {
        if(ninja instanceof Ninja) {
            ninja.health -= 15;
            console.log("Roundhouse kick!!!");
            console.log(`${this.name} kicked ${ninja.name}`);
            return this;
        }
        else {return "That is not a ninja";}
    }
}
const kobayashi = new Ninja("Kobayashi");
kobayashi.sayName().showStats().drinkSake().showStats();
const blueNinja = new Ninja("Goemon");
const redNinja = new Ninja("Bill Gates");
blueNinja.kick(redNinja);
redNinja.punch(blueNinja);
blueNinja.showStats();
redNinja.showStats();
