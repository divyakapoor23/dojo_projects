module.exports = function() {
    return {
        add: function(num1, num2) {
            console.log("the sum is:", num1 + num2);
        },
        multiply: function(num1, num2) {
            console.log("the product is:", num1 * num2);
        },
        square: function(num) {
            console.log("the square is:", num*num);
        },
        random: function() {
            console.log("the random number is:", Math.floor(Math.random() * 35)+1);
        }
     }
}