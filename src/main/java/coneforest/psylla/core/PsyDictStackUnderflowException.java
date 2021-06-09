package coneforest.psylla.core;
import coneforest.psylla.*;

@ExceptionType("dictstackunderflow")
public class PsyDictStackUnderflowException
	extends PsyException
{
	@Override
	public String getName()
	{
		return "dictstackunderflow";
	}
}
