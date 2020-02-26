package cn.hcnet2006.blog.microconsumer.fallback;

import cn.hcnet2006.blog.microconsumer.service.FeignFooService;
import org.springframework.stereotype.Component;

@Component
public class FeginFooHystrix implements FeignFooService {
    @Override
    public String foo() {
        return "sorry, foo service call failed";
    }
}
