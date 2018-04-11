package com.piper.valley.viewmodels;

import com.piper.valley.forms.AddStoreCollaboratorForm;
import com.piper.valley.models.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;

@Component
public class AddStoreCollaboratorViewModel {
    @Autowired
    private StoreService storeService;

    public HashMap<String, Object> create(AddStoreCollaboratorForm form, Long StoreOwnerId) {
        HashMap<String, Object> model = new HashMap<>();
        model.put("form"        , form);
        model.put("stores"      , storeService.getAllAcceptedUserStores(StoreOwnerId));
        return model;
    }
}
