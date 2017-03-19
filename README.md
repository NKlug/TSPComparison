# TSPComparison
A comparison of different Solutions to the "Travelling Salesman Problem".

The first implementation is just brute force, trying out every possible permutation and selecting the shortest route,
meanwhile the second one uses a slightly optimized algorithm, but is still brute force.
The third approach utilizes a neural net, to be precise a kohonen feature map, to calculate the right order.

It is obvious that the optimized brute force algorithm is superior to the casual brute force one,
but the really interesting part is how it competes against the neural net. I'm writing this when I haven't
done testing yet, but my expectation is that for only a few cities the brute force approach is faster, when
for a larger number of cities the neural net will be faster.
