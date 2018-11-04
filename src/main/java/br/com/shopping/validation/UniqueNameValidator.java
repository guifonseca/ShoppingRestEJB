package br.com.shopping.validation;

import javax.ejb.EJB;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.shopping.model.Loja;
import br.com.shopping.service.LojaService;

public class UniqueNameValidator implements ConstraintValidator<UniqueName, Loja> {
	
	@EJB
	private LojaService lojaService;

	public void initialize(UniqueName arg0) {
		// TODO Auto-generated method stub
		
	}

	public boolean isValid(Loja loja, ConstraintValidatorContext arg1) {
		Loja l = lojaService.buscarPorNome(loja.getNome());
		return l == null || (l != null && l.getId() == loja.getId());
	}
}
