/**
 * Вывести все простые числа от 1 до 1000.
 */
public class task2
{
    public static void main(String[] args)
    {
        System.out.println("Простые числа от 1 до 1000:");
        for (int i = 0; i <= 1000; i++)
        {
            if(IsPrime(i))
                System.out.println(i);
        }
    }

    // Проверить является ли число num простым
    private static boolean IsPrime(int num)
    {
        if(num < 2) return false;
        for (int i = 2; i < num; i++)
        {
            if(num%i == 0)
                return false;
        }
        return true;
    }
}