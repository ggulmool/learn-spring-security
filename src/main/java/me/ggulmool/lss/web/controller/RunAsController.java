package me.ggulmool.lss.web.controller;

import me.ggulmool.lss.service.RunAsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/runas")
class RunAsController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RunAsService runAsService;

    @Secured({ "ROLE_USER", "RUN_AS_REPORTER" })
    @RequestMapping
    @ResponseBody
    public String tryRunAs() {
        final Authentication auth = runAsService.getCurrentUser();
        auth.getAuthorities().forEach(a -> logger.debug("kny log - RunAsController#tryRunAs() - {}", a.getAuthority()));
        return "Current User Authorities inside this RunAS method only " + auth.getAuthorities().toString();
    }

    @RequestMapping("/general")
    @ResponseBody
    public String generalRunAs() {
        final Authentication auth = runAsService.getGeneralUser();
        auth.getAuthorities().forEach(a -> logger.debug("kny log - RunAsController#tryRunAs() - {}", a.getAuthority()));
        return "General User Authorities " + auth.getAuthorities().toString();
    }

}
