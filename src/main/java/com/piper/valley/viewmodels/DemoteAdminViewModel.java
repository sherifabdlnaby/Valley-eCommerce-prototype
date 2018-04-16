package com.piper.valley.viewmodels;

import com.piper.valley.forms.DemoteAdminForm;
import com.piper.valley.models.domain.User;
import com.piper.valley.models.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public class DemoteAdminViewModel {
    @Autowired
    private UserService userService;

    public HashMap<String, Object> create(DemoteAdminForm form, Long userId) {
        HashMap<String, Object> model = new HashMap<>();
        List<User> admins =  userService.getAllActiveSubordinates(userId);
        model.put("form"    , form);
        model.put("admins"  , admins);
        return model;
    }
}
