package cn.hcnet2006.blog.hcnetwebsite.service;
import cn.hcnet2006.core.page.PageRequest;
import cn.hcnet2006.core.page.PageResult;
import cn.hcnet2006.blog.hcnetwebsite.bean.SysDept;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SysDeptService extends CurdService<SysDept>{
    @Override
    int save(SysDept record);

    @Override
    int delete(SysDept record);

    @Override
    int delete(List<SysDept> records);

    @Override
    SysDept findById(Long id);

    @Override
    PageResult findPage(PageRequest pageRequest);

    List<SysDept> findRootTree();

    List<SysDept> findByParentId(Long parentId);
}
