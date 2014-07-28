package coneforest.psi;
import coneforest.psi.systemdict.*;

public class PsiSystemDictionary
	extends PsiModule
{
	public PsiSystemDictionary()
	{
		super();

		registerOperatorClasses
			(
				new Class[]
					{
						_abs.class,
						_add.class,
						_aload.class,
						_and.class,
						_append.class,
						_appendall.class,
						_arg.class,
						_array.class,
						_arraytomark.class,
						_astore.class,
						_atan.class,
						_begin.class,
						_bind.class,
						_bitset.class,
						_bitshift.class,
						_bitvector.class,
						_cbrt.class,
						_ceiling.class,
						_clear.class,
						_cleardictstack.class,
						_close.class,
						_complex.class,
						_conjugate.class,
						_copy.class,
						_cos.class,
						_cosh.class,
						_count.class,
						_countdictstack.class,
						_countexecstack.class,
						_counttomark.class,
						_currentdict.class,
						_cvlit.class,
						_cvn.class,
						_cvs.class,
						_cvx.class,
						_def.class,
						_dict.class,
						_dicttomark.class,
						_div.class,
						_dup.class,
						_end.class,
						_eq.class,
						_exch.class,
						_exec.class,
						_exit.class,
						_exp.class,
						_false.class,
						_filereader.class,
						_filewriter.class,
						_floor.class,
						_flush.class,
						_for.class,
						_forall.class,
						_ge.class,
						_get.class,
						_gt.class,
						_hypot.class,
						_idiv.class,
						_if.class,
						_ifelse.class,
						_index.class,
						_isempty.class,
						_known.class,
						_le.class,
						_length.class,
						_load.class,
						_log.class,
						_loop.class,
						_lt.class,
						_mark.class,
						_mod.class,
						_mul.class,
						_ne.class,
						_neg.class,
						_not.class,
						_null.class,
						_or.class,
						_pop.class,
						_pow.class,
						_prettyprint.class,
						_pstack.class,
						_put.class,
						_quit.class,
						_read.class,
						_remove.class,
						_repeat.class,
						_roll.class,
						_signum.class,
						_sin.class,
						_sinh.class,
						_sqrt.class,
						_stop.class,
						_stopped.class,
						_string.class,
						_stringreader.class,
						_stringwriter.class,
						_sub.class,
						_tan.class,
						_tanh.class,
						_tointeger.class,
						_token.class,
						_true.class,
						_type.class,
						_undef.class,
						_where.class,
						_writestring.class,
						_xcheck.class,
						_xor.class,
					}
				);
		try
		{
			put("[", get("mark"));
			put("<<", get("mark"));
			put("]", get("arraytomark"));
			put(">>", get("dicttomark"));
			put("==", get("prettyprint"));
		}
		catch(PsiException e)
		{
			// TODO
		}
		put("systemdict", this);
		put("errordict", new coneforest.psi.errordict.ErrorDictionary());
		put("mathpi", new PsiReal(Math.PI));
		put("mathe", new PsiReal(Math.E));
	}
}
