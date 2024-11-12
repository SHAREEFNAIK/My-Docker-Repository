package com.naik.service;

import com.naik.Exception.InvalidSSNException;
import com.naik.bindings.AppRegistrationInputs;

public interface IAppRegistrationService {
 public Integer registerCitizenApplication(AppRegistrationInputs inputs) throws InvalidSSNException ;
}
