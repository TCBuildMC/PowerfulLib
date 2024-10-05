package xyz.tcbuildmc.common.powerfullib.config.v0.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString(callSuper = false)
@EqualsAndHashCode(callSuper = false)
public final class TestObject {
    public String name;
    public int age;
}
