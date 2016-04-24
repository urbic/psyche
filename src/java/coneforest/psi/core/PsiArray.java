package coneforest.psi.core;
import coneforest.psi.*;

/**
 *	A representation of Ψ-{@code array} object.
 */
public class PsiArray
	implements PsiArraylike<PsiObject>
{
	public PsiArray()
	{
		this(new java.util.ArrayList<PsiObject>());
	}

	public PsiArray(final java.util.ArrayList<PsiObject> list)
	{
		array=list;
	}

	public PsiArray(final PsiArray array)
	{
		this.array=(java.util.ArrayList<PsiObject>)array.array.clone();
	}

	/**
	 *	@return a string {@code "real"}.
	 */
	@Override
	public String getTypeName()
	{
		return "array";
	}

	@Override
	public int length()
	{
		return array.size();
	}

	@Override
	public java.util.Iterator<PsiObject> iterator()
	{
		return array.iterator();
	}

	@Override
	public PsiArray psiClone()
	{
		return new PsiArray(this);
	}

	@Override
	public PsiObject get(final int indexValue)
		throws PsiException
	{
		try
		{
			return array.get(indexValue);
		}
		catch(IndexOutOfBoundsException e)
		{
			throw new PsiRangeCheckException();
		}
	}

	@Override
	public PsiArray psiGetInterval(final PsiInteger start, final PsiInteger count)
		throws PsiException
	{
		try
		{
			return new PsiArray(new java.util.ArrayList<PsiObject>(array.subList(start.intValue(),
					start.intValue()+count.intValue())));
		}
		catch(IndexOutOfBoundsException|IllegalArgumentException e)
		{
			throw new PsiRangeCheckException();
		}
	}

	@Override
	public void psiAppend(final PsiObject obj)
		throws PsiException
	{
		if(length()==Integer.MAX_VALUE)
			throw new PsiLimitCheckException();
		array.add(obj);
	}

	@Override
	public void insert(final int indexValue, final PsiObject obj)
		throws PsiException
	{
		try
		{
			array.add(indexValue, obj);
		}
		catch(IndexOutOfBoundsException e)
		{
			throw new PsiRangeCheckException();
		}
	}

	@Override
	public void put(final int indexValue, final PsiObject obj)
		throws PsiException
	{
		try
		{
			array.set(indexValue, obj);
		}
		catch(IndexOutOfBoundsException e)
		{
			throw new PsiRangeCheckException();
		}
	}

	@Override
	public void delete(int indexValue)
		throws PsiException
	{
		try
		{
			array.remove(indexValue);
		}
		catch(IndexOutOfBoundsException e)
		{
			throw new PsiRangeCheckException();
		}
	}

	@Override
	public PsiObject extract(final int indexValue)
		throws PsiException
	{
		try
		{
			return array.remove(indexValue);
		}
		catch(IndexOutOfBoundsException e)
		{
			throw new PsiRangeCheckException();
		}
	}

	@Override
	public PsiArray psiExtractInterval(final PsiInteger start, final PsiInteger count)
		throws PsiException
	{
		PsiArray result=psiGetInterval(start, count);
		array.subList(start.intValue(), start.intValue()+count.intValue()).clear();
		return result;
	}

	@Override
	public PsiArray psiSlice(final PsiIterable<PsiInteger> indices)
		throws PsiException
	{
		PsiArray values=new PsiArray();
		for(PsiInteger index: indices)
			values.psiAppend(psiGet(index));
		return values;
	}

	@Override
	public void psiClear()
	{
		array.clear();
	}

	@Override
	public void psiSetLength(final PsiInteger length)
		throws PsiException
	{
		final long lengthValue=length.longValue();
		if(lengthValue<0)
			throw new PsiRangeCheckException();
		if(lengthValue>Integer.MAX_VALUE)
			throw new PsiLimitCheckException();
		int i=length();
		if(lengthValue<i)
			array.subList((int)lengthValue, i).clear();
		else
		{
			array.ensureCapacity((int)lengthValue);
			while(i++<lengthValue)
				array.add(PsiNull.NULL);
		}
	}

	public PsiArray psiSort(java.util.Comparator<? super PsiObject> comparator)
	{
		PsiArray result=psiClone();
		java.util.Collections.sort(result.array, comparator);
		return result;
	}

	public PsiInteger psiBinarySearch(PsiObject o, PsiProc oComparator)
		throws PsiException
	{
		final Interpreter interpreter=(Interpreter)PsiContext.psiCurrentContext();
		final OperandStack opstack=interpreter.operandStack();
		return PsiInteger.valueOf(java.util.Collections.<PsiObject>binarySearch(array, o,
			// TODO gap
			(o1, o2)->
			{
				opstack.push(o1);
				opstack.push(o2);
				final int execLevel=interpreter.getExecLevel();
				oComparator.invoke(interpreter);
				interpreter.handleExecutionStack(execLevel);
				// TODO: ensure stack size
				return ((PsiInteger)opstack.pop()).intValue();
			}));
	}

	private java.util.ArrayList<PsiObject> array;
}
