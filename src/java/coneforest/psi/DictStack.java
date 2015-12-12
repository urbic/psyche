package coneforest.psi;

/**
 *	An interpreter’s dictionary stack.
 */
public class DictStack
	extends Stack<PsiDictlike>
{
	public DictStack()
		throws PsiException
	{
		PsiDict systemdict=new PsiSystemDict();
		push(systemdict);
		push((PsiDictlike)systemdict.get("userdict"));
	}

	public PsiObject load(String key)
		throws PsiException
	{
		PsiDictlike oDict=where(key);
		if(oDict!=null)
			return oDict.get(key);
		else
			throw new PsiUndefinedException();
	}

	public PsiObject load(PsiStringy oKey)
		throws PsiException
	{
		return load(oKey.stringValue());
	}

	public PsiDictlike where(String key)
	{
		for(int i=size()-1; i>=0; i--)
		{
			PsiDictlike oDict=get(i);
			if(oDict.known(key))
				return oDict;
		}
		return null;
	}

	public PsiDictlike where(PsiStringy oKey)
	{
		return where(oKey.stringValue());
	}

	public void store(String key, PsiObject o)
	{
		PsiDictlike oDict=where(key);
		if(oDict==null)
			oDict=peek();
		oDict.put(key, o);
	}

	public void store(PsiStringy oKey, PsiObject o)
	{
		store(oKey.stringValue(), o);
	}
}
