package coneforest.psylla.core;
import coneforest.psylla.*;

/**
*	A representation of Ψ-{@code dictlike}, an abstraction of a dictionary.
*
*	@param <V> a type of contained values.
*/
@Type("dictlike")
public interface PsyDictlike<V extends PsyObject>
	extends
		PsyContainer<V>,
		PsyIndexed<PsyStringy, V>
{

	@Override
	default public void psyForAll(final PsyObject o)
		throws PsyException
	{
		final var interpreter=PsyContext.psyCurrentContext();
		final var ostack=interpreter.operandStack();
		final var keysIterator=psyKeys().stream().iterator();
		interpreter.pushLoopLevel();
		interpreter.executionStack().push(new PsyOperator("#forall_continue")
			{
				@Override
				public void action()
					throws PsyException
				{
					//final var interpreter1=PsyContext.psyCurrentContext();
					if(keysIterator.hasNext())
					{
						final PsyStringy oKey=keysIterator.next();
						ostack.push(oKey);
						ostack.push(psyGet(oKey));
						//interpreter1.executionStack().push(this);
						interpreter.executionStack().push(this);
						o.invoke();
					}
					else
						//interpreter1.popLoopLevel();
						interpreter.popLoopLevel();
				}
			});
	}

	public V get(final String key)
		throws PsyException;

	@Override
	default public V psyGet(final PsyStringy oKey)
		throws PsyException
	{
		return get(oKey.stringValue());
	}

	@Override
	default public PsyArraylike<V> psyGetAll(final PsyIterable<PsyStringy> oEnumeration)
		throws PsyException
	{
		final PsyArraylike<V> oResult=(PsyArraylike<V>)new PsyArray();
		for(final var oKey: oEnumeration)
			oResult.psyAppend(psyGet(oKey));
		return oResult;
	}

	public void put(final String key, final V oValue);

	@Override
	default public void psyPut(final PsyStringy oKey, final V oValue)
	{
		put(oKey.stringValue(), oValue);
	}

	public boolean known(final String key);

	@Override
	default public PsyBoolean psyKnown(final PsyStringy oKey)
	{
		return PsyBoolean.valueOf(known(oKey.stringValue()));
	}

	public void undef(final String key);

	/**
	*	Deletes a key and associated value from this dictionary.
	*
	*	@param oKey a Ψ-{@code stringy} key.
	*/
	default public void psyUndef(final PsyStringy oKey)
	{
		undef(oKey.stringValue());
	}

	@Override
	public PsyStreamlike<PsyStringy> psyKeys();

	@Override
	default public void psyDelete(final PsyStringy oKey)
		throws PsyException
	{
		psyUndef(oKey);
	}

	@Override
	default public V psyExtract(final PsyStringy oKey)
		throws PsyException
	{
		V oResult=psyGet(oKey);
		psyUndef(oKey);
		return oResult;
	}

	@Override
	public PsyDictlike<V> psySlice(final PsyIterable<PsyStringy> oEnumeration)
		throws PsyException;

	@Override
	default public PsyStream psyEntries()
	{
		return new PsyStream(java.util.stream.StreamSupport.<PsyObject>stream(new PsyIterable<PsyObject>()
			{
				@Override
				public java.util.Iterator<PsyObject> iterator()
				{
					return new java.util.Iterator<PsyObject>()
						{
							@Override
							public boolean hasNext()
							{
								return parentIterator.hasNext() || flag;
							}

							@Override
							public PsyObject next()
							{
								try
								{
									return (flag=!flag)?
										(oKey=(PsyName)parentIterator.next()): psyGet(oKey);
								}
								catch(final PsyException e)
								{
									throw new AssertionError(e);
								}
							}

							private boolean flag=false;

							private PsyName oKey;

							private java.util.Iterator<PsyStringy> parentIterator
								=psyKeys().stream().iterator();

						};
				}
			}.spliterator(),
			false));
		//*/
	}

	@Override
	default public String toSyntaxString()
	{
		return toSyntaxStringHelper(new java.util.HashSet<PsyContainer<V>>());
	}

	@Override
	default public String toSyntaxStringHelper(final java.util.Set<PsyContainer<V>> processed)
	{
		if(!processed.add((PsyContainer<V>)this))
			return '%'+typeName()+'%';
		final var sj=new java.util.StringJoiner(" ", "<", ">");
		psyEntries().stream().forEach(o->
			sj.add(o instanceof PsyContainer?
				((PsyContainer<V>)o).toSyntaxStringHelper(processed):
				o.toSyntaxString()));
		return sj.toString();
	}


	@Override
	default public void psyClear()
	{
		psyKeys().stream().forEach(this::psyUndef);
	}

}
