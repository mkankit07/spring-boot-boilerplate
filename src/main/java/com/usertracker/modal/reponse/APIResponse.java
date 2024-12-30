package com.usertracker.modal.reponse;

public record APIResponse(int code, String message, Object data) { }