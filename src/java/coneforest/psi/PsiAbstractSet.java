package coneforest.psi;

abstract public class PsiAbstractSet<T extends PsiObject>
	extends PsiAbstractObject
	implements PsiSetlike<T>
{
	@Override
	public String getTypeName()
	{
		return "set";
	}

	@Override
	public String toString()
	{
		return toStringHelper(this);
	}

	@Override
	public String toStringHelper(PsiLengthy lengthy)
	{
		StringBuilder sb=new StringBuilder("(");
		if(length()>0)
		{
			for(PsiObject obj: this)
			{
				if(obj instanceof PsiLengthy)
					sb.append(obj==lengthy? "-"+obj.getTypeName()+"-": ((PsiLengthy)obj).toStringHelper(this));
				else
					sb.append(obj.toString());
				sb.append(' ');
			}
			sb.deleteCharAt(sb.length()-1);
		}
		sb.append(')');
		return sb.toString();
	}

	@Override
	public PsiAbstractSet<T> psiNewEmpty()
		throws PsiException
	{
		try
		{
			return getClass().newInstance();
		}
		catch(InstantiationException|IllegalAccessException e)
		{
			throw new PsiException("unknownerror");
		}
	}

	@Override
	public void psiAppendAll(final PsiIterable<? extends T> iterable)
		throws PsiException
	{
		if(this==iterable)
			return;
		for(T obj: iterable)
			psiAppend(obj);
	}

	@Override
	public void psiRemoveAll(PsiIterable<? extends T> iterable)
	{
		if(this==iterable)
			psiClear();
		else
			for(T obj: iterable)
				psiRemove(obj);
	}

	@Override
	public void psiRetainAll(PsiIterable<? extends T> iterable)
		throws PsiException
	{
	//	for(T obj: this)
	//		for(T otherObj: iterable)
	//			if(!psiContains(obj).getValue())
	//				psiRemove(obj);
		System.out.println("NOP RETAINALL ITERABLE");
	}

	@Override
	public void psiRetainAll(PsiSetlike<? extends T> setlike)
	{
		System.out.println("NOP RETAINALL SETLIKE");
	}

	@Override
	public PsiInteger psiLength()
	{
		return PsiInteger.valueOf(length());
	}

	@Override
	public boolean isEmpty()
	{
		return length()==0;
	}

	@Override
	public PsiBoolean psiIsEmpty()
	{
		return PsiBoolean.valueOf(isEmpty());
	}

	@Override
	public void psiClear()
	{
		for(T obj: this)
			psiRemove(obj);
	}

	@Override
	abstract public PsiAbstractSet psiClone();

	@Override
	public PsiAbstractSet psiReplicate(PsiInteger count)
	{
		return psiClone();
	}

	@Override
	public PsiBoolean psiIntersects(final PsiSetlike<? extends T> setlike)
	{
		for(T obj: setlike)
			if(psiContains(obj).booleanValue())
				return PsiBoolean.TRUE;
		return PsiBoolean.FALSE;
	}
}
