/**
 * Copyright (C) 2014 BigLoupe http://bigloupe.github.io/SoS-JobScheduler/
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */
package org.jobscheduler.dashboard.web.rest;

import com.codahale.metrics.annotation.Timed;
import org.jobscheduler.dashboard.domain.Authority;
import org.jobscheduler.dashboard.domain.PersistentToken;
import org.jobscheduler.dashboard.domain.User;
import org.jobscheduler.dashboard.repository.PersistentTokenRepository;
import org.jobscheduler.dashboard.repository.UserRepository;
import org.jobscheduler.dashboard.security.SecurityUtils;
import org.jobscheduler.dashboard.service.UserService;
import org.jobscheduler.dashboard.web.rest.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * REST controller for managing the current user's account.
 */
@RestController
public class AccountResource {

    private final Logger log = LoggerFactory.getLogger(AccountResource.class);

    @Inject
    private UserRepository userRepository;

    @Inject
    private UserService userService;

    @Inject
    private PersistentTokenRepository persistentTokenRepository;

    /**
     * GET  /rest/authenticate -> check if the user is authenticated, and return its login.
     */
    @RequestMapping(value = "/authenticate",
            method = RequestMethod.GET,
            produces = "application/json")
    @Timed
    public String isAuthenticated(HttpServletRequest request) {
        log.debug("REST request to check if the current user is authenticated");
        return request.getRemoteUser(); 
    }

    /**
     * GET  /rest/account -> get the current user.
     */
    @RequestMapping(value = "/rest/account",
            method = RequestMethod.GET,
            produces = "application/json")
    @Timed
    public UserDTO getAccount(HttpServletResponse response) {
        User user = userService.getUserWithAuthorities();
        if (user == null) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return null;
        }
        Map<String, Boolean> roles = new HashMap<>();
        for (Authority authority : user.getAuthorities()) {
            roles.put(authority.getName(), Boolean.TRUE);
        }
        return new UserDTO(user.getLogin(), user.getFirstName(), user.getLastName(),
                user.getEmail(), roles);
    }

    /**
     * POST  /rest/account -> update the current user information.
     */
    @RequestMapping(value = "/rest/account",
            method = RequestMethod.POST,
            produces = "application/json")
    @Timed
    public void saveAccount(@RequestBody UserDTO userDTO) throws IOException {
        userService.updateUserInformation(userDTO.getFirstName(), userDTO.getLastName(), userDTO.getEmail());
    }

    /**
     * POST  /rest/change_password -> changes the current user's password
     */
    @RequestMapping(value = "/rest/account/change_password",
            method = RequestMethod.POST,
            produces = "application/json")
    @Timed
    public void changePassword(@RequestBody String password, HttpServletResponse response) throws IOException {
        if (password == null || password.equals("")) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Password should not be empty");
        } else {
            userService.changePassword(password);
        }
    }

    /**
     * GET  /rest/account/sessions -> get the current open sessions.
     */
    @RequestMapping(value = "/rest/account/sessions",
            method = RequestMethod.GET,
            produces = "application/json")
    @Timed
    public List<PersistentToken> getCurrentSessions(HttpServletResponse response) {
        User user = userRepository.findOne(SecurityUtils.getCurrentLogin());
        if (user == null) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        return persistentTokenRepository.findByUser(user);
    }

    /**
     * DELETE  /rest/account/sessions?series={series} -> invalidate an existing session.
     */
    @RequestMapping(value = "/rest/account/sessions/{series}",
            method = RequestMethod.DELETE)
    @Timed
    public void invalidateSession(@PathVariable String series) throws UnsupportedEncodingException {
        String decodedSeries = URLDecoder.decode(series, "UTF-8");
        persistentTokenRepository.delete(decodedSeries);
    }
}
