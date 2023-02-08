import java.util.Scanner;

/**
 * Вычислить n-е треугольное число и факториал числа n.
 */
public class task1
{
    public static void main(String[] args)
    {
        Scanner sc = null;
        try
        {
            System.out.println("Введите число n...");
            sc = new Scanner(System.in);
            int n = sc.nextInt();
            System.out.printf("Тр%d = %d\n", n, Triangular(n));
            System.out.printf("%d! = %d", n, Factorial(n));
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        finally
        {
            if(sc != null)
                sc.close();
        }
    }

    // Найти n-е треугольное число
    private static int Triangular(int n)
    {
        int tria = 0;
        for (int i = 0; i <= n; i++)
            tria += i;
        return tria;
    }
    
    // Найти факториал числа n
    private static int Factorial(int n)
    {
        int fact = 1;
        for (int i = 2; i <= n; i++)
            fact *= i;
        return fact;
    }
}