//ES5 VERSION
function Card() {

    let suits = ["Hearts", "Spades", "Diamonds", "Clubs"];
    let nums = ["Ace","2","3","4","5","6","7","8","9","10","Jack","Queen","King"];

    var s = Math.floor(Math.random() * suits.length);
    var n = Math.floor(Math.random() * nums.length);

    this.nameCard = function() {
        console.log(`${this.getNum()} of ${this.getSuit()}`);
    }
    this.getSuit = function() {
        return suits[s];
    }
    this.getNum = function() {
        return nums[n];
    }
    this.allSuits = function() {
        return suits;
    }
    this.allNums = function() {
        return nums;
    }
}
// const card1 = new Card();
// card1.nameCard();

function Deck() {
    this.deck = [];
    this.createDeck = function(deck) {
        let card = new Card();
        let suit = card.allSuits();
        let num = card.allNums();

        for(var x=0; x<suit.length; x++){
            for(var y=0; y<num.length; y++){
                card.nameCard = function() {
                    return `${num[y]} of ${suit[x]}`;
                }
                this.deck.push(card.nameCard());
            }
        }
        console.log("New Deck");
        // console.log(this.deck)
        return this;
    }
    this.shuffle = function() {
        for(var x=0; x<this.deck.length; x++) {
            var rand = Math.floor(Math.random() * this.deck.length);
            temp = this.deck[x];
            this.deck[x] = this.deck[rand];
            this.deck[rand] = temp;
        }
        console.log("Shuffled");        
        // console.log(this.deck);       
        return this;
    }
    this.reset = function () {
        console.log("Deck Reset");   
        this.deck = [];   
        this.createDeck();
        // console.log(this.deck);                               
        return this;
    }
    this.randomDeal = function (deck) {
        var rand = Math.floor(Math.random() * this.deck.length);
        var dealt = this.deck[rand];
        // console.log(rand);
        // console.log(dealt);
        for(var x=rand; x<this.deck.length-1; x++) { 
            this.deck[x] = this.deck[x+1];
        }
        this.deck.pop();
        // console.log(this.deck.length)
        // console.log(`Dealing ${dealt}`);
        return [dealt, this];
    }
    this.createDeck();
}

// const deck = new Deck();
// deck.createDeck().shuffle().reset().randomDeal();

function Player (name) {
    this.name = name;
    this.hand = [];
    let deck = new Deck();
    this.displayHand = function() {
        console.log(`Your hand has the following ${this.hand.length} cards: ${this.hand}`);
        return this;
    }
    this.dealHand = function(numCards) {
        while(numCards>0) {
            this.hand.push(deck.randomDeal()[0]);
            numCards--;
        }
        this.displayHand();
        return this;
    }
    this.takeCard = function() {
        let card = deck.randomDeal()[0];
        this.hand.push(card);
        // console.log(this.hand); 
        this.displayHand();       
        return this;
    }
    this.discardCard = function(index) {
        if(index >= this.hand.length){
            console.log("You don't have that many cards in your hand.");
            this.displayHand();                   
        }
        else{
            // console.log(this.hand);            
            let dcard = this.hand[index-1];
            for(var x=index-1; x<this.hand.length-1; x++) { 
                this.hand[x] = this.hand[x+1];
            }
            this.hand.pop();
            console.log(`Discarding ${dcard}`);
            // console.log(this.hand);
            this.displayHand();                   
        }
        return this;
    }
}
const tim = new Player("Tim");
tim.dealHand(10).takeCard().takeCard().takeCard().takeCard().discardCard(2).discardCard(5);