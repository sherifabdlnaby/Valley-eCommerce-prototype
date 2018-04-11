package com.piper.valley.controllers;

import com.piper.valley.forms.FinishOrderForm;
import com.piper.valley.models.domain.Order;
import com.piper.valley.models.service.OrderService;
import com.piper.valley.utilities.FlashMessages;
import com.piper.valley.validators.FinishOrderValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class OrderController {
	/////////////////////////*  SERVICES, REPOSITORIES AND VALIDATORS SECTION  */////////////////////////////
	@Autowired
	private OrderService orderService;


	@Autowired
	private FinishOrderValidator finishOrderValidator;


	/////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////*  VALIDATORS BINDING SECTION  *//////////////////////////////////////

	@InitBinder("finishOrderForm")
	public void addFormInitBinder(WebDataBinder binder) {
		binder.addValidators(finishOrderValidator);
	}


	/////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////*  CONTROLLER ACTION  *///////////////////////////////////////////


	@PreAuthorize("hasAuthority('STORE_OWNER')")
	@RequestMapping(value = "/order/finish", method = RequestMethod.POST)
	public ModelAndView acceptStore(@Valid @ModelAttribute("finishOrderForm") FinishOrderForm finishOrderForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		if(bindingResult.hasErrors()){
			FlashMessages.success("Failed!", redirectAttributes);

		} else {
			Order order = orderService.finishOrder(finishOrderForm.getOrderId());
			FlashMessages.success("Order marked as Delivered!", redirectAttributes);
		}
		return new ModelAndView("redirect:/store/orders");
	}

}
