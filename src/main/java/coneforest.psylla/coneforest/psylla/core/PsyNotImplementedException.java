package coneforest.psylla.core;

import coneforest.psylla.runtime.*;

@ErrorType("notimplemented")
public class PsyNotImplementedException
	extends PsyErrorException
{
	/**
	*	Constructs a new {@code exception} object with null as its detail message.
	*/
	public PsyNotImplementedException()
	{
		super();
	}
}
