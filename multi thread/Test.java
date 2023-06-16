class Test
{
    public static void main(String[] args)
    {
        int a = 0, b = 0;
        for(int i = 1; i < 2; i++)
        {
            a = a++;
            b = a++;
        }
        System.out.println(b);
        System.out.println(a);
    }
}