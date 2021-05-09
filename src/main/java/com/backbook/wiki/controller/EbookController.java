package com.backbook.wiki.controller;


import com.backbook.wiki.req.EbookReq;
import com.backbook.wiki.resp.CommonResp;
import com.backbook.wiki.resp.EbookResp;
import com.backbook.wiki.service.EbookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/ebook")
public class EbookController {
    @Resource
    private EbookService ebookService;

    @GetMapping("/list")
    public CommonResp<List<EbookResp>> list(EbookReq ebookReq){
        CommonResp<List<EbookResp>> resp = new CommonResp<>();
        List<EbookResp> list = ebookService.list(ebookReq);
        resp.setMessage("this is ebook");
        resp.setContent(list);
        return resp;
    }

}
