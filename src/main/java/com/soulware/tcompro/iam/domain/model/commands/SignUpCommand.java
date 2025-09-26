package com.soulware.tcompro.iam.domain.model.commands;

public record SignUpCommand(String email, String password, String role, String firstName, String lastName, String phoneNumber) {
}
