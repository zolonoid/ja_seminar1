import java.util.Scanner;

/**
 * Реализовать простой калькулятор.
 */
public class task3
{
    private static Scanner sc;
    
    public static void main(String[] args)
    {
        System.out.println("Шаг 1: Введите целое число.");
        System.out.println("Шаг 2: Укажите арифметическое действие: + - * /");
        System.out.println("Шаг 3: Введите еще одно число.");
        System.out.println("Шаг 4: После получения результата:");
        System.out.println("       - продолжите вычисления вернувшись к шагу 2");
        System.out.println("       - введите X для завершения.");
        try
        {
            sc = new Scanner(System.in);
            int operand1 = GetOperand();
            while(true)
            {
                char operator = GetOperator();
                if(operator == 'X' || operator == 'x') break;
                int operand2 = GetOperand();
                int result = Calculate(operand1, operand2, operator);
                System.out.printf("=\n%d\n", result);
                operand1 = result;
            }
        }
        catch(Exception e)
        {
            System.out.printf("Ошибка: %s", e.getMessage());
        }
        finally
        {
            if(sc != null)
                sc.close();
        }
    }

    // Получить введенное пользователем число.
    private static int GetOperand()
    {
        int operand = sc.nextInt();
        return operand;
    }

    // Получить указанное пользователем арифметическое действие.
    private static char GetOperator() throws Exception
    {
        char operator = sc.next().charAt(0);
        if("+-*/Xx".indexOf(operator) < 0)
            throw new Exception("Указано недопустимое действие.");
        return operator;
    }

    // Выполнить вычисление.
    private static int Calculate(int operand1, int operand2, char operator)
    {
        switch (operator)
        {
            case '+':
                return operand1 + operand2;
            case '-':
                return operand1 - operand2;
            case '*':
                return operand1 * operand2;
            case '/':
                return operand1 / operand2;
        }
        return 0;
    }
}