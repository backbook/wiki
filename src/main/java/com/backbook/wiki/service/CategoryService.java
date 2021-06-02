package com.backbook.wiki.service;


import com.backbook.wiki.domain.Category;
import com.backbook.wiki.domain.CategoryExample;
import com.backbook.wiki.mapper.CategoryMapper;
import com.backbook.wiki.req.CategoryQueryReq;
import com.backbook.wiki.req.CategorySaveReq;
import com.backbook.wiki.resp.CategoryQueryResp;
import com.backbook.wiki.resp.PageResp;
import com.backbook.wiki.util.CopyUtil;
import com.backbook.wiki.util.SnowFlake;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CategoryService {

    private static final Logger LOG = LoggerFactory.getLogger(CategoryService.class);
    @Resource
    private CategoryMapper categoryMapper;
    @Resource
    private SnowFlake snowFlake;


    public List<CategoryQueryResp> all(){
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.setOrderByClause("sort asc");
        //只针对于第一个sql有作用
        List<Category> categorysList = categoryMapper.selectByExample(categoryExample);
        List<CategoryQueryResp> respList = CopyUtil.copyList(categorysList, CategoryQueryResp.class);
        return respList;
    }


    public PageResp<CategoryQueryResp> list(CategoryQueryReq categoryQueryReq){
        CategoryExample categoryExample = new CategoryExample();
        CategoryExample.Criteria criteria = categoryExample.createCriteria();
        categoryExample.setOrderByClause("sort asc");
        //只针对于第一个sql有作用
        PageHelper.startPage(categoryQueryReq.getPage(), categoryQueryReq.getSize());
        List<Category> categorysList = categoryMapper.selectByExample(categoryExample);
        PageInfo<Category> pageInfo = new PageInfo<>(categorysList);
        LOG.info("总行数: {}",pageInfo.getTotal());
        LOG.info("总页数: {}",pageInfo.getPages());

//        List<CategoryResp> respList = new ArrayList<>();
//        for (Category category : categorys) {
//            CategoryResp categoryResp = new CategoryResp();
//            BeanUtils.copyProperties(category,categoryResp);
//            respList.add(categoryResp);
//        }
        PageResp<CategoryQueryResp> pageResp = new PageResp<>();

        List<CategoryQueryResp> respList = CopyUtil.copyList(categorysList, CategoryQueryResp.class);

        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(respList);

        return pageResp;
    }


    public void save(CategorySaveReq req){
        Category category = CopyUtil.copy(req, Category.class);
        if (ObjectUtils.isEmpty(req.getId())){
            //新增
            category.setId(snowFlake.nextId());
            categoryMapper.insert(category);
        }else {
            //更新
            categoryMapper.updateByPrimaryKey(category);
        }

    }

    public void delete(Long id){
        categoryMapper.deleteByPrimaryKey(id);
    }

}
