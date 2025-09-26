package com.soulware.tcompro.iam.domain.model.commands;

public record ChangePasswordCommand(String email, String newPassword) {
}
