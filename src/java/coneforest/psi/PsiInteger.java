package coneforest.psi;

public class PsiInteger
	extends PsiNumeric
	implements PsiLogical<PsiInteger>
{
	public PsiInteger(final long value)
	{
		this.value=value;
	}

	public Long getValue()
	{
		return value;
	}
	
	public String getTypeName()	{ return "integer"; }

	public String toString()
	{
		return String.valueOf(value);
	}

	public PsiInteger not()
	{
		return new PsiInteger(~getValue());
	}

	public PsiInteger or(final PsiInteger obj)
	{
		return new PsiInteger(getValue() | obj.getValue());
	}

	public PsiInteger and(final PsiInteger obj)
	{
		return new PsiInteger(getValue() & obj.getValue());
	}

	public PsiInteger xor(final PsiInteger obj)
	{
		return new PsiInteger(getValue() ^ obj.getValue());
	}

	public PsiNumeric neg()
	{
		if(getValue()!=Long.MIN_VALUE)
			return new PsiInteger(-getValue());
		else
			return new PsiReal(-getValue());
	}
	
	public PsiNumeric abs()
	{
		if(value!=Long.MIN_VALUE)
			return new PsiInteger(value>=0L? value: -value);
		else
			return new PsiReal(Math.abs(value));
	}

	public PsiNumeric add(final PsiNumeric numeric)
	{
		double result=value+numeric.getValue().doubleValue();
		if(numeric instanceof PsiInteger
				&& result>=Long.MIN_VALUE
				&& result<=Long.MAX_VALUE)
			return new PsiInteger(((Double)result).longValue());
		else
			return new PsiReal(result);
	}

	public PsiNumeric sub(final PsiNumeric numeric)
	{
		double result=value-numeric.getValue().doubleValue();
		if(numeric instanceof PsiInteger
				&& result>=Long.MIN_VALUE
				&& result<=Long.MAX_VALUE)
			return new PsiInteger(((Double)result).longValue());
		else
			return new PsiReal(result);
	}

	public PsiNumeric mul(final PsiNumeric numeric)
	{
		double result=value*numeric.getValue().doubleValue();
		if(numeric instanceof PsiInteger
				&& result>=Long.MIN_VALUE
				&& result<=Long.MAX_VALUE)
			return new PsiInteger(((Double)result).longValue());
		else
			return new PsiReal(result);
	}

	public PsiInteger floor()
	{
		return this;
	}

	public PsiInteger ceiling()
	{
		return this;
	}

	public static PsiInteger mod(final PsiInteger x, final PsiInteger y)
	{
		long xValue=x.getValue();
		long yValue=y.getValue();
		if(yValue>0)
			return new PsiInteger(xValue>=0? xValue%yValue: yValue-(-xValue)%yValue);
		if(yValue<0)
			return new PsiInteger(xValue>=0? xValue%(-yValue)+(xValue!=0? yValue:0): -((-xValue)%(-yValue)));
		return null;
	}

	public static PsiInteger bitshift(final PsiInteger x, final PsiInteger shift)
	{
		long xValue=x.getValue();
		long shiftValue=shift.getValue();
		return new PsiInteger(shiftValue>=0? (xValue<<shiftValue): (xValue>>(-shiftValue)));
	}

	private final long value;
}
