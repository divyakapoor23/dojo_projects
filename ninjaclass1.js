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
}
const kobayashi = new Ninja("Kobayashi");
kobayashi.sayName().showStats().drinkSake().showStats();
