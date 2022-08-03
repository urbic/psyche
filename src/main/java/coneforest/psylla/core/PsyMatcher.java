package coneforest.psylla.core;
import coneforest.psylla.*;

@Type("matcher")
public class PsyMatcher
	implements PsyResetable
{
	public PsyMatcher(final PsyTextual oTextual, final PsyRegExp oRegExp)
	{
		matcher=oRegExp.getPattern().matcher(oTextual.stringValue());
	}

	public void psyReset()
	{
		matcher.reset();
	}

	public PsyBoolean psyMatches()
	{
		return PsyBoolean.valueOf(matcher.matches());
	}

	public PsyBoolean psyFind()
	{
		return PsyBoolean.valueOf(matcher.find());
	}

	public PsyName psyReplaceAll(final PsyTextual oReplacement)
	{
		return new PsyName(matcher.replaceAll(oReplacement.stringValue()));
	}

	public PsyInteger psyCaptureGroupCount()
	{
		return PsyInteger.valueOf(matcher.groupCount());
	}

	public PsyInteger psyCaptureGroupStart(final PsyObject oKey)
		throws PsyException
	{
		try
		{
			final int start;
			if(oKey instanceof PsyInteger)
				start=matcher.start(((PsyInteger)oKey).intValue());
			else if(oKey instanceof PsyTextual)
				start=matcher.start(((PsyTextual)oKey).stringValue());
			else
				throw new PsyTypeCheckException();
			return start>=0? PsyInteger.valueOf(start): null;
		}
		catch(final IndexOutOfBoundsException e)
		{
			throw new PsyRangeCheckException();
		}
		catch(final IllegalArgumentException e)
		{
			throw new PsyUndefinedException();
		}
		catch(final IllegalStateException e)
		{
			throw new PsyInvalidStateException();
		}
	}

	public PsyInteger psyCaptureGroupEnd(final PsyObject oKey)
		throws PsyException
	{
		try
		{
			final int end;
			if(oKey instanceof PsyInteger)
				end=matcher.start(((PsyInteger)oKey).intValue());
			else if(oKey instanceof PsyTextual)
				end=matcher.start(((PsyTextual)oKey).stringValue());
			else
				throw new PsyTypeCheckException();
			return end>=0? PsyInteger.valueOf(end): null;
		}
		catch(final IndexOutOfBoundsException e)
		{
			throw new PsyRangeCheckException();
		}
		catch(final IllegalArgumentException e)
		{
			throw new PsyUndefinedException();
		}
		catch(final IllegalStateException e)
		{
			throw new PsyInvalidStateException();
		}
	}

	private final java.util.regex.Matcher matcher;

	public static final PsyOperator[] OPERATORS=
		{
			new PsyOperator.Action
				("capturegroup", (oContext)->
					{
						final var ostack=oContext.operandStackBacked(2);
						try
						{
							final var matcher=((PsyMatcher)ostack.getBacked(0)).matcher;
							final var oKey=ostack.getBacked(1);
							if(oKey instanceof PsyInteger)
							{
								final var index=((PsyInteger)oKey).intValue();
								final var group=matcher.group(index);
								if(group!=null)
								{
									ostack.push(new PsyName(group));
									ostack.push(PsyInteger.valueOf(matcher.start(index)));
									ostack.push(PsyInteger.valueOf(matcher.end(index)));
								}
								ostack.push(PsyBoolean.valueOf(group!=null));
							}
							else if(oKey instanceof PsyTextual)
							{
								final var key=((PsyTextual)oKey).stringValue();
								final var group=matcher.group(key);
								if(group!=null)
								{
									ostack.push(new PsyName(group));
									ostack.push(PsyInteger.valueOf(matcher.start(key)));
									ostack.push(PsyInteger.valueOf(matcher.end(key)));
								}
								ostack.push(PsyBoolean.valueOf(group!=null));
							}
							else
								throw new PsyTypeCheckException();
						}
						catch(final IndexOutOfBoundsException e)
						{
							throw new PsyRangeCheckException();
						}
						catch(final IllegalArgumentException e)
						{
							throw new PsyUndefinedException();
						}
						catch(final IllegalStateException e)
						{
							throw new PsyInvalidStateException();
						}
					}),
			new PsyOperator.Arity11<PsyMatcher>("capturegroupcount", PsyMatcher::psyCaptureGroupCount),
			new PsyOperator.Arity21<PsyTextual, PsyRegExp>("matcher", PsyMatcher::new),
			new PsyOperator.Arity11<PsyMatcher>("matches", PsyMatcher::psyMatches),
			new PsyOperator.Arity21<PsyMatcher, PsyTextual>("replaceall", PsyMatcher::psyReplaceAll),
		};
}
