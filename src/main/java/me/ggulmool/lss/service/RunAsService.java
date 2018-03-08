package me.ggulmool.lss.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class RunAsService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Secured({"ROLE_RUN_AS_REPORTER"})
    public Authentication getCurrentUser() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        logger.debug("kny log - RunAsService#getCurrentUser() call");
        return authentication;
    }

    public Authentication getGeneralUser() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        logger.debug("kny log - RunAsService#getGeneralUser() call");
        return authentication;
    }
}
