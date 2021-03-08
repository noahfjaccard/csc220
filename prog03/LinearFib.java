package prog03;

public class LinearFib implements Fib {

	@Override
	public double fib(int n) {
		// TODO Auto-generated method stub
		int a = 0;
		int b = 1;
		int total = 1;
		for (int i = 2; i <= n; i++)
		{
			a = b;
			b = total;
			total = a + b;
		}
		
		return total;
	}

	@Override
	public double o(int n) {
		// TODO Auto-generated method stub
		return n;
	}

}
