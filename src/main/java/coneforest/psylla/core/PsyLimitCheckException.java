package coneforest.psylla.core;
import coneforest.psylla.*;

@ExceptionType("limitcheck")
public class PsyLimitCheckException
	extends PsyException
{
	@Override
	public String getName()
	{
		return "limitcheck";
	}
}
