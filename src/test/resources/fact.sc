fn factorial(n: int): int {
    let result = 1;
    while (n > 0) {
        result = result * n;
        n = n - 1;
    };
    result
}