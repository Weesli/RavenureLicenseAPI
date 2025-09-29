package net.weesli.ravenurelicenseapi.model;

import net.weesli.ravenurelicenseapi.enums.LicenseStatus;

public record LicenseResponse(LicenseStatus status, String message) {
}
