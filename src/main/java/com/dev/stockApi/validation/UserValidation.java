package com.dev.stockApi.validation;

import com.dev.stockApi.entity.UserEntity;
import com.dev.stockApi.exception.ApiException;

public class UserValidation {

    public static void verifyAccountStatus(UserEntity user) {
        if(!user.isEnabled()) { throw new ApiException("Account is disabled"); }
        if(!user.isAccountNonExpired()) { throw new ApiException("Account is expired"); }
        if(!user.isAccountNonLocked()) { throw new ApiException("Account is locked"); }
    }
}
