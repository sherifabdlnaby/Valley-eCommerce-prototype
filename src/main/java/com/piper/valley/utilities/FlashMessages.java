package com.piper.valley.utilities;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FlashMessages {
	public static void danger(String msg, RedirectAttributes redirectAttributes) {
		add(msg, redirectAttributes, MsgType.DANGER);
	}

	public static void info(String msg, RedirectAttributes redirectAttributes) {
		add(msg, redirectAttributes, MsgType.INFO);
	}

	public static void warning(String msg, RedirectAttributes redirectAttributes) {
		add(msg, redirectAttributes, MsgType.WARNING);
	}

	public static void success(String msg, RedirectAttributes redirectAttributes) {
		add(msg, redirectAttributes, MsgType.SUCCESS);
	}

	private static void add(String msg, RedirectAttributes redirectAttributes, MsgType msgType) {
		String flashVar = "_" + msgType.toString().toLowerCase();
		List<String> msgs;
		Map<String, ?> flashattr = redirectAttributes.getFlashAttributes();
		if (flashattr.containsKey(flashVar))
			msgs = (List<String>) flashattr.get(flashVar);
		else
			msgs = new ArrayList<>();

		msgs.add(msg);
		redirectAttributes.addFlashAttribute(flashVar, msgs);
	}
}

