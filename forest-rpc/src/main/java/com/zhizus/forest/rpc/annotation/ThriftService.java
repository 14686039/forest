package com.zhizus.forest.rpc.annotation;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.springframework.stereotype.Component;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target({java.lang.annotation.ElementType.TYPE})
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Component
public @interface ThriftService {

    String[] value() default {};

    Class<? extends TProtocol> thriftProtoType() default TBinaryProtocol.class;
}
