package org.stellardev.galacticlib.exception;

public class HandlerAlreadyRegisteredException extends Exception {

    public HandlerAlreadyRegisteredException(String handlerType) {
        super(handlerType + " handler is already registered, and cannot be modified. Try to make sure that you are adding your handler before it is first called.");
    }

}
