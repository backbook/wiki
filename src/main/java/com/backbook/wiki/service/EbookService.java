package com.backbook.wiki.service;


import com.backbook.wiki.domain.Ebook;
import com.backbook.wiki.domain.EbookExample;
import com.backbook.wiki.mapper.EbookMapper;
import com.backbook.wiki.req.EbookQueryReq;
import com.backbook.wiki.req.EbookSaveReq;
import com.backbook.wiki.resp.EbookQueryResp;
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
public class EbookService {

    private static final Logger LOG = LoggerFactory.getLogger(EbookService.class);
    @Resource
    private EbookMapper ebookMapper;
    @Resource
    private SnowFlake snowFlake;


    public PageResp<EbookQueryResp> list(EbookQueryReq ebookQueryReq){
        EbookExample ebookExample = new EbookExample();
        EbookExample.Criteria criteria = ebookExample.createCriteria();
        if(!ObjectUtils.isEmpty(ebookQueryReq.getName())){
            criteria.andNameLike("%"+ ebookQueryReq.getName()+"%");
        }
        if(!ObjectUtils.isEmpty(ebookQueryReq.getCategoryId2())){
            criteria.andCategory2IdEqualTo( ebookQueryReq.getCategoryId2());
        }
        //只针对于第一个sql有作用
        PageHelper.startPage(ebookQueryReq.getPage(), ebookQueryReq.getSize());
        List<Ebook> ebooksList = ebookMapper.selectByExample(ebookExample);
        PageInfo<Ebook> pageInfo = new PageInfo<>(ebooksList);
        LOG.info("总行数: {}",pageInfo.getTotal());
        LOG.info("总页数: {}",pageInfo.getPages());

//        List<EbookResp> respList = new ArrayList<>();
//        for (Ebook ebook : ebooks) {
//            EbookResp ebookResp = new EbookResp();
//            BeanUtils.copyProperties(ebook,ebookResp);
//            respList.add(ebookResp);
//        }
        PageResp<EbookQueryResp> pageResp = new PageResp<>();

        List<EbookQueryResp> respList = CopyUtil.copyList(ebooksList, EbookQueryResp.class);

        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(respList);

        return pageResp;
    }


    public void save(EbookSaveReq req){
        Ebook ebook = CopyUtil.copy(req, Ebook.class);
        if (ObjectUtils.isEmpty(req.getId())){
            //新增
           ebook.setId(snowFlake.nextId());
            ebookMapper.insert(ebook);
        }else {
            //更新
            ebookMapper.updateByPrimaryKey(ebook);
        }

    }

    public void delete(Long id){
        ebookMapper.deleteByPrimaryKey(id);
    }

}
