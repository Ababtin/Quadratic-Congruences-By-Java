package csc281;
import java.util.Scanner;

public class CSC281 {
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input values
        System.out.print("Enter a: ");
        int a = scanner.nextInt();
        System.out.print("Enter b: ");
        int b = scanner.nextInt();
        System.out.print("Enter c: ");
        int c = scanner.nextInt();
        System.out.print("Enter p (a prime number): ");
        int p = scanner.nextInt();

        // Check if p is prime and a is not congruent to 0 mod p
        if (isPrime(p) && a % p != 0) {
            int[] solutions = quadraticSolver(a, b, c, p);
            if (solutions.length == 0) {
                System.out.println("NO SOLUTION");
            } else {
                System.out.println("Solutions: {" + solutions[0] + ", " + solutions[1] + "}");
            }
        } else {
            System.out.println("Invalid input");
        }
    }

    // Check if a number is prime
    private static boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    // Solve the quadratic congruence ax^2 + bx + c ≡ 0 (mod p)
    private static int[] quadraticSolver(int a, int b, int c, int p) {
        int delta = (b * b - 4 * a * c) % p;
        delta = (delta + p) % p; // Ensure delta is positive

        // Check if the equation has solutions
        if (legendreSymbol(delta, p) != 1) {
            return new int[0];
        }

        // Find a square root of 'delta' modulo 'p'
        int sqrtDelta = modSqrt(delta, p);

        // Calculate the two solutions
        int inv2a = modInverse(2 * a, p);
        int t1 = (inv2a * (-b + sqrtDelta + p)) % p; // Add p to ensure a positive result
        int t2 = (inv2a * (-b - sqrtDelta + p)) % p; // Add p to ensure a positive result
     // Ensure the solutions are in the correct range (0 to p-1)
        t1 = (t1 + p) % p;
        t2 = (t2 + p) % p;
        return new int[]{t1, t2};
    }

    // Calculate the Legendre symbol (a/p)
    private static int legendreSymbol(int a, int p) {
        int ls = modPow(a, (p - 1) / 2, p);
        return ls == p - 1 ? -1 : ls;
    }

    // Calculate the modular inverse of 'a' modulo 'p'
    private static int modInverse(int a, int p) {
        int m = p;
        int m0 = m, t, q;
        int x0 = 0, x1 = 1;

        if (m == 1)
            return 0;

        while (a > 1) {
            q = a / m;
            t = m;

            m = a % m;
            a = t;
            t = x0;

            x0 = x1 - q * x0;
            x1 = t;
        }

        if (x1 < 0)
            x1 += m0;

        return x1;
    }

    // Calculate modular square roots using the Tonelli-Shanks algorithm
    private static int modSqrt(int a, int p) {
        int q = p - 1;
        int s = 0;

        while (q % 2 == 0) {
            q /= 2;
            s += 1;
        }

        int z = 2;
        while (legendreSymbol(z, p) != -1) {
            z += 1;
        }

        int c = modPow(z, q, p);
        int r = modPow(a, (q + 1) / 2, p);
        int t = modPow(a, q, p);

        int m = s;
        while (t != 1) {
            int i = 1;
            int tsquared = (t * t) % p;

            while (tsquared != 1) {
                tsquared = (tsquared * tsquared) % p;
                i += 1;
            }

            int b = modPow(c, 1 << (m - i - 1), p);
            m = i;
            c = (b * b) % p;
            r = (r * b) % p;
            t = (t * c) % p;
        }

        return r;
    }

    // Calculate 'base' raised to the power 'exponent' modulo 'mod'
    private static int modPow(int base, int exponent, int mod) {
        int result = 1;
        base = ((base % mod) + mod) % mod; // Ensure base is positive
        while (exponent > 0) {
            if (exponent % 2 == 1) {
                result = (result * base) % mod;
            }
            exponent = exponent >> 1;
            base = (base * base) % mod;
        }
        return result;
    }
}



