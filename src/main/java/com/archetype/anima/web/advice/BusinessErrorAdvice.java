package com.archetype.anima.web.advice;

import com.archetype.anima.error.InsertException;
import com.archetype.anima.error.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@ControllerAdvice("com.archetype.anima.web.controller")
public class BusinessErrorAdvice {

    @ExceptionHandler(value = {NotFoundException.class})
    public String handleNotFound(RedirectAttributes redirectAttributes) {
        log.debug("not found. redirect to index");
        redirectAttributes.addFlashAttribute("alert", "not found");
        return "redirect:/anima/books";
    }

    @ExceptionHandler(value = {InsertException.class})
    public String handleInsertError(Throwable e, RedirectAttributes redirectAttributes) {
        log.debug("insert error.cause=" + e.getMessage());
        redirectAttributes.addFlashAttribute("alert", "insert error");
        return "redirect:/anima/books";
    }
}
