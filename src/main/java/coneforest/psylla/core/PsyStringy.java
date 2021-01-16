package coneforest.psylla.core;
import coneforest.psylla.*;

/**
*	A representation of Ψ-{@code stringy}, a basic type of mutable and
*	immutable strings.
*/
@Type("stringy")
public interface PsyStringy
	extends
		PsyEvaluable,
		PsyConvertableToInteger,
		PsyConvertableToReal,
		PsyIterable<PsyInteger>,
		PsyLengthy,
		PsyScalar<PsyStringy>
{

	/**
	*	Returns a string value of this object.
	*
	*	@return a string value.
	*/
	public String stringValue();

	public PsyStringy psyUpperCase();

	public PsyStringy psyLowerCase();

	@Override
	default public PsyName psyToName()
	{
		return new PsyName(stringValue());
	}

	@Override
	default public void eval(final Interpreter interpreter)
		throws PsyException
	{
		(new PsyStringReader(this)).eval(interpreter);
	}

	@Override
	default public PsyInteger psyToInteger()
		throws PsyException
	{
		try
		{
			// TODO fractional
			return PsyInteger.valueOf(Long.parseLong(stringValue()));
		}
		catch(final NumberFormatException e)
		{
			throw new PsySyntaxErrorException();
		}
	}

	@Override
	default public PsyReal psyToReal()
		throws PsySyntaxErrorException
	{
		try
		{
			return new PsyReal(Double.parseDouble(stringValue()));
		}
		catch(final NumberFormatException e)
		{
			throw new PsySyntaxErrorException();
		}
	}

	@Override
	default public int length()
	{
		return stringValue().length();
	}

	@Override
	default public PsyBoolean psyEq(final PsyObject o)
	{
		return PsyBoolean.valueOf(o instanceof PsyStringy
				&& stringValue().equals(((PsyStringy)o).stringValue()));
	}

	@Override
	default public PsyBoolean psyLt(final PsyStringy oString)
	{
		return PsyBoolean.valueOf(stringValue().compareTo(oString.stringValue())<0);
	}

	@Override
	default public PsyBoolean psyLe(final PsyStringy oString)
	{
		return PsyBoolean.valueOf(stringValue().compareTo(oString.stringValue())<=0);
	}

	@Override
	default public PsyBoolean psyGt(final PsyStringy oString)
	{
		return PsyBoolean.valueOf(stringValue().compareTo(oString.stringValue())>0);
	}

	@Override
	default public PsyBoolean psyGe(final PsyStringy oString)
	{
		return PsyBoolean.valueOf(stringValue().compareTo(oString.stringValue())>=0);
	}

	@Override
	default public PsyInteger psyCmp(final PsyStringy oString)
	{
		return PsyInteger.valueOf(stringValue().compareTo(oString.stringValue()));
	}

	default public PsyArray psySplit(final PsyRegExp oRegExp)
		throws PsyException
	{
		final var oArray=new PsyArray();
		for(final var item: oRegExp.getPattern().split(stringValue(), -1))
			// TODO PsyString
			oArray.psyAppend(new PsyString(item));
		return oArray;
	}

	@Override
	default public java.util.Iterator<PsyInteger> iterator()
	{
		return new java.util.Iterator<PsyInteger>()
			{
				public boolean hasNext()
				{
					return index<stringValue().length();
				}

				public PsyInteger next()
				{
					return PsyInteger.valueOf(stringValue().charAt(index++));
				}

				private int index=0;
			};
	}

	public static final PsyOperator[] OPERATORS=
		{
			new PsyOperator.Arity11<PsyStringy>("lowercase", PsyStringy::psyLowerCase),
			new PsyOperator.Arity21<PsyStringy, PsyRegExp>("split", PsyStringy::psySplit),
			new PsyOperator.Arity11<PsyStringy>("uppercase", PsyStringy::psyUpperCase),
		};

}
