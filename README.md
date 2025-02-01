# Quadratic-Congruences-By-Java
# Quadratic Congruence Solver

This Java program solves quadratic congruences of the form:

\[
ax^2 + bx + c \equiv 0 \pmod{p}
\]

where:
- \( a, b, c \) are integers provided by the user.
- \( p \) is a prime number also provided by the user.
- The goal is to find integer solutions \( x \) modulo \( p \).

## Features
- **Prime Number Validation:** Ensures \( p \) is a prime number.
- **Quadratic Residue Check:** Uses the **Legendre symbol** to determine if the equation has solutions.
- **Modular Square Root Calculation:** Implements the **Tonelli-Shanks algorithm** to compute square roots modulo \( p \).
- **Modular Inverse Calculation:** Finds the modular inverse to complete the quadratic formula.
- **Efficient Computation:** Uses **modular exponentiation** for fast calculations.

## How It Works
1. Takes user input for values \( a, b, c, \) and \( p \).
2. Validates \( p \) as prime and checks \( a \neq 0 \mod p \).
3. Computes the discriminant \( \Delta = b^2 - 4ac \) mod \( p \).
4. Checks if \( \Delta \) is a quadratic residue modulo \( p \) using the **Legendre symbol**.
5. Finds the square root of \( \Delta \) mod \( p \) using the **Tonelli-Shanks algorithm**.
6. Solves for \( x \) using the quadratic formula in modular arithmetic:
   \[
   x = \frac{-b \pm \sqrt{\Delta}}{2a} \mod p
   \]
7. Outputs the solutions \( x_1, x_2 \) mod \( p \) if they exist, otherwise prints `"NO SOLUTION"`.

##Dependencies
- **JDK 8 or later (for Java execution)
- **Java Scanner (for input handling)
- **Mathematical knowledge of modular arithmetic (recommended)

##Applications
This program is useful in various domains, including:

- **Cryptography: Used in RSA and Elliptic Curve Cryptography (ECC) to solve modular equations.
- **Number Theory: Helps in solving equations in finite fields.
- **Computer Science Algorithms: Applies modular arithmetic techniques in computational problems.
- **Mathematical Research: Supports research in prime numbers and quadratic congruences.
