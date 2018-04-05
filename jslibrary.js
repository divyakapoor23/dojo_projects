//Can we make this into a method of an object?
function each(arr, cb2) {
    // loop through the array
    for(var i = 0; i < arr.length; i++) { 
        cb2(arr[i]);
    }
}
var _ = {
    // http://underscorejs.org/
    map: function(arr, cb1) {
        // _.map(list, iteratee, [context]) Alias: collect 
        // Produces a new array of values by mapping each value in list through a transformation function (iteratee). The iteratee is passed three arguments: the value, then the index (or key) of the iteration, and finally a reference to the entire list.
        let arr2 = [];
        each(arr, (num) => { //this anonymous arrow function is cb2!
            arr2.push(cb1(num));
        });                        
        return arr2;
    },
    reduce: function(arr, cb1, memo) { 
        // _.reduce(list, iteratee, [memo], [context]) Aliases: inject, foldl 
        // Also known as inject and foldl, reduce boils down a list of values into a single value. Memo is the initial state of the reduction, and each successive step of it should be returned by iteratee. The iteratee is passed four arguments: the memo, then the value and index (or key) of the iteration, and finally a reference to the entire list.
        // If no memo is passed to the initial invocation of reduce, the iteratee is not invoked on the first element of the list. The first element is instead passed as the memo in the invocation of the iteratee on the next element in the list.
        let sum = memo;
        each(arr, (num) => { //this anonymous arrow function is cb2!
            sum += cb1(memo, num);
        });                        
        return sum;
    },
    find: function(arr, cb1) {   
        // _.find(list, predicate, [context]) Alias: detect 
        // Looks through each value in the list, returning the first one that passes a truth test (predicate), or undefined if no value passes the test. The function returns as soon as it finds an acceptable element, and doesn't traverse the entire list.
        let x;  
        each(arr, (num) => { //this anonymous arrow function is cb2!
            if(cb1(num) && x==undefined) {
                x = num;
            }
          });  
        return x;
    },
    filter: function(arr, cb1) {  
        // _.filter(list, predicate, [context]) Alias: select 
        // Looks through each value in the list, returning an array of all the values that pass a truth test (predicate).
        let arr2 = [];
        each(arr, (num) => { //this anonymous arrow function is cb2!
            if(cb1(num)) {
                arr2.push(num);
            }
        });                        
        return arr2;
    },
    reject: function(arr, cb1) { 
        // _.reject(list, predicate, [context]) 
        // Returns the values in list without the elements that the truth test (predicate) passes. The opposite of filter.
        let arr2 = [];
        each(arr, (num) => { //this anonymous arrow function is cb2!
            if(!cb1(num)) {
                arr2.push(num);
            }
        });                        
        return arr2;
    }
}
    
var evens = _.filter([1, 2, 3, 4, 5, 6], function(num){ return num % 2 == 0; }); //this function is cb1!
console.log(evens); // if things are working right, this will return [2,4,6].

var mapping = _.map([1, 2, 3], function(num){ return num * 3; });
console.log(mapping);

var reduceToSum = _.reduce([1, 2, 3], function(memo, num){ return memo + num; }, 0);
console.log(reduceToSum);

var findEven = _.find([1, 2, 3, 4, 5, 6], function(num){ return num % 2 == 0; });
console.log(findEven);

var odds = _.reject([1, 2, 3, 4, 5, 6], function(num){ return num % 2 == 0; });
console.log(odds);