package com.piper.valley.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Component
public class Authenticator {
	@Autowired
	private HttpServletRequest request;
	public boolean isAuth()
	{
		//BEST AUTH SYSTEM EVAR
		HttpSession httpSession = request.getSession();
		Object username = httpSession.getAttribute("username");
		if(username != null)
			return true;
		return false;
	}

	public boolean saveAuth(int id, String username)
	{
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("id", id);
		httpSession.setAttribute("username", username);
		return true;
	}

	public boolean removeAuth()
	{
		HttpSession httpSession = request.getSession();
		httpSession.removeAttribute("username");
		httpSession.removeAttribute("id");
		return true;
	}

}
