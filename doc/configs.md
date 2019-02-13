# Informal specification of internal config maps.

Inside Huginn are a variety of maps derived from an initial config file.
Huginn originally intended for them to all be one map, and have it include all
relevant information, but this seems unwieldy.
Therefore, smaller, context focused maps are going to be specified.
Each map contains the data relevant to one part of the computation.
I hope to compare the specifications, and see if a monolithic configuration would be better


## Population config

* constant-set: a set of constant numbers to be included in the function
* variable-list: a vector of symbols, that will become arguments to the produced function.
* depth-limit: a positive integer representing the max depth of generated program trees.
*  population-count: a positive integer, representing the number of individuals to be generated.

## Iteration config
Contains information about this present generation. Although not yet present, one needs to consider the information required to make decisions like varying the mutation rate

* mutation-count: a non-negative integer representing the number of individuals this generation to receive mutations.
* crossover-count:
* passthrough-count: a non-negative integer representing the number of individuals to passthrough to the next generation untouched.

## Test config
This contains the information used to evaluated the performance of individuals.
* variable-list: a vector of symbols, which a function under evualation might have bindings for.
* results: a sequence of numbers, with the nth number being the result of calling the ideal function with the nth arguments from the argument sequences.
* For every symbol in `:variable-list`, there should be a sequence of numbers the same length as `:results`
