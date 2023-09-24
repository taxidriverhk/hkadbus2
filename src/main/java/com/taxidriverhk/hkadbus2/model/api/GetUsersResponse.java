package com.taxidriverhk.hkadbus2.model.api;

import java.util.List;

import com.taxidriverhk.hkadbus2.model.domain.User;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GetUsersResponse {

    private List<User> users;
}
