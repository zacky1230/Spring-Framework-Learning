package com.zctech88.simple.jdbc;

import lombok.Builder;
import lombok.Data;

/**
 * @author <a href="mailto:gy1zc3@gmail.com">zacky</a>
 * @since 11/24/19 11:20
 */
@Data
@Builder
public class Foo {
    private Long id;
    private String bar;
}
