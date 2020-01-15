package org.innovate.restapitrafficlogger.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class SampleController {

    public static final Logger LOG = LoggerFactory.getLogger(SampleController.class);

    @GetMapping(value = "sample", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getMessage() {
        LOG.info("Sample controller!");
        return new ResponseEntity<String>("{'message' : 'I am doing good' }", HttpStatus.OK);
    }
}
