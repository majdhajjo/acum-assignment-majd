package org.example.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrMsg {
    private String message;
}
