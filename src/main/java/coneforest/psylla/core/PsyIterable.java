package coneforest.psylla.core;
import coneforest.psylla.*;

/**
*	A representation of Ψ-{@code iterable}, a type of an object that can be
*	iterated over.
*
*	@param <T> a type of elements returned by the iterator.
*/
@Type("iterable")
public interface PsyIterable<T extends PsyObject>
	extends
		PsyStreamable<T>,
		Iterable<T>
{

	default public PsyArray psyToArray()
		throws PsyException
	{
		final var oArray=new PsyArray();
		for(final T o: this)
			oArray.psyAppend(o);
		return oArray;
	}

	default public PsyFormalStream<T> psyStream()
	{
		return new PsyStream(java.util.stream.StreamSupport.<T>stream(spliterator(), false));
	}

	default public PsyString psyUnite(final PsyTextual oSeparator)
		throws PsyException
	{
		final var separator=oSeparator.stringValue();
		final var sb=new StringBuilder();
		final java.util.Iterator<T> iterator=iterator();
		try
		{
			while(iterator.hasNext())
			{
				sb.append(((PsyTextual)iterator.next()).stringValue());
				if(iterator.hasNext())
					sb.append(separator);
			}
		}
		catch(final ClassCastException e)
		{
			throw new PsyTypeCheckException();
		}
		return new PsyString(sb);
	}

}
