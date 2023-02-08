import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * task4
 */
public class task4
{
    public static void main(String[] args)
    {
        System.out.println("Задайте уравнение вида x + y = z, где x,y,z >= 0\n" +
                           "Одна или более цифр в числах x,y могут быть заменены знаком ?\n" +
                           "Например: 2? + ?5 = 69");
        Scanner sc = null;
        try
        {
            sc = new Scanner(System.in);
            String input = sc.nextLine();
            Pattern pattern = Pattern.compile("([?\\d]+)\\s?\\+\\s?([?\\d]+)\\s?\\=\\s?(\\d+)");
            Matcher matcher = pattern.matcher(input);
            if(!matcher.matches())
                throw new Exception("Неверный формат ввода");
            String x = matcher.group(1);
            String y = matcher.group(2);
            String z = matcher.group(3);
            ArrayList<Integer> variants1 = new UnknownNumber(x).Variants();
            ArrayList<Integer> variants2 = new UnknownNumber(y).Variants();
            int result = Integer.parseInt(z);
            ArrayList<Equation> solves = GetEquationSolvings(variants1, variants2, result);
            System.out.printf("Вариантов решения уравнения найдено %d:\n", solves.size());
            for(Equation e : solves)
                System.out.printf("%s -> %d + %d = %d", matcher.group(0), e.getX(), e.getY(), e.getZ());
        }
        catch (Exception e)
        {
            System.out.printf("Ошибка: %s", e.getMessage());
        }
        finally
        {
            if(sc != null)
                sc.close();
        }
    }

    // Найти возможные решения уравнения проверив его равенство для всех вариантов неизвестных чисел.
    private static ArrayList<Equation> GetEquationSolvings(ArrayList<Integer> variants1, ArrayList<Integer> variants2, int result)
    {
        var solvings = new ArrayList<Equation>();
        for(int x : variants1)
        {
            for (int y : variants2)
            {
                if(x + y == result)
                    solvings.add(new Equation(x, y, result));
            }
        }
        return solvings;
    }
}

// Класс для работы с неизвестными числами типа 2?, ?5.
class UnknownNumber
{
    private String _number;
    private char[] _valid_digits;
    private ArrayList<Integer> _digits;
    private ArrayList<Integer> _unknown_digits;
    private ArrayList<Integer> _variants;

    {
        _valid_digits = new  char[]{'0','1','2','3','4','5','6','7','8','9','?'};
        Arrays.sort(_valid_digits);
        _digits = new ArrayList<Integer>();
        _unknown_digits = new ArrayList<Integer>();
        _variants = new ArrayList<Integer>();
    }
    
    UnknownNumber(String number) throws Exception
    {
        _number = number;
        SplitNumberByDigits();
        if(_unknown_digits.size() > 0)
        {
            var digits = new ArrayList<Integer>(_digits);
            SearchNumberVariants(digits, 0);
        }
        else _variants.add(DigitsToNumber(_digits));
    }
    
    // Получить варианты значений неизвестного числа.
    public ArrayList<Integer> Variants()
    {
        ArrayList<Integer> variants = new ArrayList<Integer>(_variants);
        return variants;
    }
    
    // Найти все возможные варианты неизвестного числа.
    private void SearchNumberVariants(ArrayList<Integer> digits, int index)
    {
        for(int d = 0; d < 10; d++)
        {
            digits.set(_unknown_digits.get(index), d);
            if(index == _unknown_digits.size() - 1)
                _variants.add(DigitsToNumber(digits));
            else
                SearchNumberVariants(digits, index + 1);
        }
    }

    // Разбить число на цифры в соответствии с их положением в разряде числа.
    private void SplitNumberByDigits() throws Exception
    {
        for(int i = 0; i < _number.length(); i++)
        {
            char digit = _number.charAt(i);
            int index = Arrays.binarySearch(_valid_digits, digit);
            if(index < 0)
                throw new Exception("Недопустимый формат числа");
            if(digit == '?')
            {
                _digits.add(-1);
                _unknown_digits.add(_digits.size() - 1);
            }
            else _digits.add(index);
        }
    }

    // Преобразовать набор цифр в целое число.
    private int DigitsToNumber(ArrayList<Integer> digits)
    {
        int number = 0;
        for(int i = 0, size = digits.size(); i < size; i++)
        {
            int radix = size - i - 1;
            number += digits.get(i) * Math.pow(10, radix);
        }
        return number;
    }
}

// Класс представляет уравнение и содержит его данные.
class Equation
{
    private int _x;
    private int _y;
    private int _z;

    Equation(int x, int y, int z)
    {
        _x = x;
        _y = y;
        _z = z;
    }

    public int getX() { return _x;  }
    public int getY() { return _y;  }
    public int getZ() { return _z;  }
}